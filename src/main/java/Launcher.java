import Utils.KeyHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Launcher {

    public static void main(String[] args) throws LoginException {

        JDA jda = JDABuilder.createDefault(KeyHandler.discordKey)
                .build();
    }
}
