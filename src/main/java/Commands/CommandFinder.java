package Commands;

import Commands.Misc.Ping;
import Commands.Misc.Whois;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public class CommandFinder {
    private MessageReceivedEvent event;
    private List<Command> commandList = new ArrayList<>();
    private Command commandToRun;

    public CommandFinder() {
        // TODO:
        // Add new commands here
        commandList.add(new Ping());
        commandList.add(new Whois());
    }

    public boolean isCommand(String possibleCommand) {
        for(Command cmd : commandList) {
                if(cmd.getName().equals(possibleCommand)) {
                    this.commandToRun = cmd;
                    return true;
                }
            }
        return false;
    }

    public void setEvent(MessageReceivedEvent event) {
        this.event = event;
    }

    public void help() {
        HelpEmbed helpEmbed = new HelpEmbed(this.commandToRun);
        MessageEmbed me = helpEmbed.createHelpEmbed();
        this.event.getTextChannel().sendMessageEmbeds(me).queue();
    }

    public void run() {
        this.commandToRun.execute(this.event);
    }

    public void listCommands() {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("List of commands and their description:");
        eb.setColor(255);
        eb.addBlankField(false);
        int i = 0; // Counter to split commands by 4
        for(Command cmd : commandList) {
            i++;
            eb.addField(cmd.getName(), cmd.getDescription(), true);
            if(i % 4 == 0) {
                eb.addBlankField(true);
            }
        }
        eb.addBlankField(false);
        eb.setFooter("Use !help <command> for detailed help. Coming soon\u2122");

        this.event.getTextChannel().sendMessageEmbeds(eb.build()).queue();
    }
}