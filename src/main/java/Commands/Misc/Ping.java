package Commands.Misc;

import Commands.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Ping extends Command {
    private final String name = "ping";
    private final String description = "Have the bot respond with a pong!";


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void execute(MessageReceivedEvent event) {
       event.getMessage().getTextChannel().sendMessage("Pong!").queue();
    }
}
