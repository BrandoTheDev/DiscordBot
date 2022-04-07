package Commands;

import Commands.APIs.Jokes.DadJoke;
import Commands.APIs.Weather.Weather;
import Commands.Games.Gambling.Coinflip;
import Commands.Info.HelpEmbed;
import Commands.Misc.Ping;
import Commands.Misc.Whois;
import Commands.Moderation.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public class CommandFinder {
    private MessageReceivedEvent event;
    private final List<Command> commandList = new ArrayList<>();
    private Command commandToRun;

    public CommandFinder() {
                                            // TODO:
                                            // Add new commands here
        commandList.add(new Ping());        // Misc
        commandList.add(new Whois());       // Misc
        commandList.add(new Coinflip());    // Games.Gambling
        commandList.add(new Deafen());      // Moderation
        commandList.add(new Undeafen());    // Moderation
        commandList.add(new Mute());        // Moderation
        commandList.add(new Unmute());      // Moderation
        commandList.add(new Timeout());     // Moderation
        commandList.add(new Weather());     // APIs.Weather
        commandList.add(new DadJoke());     // APIs.Jokes
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
        for(Command cmd : commandList) {
            eb.addField(cmd.getName(), cmd.getDescription(), true);
        }
        eb.addBlankField(false);
        eb.setFooter("Use !help <command> for usage help.");

        this.event.getTextChannel().sendMessageEmbeds(eb.build()).queue();
    }
}