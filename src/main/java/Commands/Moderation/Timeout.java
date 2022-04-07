package Commands.Moderation;

import Commands.Command;
import Utils.UserRoleChecker;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class Timeout extends Command {
    private List<Member> mentionedUsers;
    private final HashMap<String, String> usage = new HashMap<>();
    private TextChannel textChannel;

    public Timeout() {
        usage.put("!timeout @mentionedUser", "Disables participating in every channel for @mentionedUser");
        usage.put("!timeout @mentionedUser1 @mentionedUser2", "Disables participating in every channel for ALL @mentionedUser(s)");
    }

    private void timeout() {
        for(Member member : mentionedUsers) {
            if(UserRoleChecker.userHasRole(member.getRoles(),"Staff")) {
                textChannel.sendMessage(member.getEffectiveName() + " is Staff and cannot be Timed out.").queue();
            } else {
                if(member.isTimedOut()) {
                    member.removeTimeout().queue();
                } else {
                    member.timeoutFor(Duration.ofSeconds(60)).queue();
                }
            }
        }
    }

    @Override
    public String getName() {
        return "timeout";
    }

    @Override
    public String getDescription() {
        return "Disables participating in every channel!";
    }

    @Override
    public HashMap<String, String> getUsage() {
        return usage;
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        Member member = event.getMember();
        List<Role> roles = event.getAuthor().getJDA().getRoles();
        textChannel = event.getTextChannel();

        if(UserRoleChecker.isUserAllowedOrOwner(member, roles, "Staff")) {
            if(event.getMessage().getMentionedMembers().isEmpty()) {
                event.getMessage().getTextChannel().sendMessage("Please @mention a user").queue();
            } else {
                mentionedUsers = event.getMessage().getMentionedMembers();
                timeout();
            }
        }
    }
}
