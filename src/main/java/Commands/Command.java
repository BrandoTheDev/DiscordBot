package Commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Command {

    public abstract String getName();
    public abstract String getDescription();
    public abstract void execute(MessageReceivedEvent event);
}
