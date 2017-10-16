package core;

import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.STATIC;

import java.util.Arrays;


public class permsCore {

    public static boolean check(MessageReceivedEvent event, String[] perms) {
        for(Role role : event.getGuild().getMember(event.getAuthor()).getRoles()) {
            if(Arrays.stream(perms).parallel().anyMatch(role.getName()::contains)) {
                return true;
            }
        }
        event.getTextChannel().sendMessage(":warning: Sorry , " + event.getAuthor().getAsMention() + " you dont have the permissions to use this command!").queue();
        return false;
    }

}
