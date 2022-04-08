package Commands.APIs.Dictionary;

import Commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;

public class Whats extends Command {
    private String title = "";
    private String whatItIs = "";
    private String whatDescription = "";
    private String errorMessage = "";

    private final HashMap<String, String> usage = new HashMap<>();

    public Whats() {
        usage.put("!whats <thing-to-search>", "Dictionary search for a definition");
    }

    private MessageEmbed dataEmbed() {
        EmbedBuilder db = new EmbedBuilder();
        db.setTitle(this.title);
        db.setDescription(this.whatDescription);
        db.addField("What is it?", whatItIs, false);
        return db.build();
    }

    private MessageEmbed errorEmbed() {
        EmbedBuilder db = new EmbedBuilder();
        db.setTitle("Error:");
        db.setDescription("That does not exist in the dictionary.");
        return db.build();
    }

    private MessageEmbed fetchWord(String word) {
        try {

            Document doc = Jsoup.connect("https://www.merriam-webster.com/dictionary/" + word).get();

            this.errorMessage = doc.getElementsByClass("spelling-suggestion-text").text();

            if(this.errorMessage.equals("")) {

                // A temporary fix until I can figure out why its grabbing two titles/what it is
                String[] t = doc.getElementsByClass("hword").text().split(" ");
                String[] w = doc.getElementsByClass("important-blue-link").text().split(" ");

                this.title = t[0];
                this.whatItIs = w[0];
                this.whatDescription = doc.getElementById("dictionary-entry-1").text();
                return dataEmbed();
            }
        } catch (Exception e) {
            // Probably a 404 HttpStatusException. TODO: Handle exception properly
            e.printStackTrace();
        }
        return errorEmbed();
    }

    @Override
    public String getName() {
        return "whats";
    }

    @Override
    public String getDescription() {
        return "Search for the definition of a word";
    }

    @Override
    public HashMap<String, String> getUsage() {
        return usage;
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        String term = event.getMessage().getContentDisplay();
        term = term.replace("!whats", "");

        event.getTextChannel().sendMessageEmbeds(fetchWord(term)).queue();
    }
}
