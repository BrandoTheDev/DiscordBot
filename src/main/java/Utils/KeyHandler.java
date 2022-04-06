package Utils;

/**
 * This class will be used for storing, retrieving and modifying keys for APIs
 */

public class KeyHandler {
    // Our messaging platform of choice. Others are irc, slack, twitch.tv
    // Get your discord token at: https://discord.com/developers/applications/INSERT-YOUR-APP-ID-HERE/bot
    private String discordKey = "ODM3NzI4NzAyMTEyNTk2MDA4.YIwxpg.A2BICAja_LTJazLEfykgnUJtMi8";

    public String getDiscordKey() {
        return discordKey;
    }

    public void setDiscordKey(String key) {
        this.discordKey = key;
    }
}
