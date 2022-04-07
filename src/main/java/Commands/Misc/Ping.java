package Commands.Misc;

import Commands.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;

public class Ping extends Command {
    private final HashMap<String, String> usage = new HashMap<>();

    public Ping() {
        addUsage();
    }

    private void addUsage() {
        usage.put("!ping", "No really, that's it.");
    }

    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public String getDescription() {
        return "Have the bot respond with a pong!";
    }

    @Override
    public HashMap<String, String> getUsage() {
        return usage;
    }

    public void execute(MessageReceivedEvent event) {
       event.getMessage().getTextChannel().sendMessage("Pong!").queue();
    }
}
