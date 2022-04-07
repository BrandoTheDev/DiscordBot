package Commands.APIs.Jokes;

import Commands.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;

public class DadJoke extends Command {
    private HashMap<String, String> usage = new HashMap<>();

    public DadJoke() {
        usage.put("!joke", "Fetches a dad joke");
    }

    private String fetchJoke() {
        try {
            // Grab our page we want to scrape
            Document doc = Jsoup.connect("https://icanhazdadjoke.com/").get();

            // <p class="subtitle">
            String joke = doc.getElementsByClass("subtitle").text();

            // Remove the watermark prepended
            joke = joke.replace("icanhazdadjoke", "");

            return joke;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "Dad joke API might be down.. try again later.";
    }

    @Override
    public String getName() {
        return "joke";
    }

    @Override
    public String getDescription() {
        return "Tells you a knee-slappin' dad joke.";
    }

    @Override
    public HashMap<String, String> getUsage() {
        return usage;
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        event.getTextChannel().sendMessage(fetchJoke()).queue();
    }
}
