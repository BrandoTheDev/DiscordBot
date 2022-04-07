package Commands.Misc;

import Commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Whois extends Command {
    private String name = "whois";
    private String description = "Gives details on a @mentioned user";

    private List<User> mentionedUsers = new ArrayList<>();
    private TextChannel textChannel;

    private User user;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
    private String getFunFact() {
        String[] facts = {"Likes vegemite on everything",
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
                "Masturbates 9 times a day",
                "Wears a diaper",
                "Was held back 3 grades",
                "Drinks pond water",
                "Eats their own toenails"
        };

        Random random = new Random();
        int funnyNumber = random.nextInt(0, facts.length -1);
        return facts[funnyNumber];
    }

    private MessageEmbed whoisEmbed() {
        Random random = new Random();
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(user.getName());
        eb.setDescription(user.getAsTag());
        int randomFactNumber = random.nextInt(50, 99);
        eb.addField("Fun fact that's " + randomFactNumber + "% true:", getFunFact(), false);
        if(user.getAvatarUrl() != null){
            eb.setThumbnail(user.getAvatarUrl());
        } else {
            eb.setThumbnail(user.getDefaultAvatarUrl());
        }
        int randomColor = random.nextInt(0, 255);
        eb.setColor(randomColor);
        return eb.build();
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        boolean emptyList = event.getMessage().getMentionedUsers().isEmpty();
        mentionedUsers = event.getMessage().getMentionedUsers();
        textChannel = event.getMessage().getTextChannel();

        if(emptyList){
            textChannel.sendMessage("You need to @mention a user.").queue();
        } else if (mentionedUsers.get(0).isBot()) {
            textChannel.sendMessage("Don't worry about who I am..").queue();
        } else if (mentionedUsers.size() > 1){
            textChannel.sendMessage("Slow down detective! Lets whois 1 member at a time..").queue();
        } else {
            this.user = mentionedUsers.get(0);
            textChannel.sendMessageEmbeds(whoisEmbed()).queue();
        }
    }
}
