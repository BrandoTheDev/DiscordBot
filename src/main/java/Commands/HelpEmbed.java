package Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.util.Random;

public class HelpEmbed {
    private Command command;

    public HelpEmbed(Command command) {
        this.command = command;
    }

    public MessageEmbed createHelpEmbed() {
        // Setup a random color for each embed
        Random random = new Random();
        int randomColor = random.nextInt(0, 255);

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(command.getName());
        eb.setDescription(command.getDescription());
        eb.addBlankField(false);
        for(String key : command.getUsage().keySet()) {
            eb.addField(key,command.getUsage().get(key),false);
        }
        eb.setColor(randomColor);
        return eb.build();
    }
}
