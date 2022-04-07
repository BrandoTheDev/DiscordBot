package Commands.Misc;

import Commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Whois extends Command {
    private final Random random = new Random(System.currentTimeMillis());
    private final HashMap<String, String> usage = new HashMap<>();

    public Whois() {
        addUsage();
    }

    private void addUsage() {
        usage.put("!whois @mentionedUser", "Displays a card of the user with a fun fact!");
    }

    /**
     * Basic function wrapper of random.nextInt
     * Made for code readability.
     * @param origin The lowest number you want
     * @param bound The highest number you want
     * @return an int between origin and bound
     */
    private int randomValue(int origin, int bound) {
        return this.random.nextInt(origin, bound);
    }

    private String getFunFact() {
        String[] facts = {
                "Likes vegemite on everything",
                "Paints their toenails",
                "Sleeps with a body pillow",
                "Made out with their cousin once",
                "Puts fish on their pizza",
                "Farts at the urinal",
                "Hates their family",
                "Wears a thong",
                "Hates playing video games",
                "Eats unborn babies",
                "Likes long walks on the beaches",
                "Sings in the shower",
                "Ate an entire box of crayons",
                "Watches anime dubbed",
                "Wears a diaper",
                "Was held back 3 grades",
                "Drinks pond water",
                "Eats their own toenails",
                "Has 3 nipples",
                "Only eats ant hills",
                "Watches anime only in English dubbed"
        };

        int funnyNumber = randomValue(0, facts.length -1);
        return facts[funnyNumber];
    }

    /**
     * Creates a MessageEmbed (card) of the mentioned user
     * @param user our mentioned user to create the card for
     * @return MessageEmbed to send to a text channel
     */
    private MessageEmbed whoisEmbed(User user) {
        int randomFactNumber = randomValue(50, 99);
        int randomColor = randomValue(0, 255);

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(user.getName());
        eb.setDescription(user.getAsTag());
        eb.addField("Fun fact that's " + randomFactNumber + "% true:", getFunFact(), false);
        if(user.getAvatarUrl() != null){
            eb.setThumbnail(user.getAvatarUrl());
        } else {
            eb.setThumbnail(user.getDefaultAvatarUrl());
        }
        eb.setColor(randomColor);
        return eb.build();
    }

    @Override
    public String getName() {
        return "whois";
    }

    @Override
    public String getDescription() {
        return "Gives details on a @mentioned user";
    }

    @Override
    public HashMap<String, String> getUsage() {
        return usage;
    }

    @Override
    public void execute(MessageReceivedEvent event) {

        TextChannel textChannel = event.getMessage().getTextChannel();
        List<User> mentionedUsers = event.getMessage().getMentionedUsers();
        boolean emptyList = mentionedUsers.isEmpty();

        if(emptyList){
            textChannel.sendMessage("You need to @mention a user.").queue();
        } else if (mentionedUsers.get(0).isBot()) {
            textChannel.sendMessage("Don't worry about who I am..").queue();
        } else if (mentionedUsers.size() > 1){
            textChannel.sendMessage("Slow down detective! Lets whois 1 member at a time..").queue();
        } else {
            User user = mentionedUsers.get(0);
            textChannel.sendMessageEmbeds(whoisEmbed(user)).queue();
        }
    }
}
