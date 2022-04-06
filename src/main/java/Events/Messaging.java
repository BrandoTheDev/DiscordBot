package Events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Messaging extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        System.out.println("Message received!");
    }
}
