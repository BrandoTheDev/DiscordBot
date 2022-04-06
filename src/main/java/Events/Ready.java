package Events;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Ready extends ListenerAdapter {

    @Override
    public void onReady(ReadyEvent event) {
        int guildTotal = event.getGuildTotalCount();
        int guildAvailable = event.getGuildAvailableCount();
        int guildUnavailable = event.getGuildUnavailableCount();
        String guildReport = String.format("Total Guilds: %s\nTotal Available: %s\nTotal Unavailable: %s",
                guildTotal, guildAvailable, guildUnavailable);
        System.out.println(guildReport);
    }

}
