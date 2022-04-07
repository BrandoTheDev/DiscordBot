package Events;

import Commands.CommandFinder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Messaging extends ListenerAdapter {

    private final CommandFinder commandFinder = new CommandFinder();

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event){
        // Our bot command prefix
        String prefix = "!";

        // Make sure the sender is not a bot
        if(!event.getAuthor().isBot()) {

            // Make sure the message starts with command prefix
            String msg = event.getMessage().getContentDisplay().toLowerCase();
            if(msg.startsWith(prefix)) {

                // Split message into array
                ArrayList<String> messageArray = new ArrayList<>();
                Collections.addAll(messageArray, msg.split(" "));

                // Remove prefix from command
                String possibleCommand = messageArray.get(0).replace(prefix, "");

                // Check if user wants help
                if(possibleCommand.equals("help")) {

                    if(messageArray.size() <= 1) {
                        // If no command was given, show a list of commands available
                        commandFinder.setEvent(event);
                        commandFinder.listCommands();
                    }
                    else {
                        // Check if 2nd arg is valid command
                        String possibleHelpCommand = messageArray.get(1);
                        if(commandFinder.isCommand(possibleHelpCommand)) {

                            // Command is valid. Set our event to use
                            commandFinder.setEvent(event);

                            // Call our Help to show command info
                            commandFinder.help();
                        }
                    }
                    // Check if command is valid
                } else if(commandFinder.isCommand(possibleCommand)) {

                    // Command is valid. Set our event to use
                    commandFinder.setEvent(event);

                    // Run the command!
                    commandFinder.run();
                }
            }
        }
    }
}
