package Commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;

public abstract class Command {

    public abstract String getName();
    public abstract String getDescription();
    public abstract HashMap<String, String> getUsage();
    public abstract void execute(MessageReceivedEvent event);
}
