package commands;

import core.permsCore;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class cmdPing implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if(!permsCore.check(event, this.perms())) {
            return;
        }
        event.getTextChannel().sendMessage("Pong!").queue();
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        event.getGuild().getTextChannelsByName("botlog", true).get(0).sendMessage("CMD: ping used by " + event.getMessage().getMember().getUser().getName()).queue();
        System.out.println("CMD: ping used by " + event.getMessage().getMember().getUser().getName());
    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public String[] perms() {return new String[] {"Wächter", "Owner", "Royality", "Bot"};}
}
