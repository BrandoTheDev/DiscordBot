package Commands.APIs.Weather;

import Commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;

public class Weather extends Command {
    private final HashMap<String, String> usage = new HashMap<>();

    private String weatherInFahrenheit;
    private String precipitation;
    private String humidity;
    private String wind;
    private String location;

    public Weather() {
        usage.put("!weather <zipcode>", "Gives you the daily forecast (!weather 96793)");
    }

    private MessageEmbed weatherEmbed() {
        EmbedBuilder weatherCard = new EmbedBuilder();
        weatherCard.setTitle(this.location);
        weatherCard.setDescription("Current Weather: " + weatherInFahrenheit + " F");
        weatherCard.addField("Precipitation:", this.precipitation,false);
        weatherCard.addField("Humidity:", this.humidity,false);
        weatherCard.addField("Wind:", this.wind,false);
        weatherCard.setColor(Integer.parseInt(weatherInFahrenheit));
        return weatherCard.build();
    }

    private void getWeather(String zipcode) {
        try {
            // Grab our page we want to scrape
            Document doc = Jsoup.connect("https://www.google.com/search?q=weather" + zipcode).get();

            //<span id="wob_tm" class="wob_t q8U8x" style="display:inline"></span>
            this.weatherInFahrenheit = doc.getElementById("wob_tm").text();

            // <div>Precipitation <span id="wob_pp"></span></div>
            this.precipitation = doc.getElementById("wob_pp").text();

            // <div>Humidity <span id="wob_hm"></span></div>
            this.humidity = doc.getElementById("wob_hm").text();

            //  <div>Wind <span id="wob_ws"></span></div>
            this.wind = doc.getElementById("wob_ws").text();

            //<div class="wob_loc q8U8x" id="wob_loc"></div>
            this.location = doc.getElementById("wob_loc").text();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "weather";
    }

    @Override
    public String getDescription() {
        return "Returns weather info like precipitation, wind, humidity.";
    }

    @Override
    public HashMap<String, String> getUsage() {
        return usage;
    }

    @Override
    public void execute(MessageReceivedEvent event) {

        String[] messageArray = event.getMessage().getContentDisplay().split(" ");

        if(messageArray.length <= 1)
        {
            event.getTextChannel().sendMessage("Please enter a zipcode after the command. !weather 96793").queue();
        }
        else
        {
            try
            {
                getWeather(messageArray[1]);

                if(this.location == null
                        || this.weatherInFahrenheit == null
                        || this.humidity == null
                        || this.wind == null
                        || this.precipitation == null)
                {
                    event.getTextChannel().sendMessage("Please enter a valid US zip").queue();
                }
                else
                {
                    event.getTextChannel().sendMessageEmbeds(weatherEmbed()).queue();
                }
            }
            catch (Exception e)
            {
                event.getTextChannel().sendMessage("Please enter a valid US zip").queue();
            }
        }
    }
}
