
import Events.Messaging;
import Events.Ready;
import Utils.KeyHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.util.logging.Logger;

public class Launcher {

    private static final Logger LOG = Logger.getLogger("Launcher");

    public static void main(String[] args) throws LoginException, InterruptedException {

        LOG.info("Starting up the bot..");

        JDA jda = JDABuilder.createDefault(KeyHandler.DISCORDKEY)
                .setActivity(Activity.competing("Minecraft."))
                .setStatus(OnlineStatus.DO_NOT_DISTURB) // Changed to DND
                .addEventListeners(new Ready())
                .addEventListeners(new Messaging())
                .build()
                .awaitReady();
    }
}
