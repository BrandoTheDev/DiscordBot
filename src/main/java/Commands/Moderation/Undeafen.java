package Commands.Moderation;

import Commands.Command;
import Utils.UserRoleChecker;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;
import java.util.List;

public class Undeafen extends Command {
    private List<Member> mentionedUsers;
    private final HashMap<String, String> usage = new HashMap<>();
    private TextChannel textChannel;

    public Undeafen() {
        usage.put("!undeafen @mentionedUser", "Enables hearing in voice calls for @mentionedUser");
        usage.put("!undeafen @mentionedUser1 @mentionedUser2", "Enables hearing in voice calls for ALL @mentionedUser(s)");
    }

    private void undeafen() {
        for(Member member : mentionedUsers) {
            if(UserRoleChecker.userHasRole(member.getRoles(),"Staff")) {
                textChannel.sendMessage(member.getEffectiveName() + " is Staff and cannot be Timed out.").queue();
            } else {
                member.deafen(false).queue();
            }
        }
    }

    @Override
    public String getName() {
        return "undeafen";
    }

    @Override
    public String getDescription() {
        return "Enables hearing in voice calls.";
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
                undeafen();
            }
        }
    }
}
