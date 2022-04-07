package Commands.Misc;

import Commands.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;

public class Ping extends Command {
    private final String name = "ping";
    private final String description = "Have the bot respond with a pong!";

    private final HashMap<String, String> usage = new HashMap<>();

    private void addUsage() {
        usage.put("!ping", "No really, that's it.");
    }

    public Ping() {
        addUsage();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public HashMap<String, String> getUsage() {
        return usage;
    }

    public void execute(MessageReceivedEvent event) {
       event.getMessage().getTextChannel().sendMessage("Pong!").queue();
    }
}
