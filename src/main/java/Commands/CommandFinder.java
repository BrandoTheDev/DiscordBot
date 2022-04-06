package Commands;

import Commands.Misc.Ping;
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
        commandList.add(new Ping());
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

    public void help(String possibleCommand) {
        HelpEmbed helpEmbed = new HelpEmbed(possibleCommand);
        helpEmbed.help();
    }

    public void run() {
        this.commandToRun.execute(this.event);
    }
}
