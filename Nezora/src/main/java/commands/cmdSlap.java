package commands;

import at.mukprojects.giphy4j.Giphy;
import at.mukprojects.giphy4j.entity.search.SearchRandom;
import at.mukprojects.giphy4j.exception.GiphyException;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.SECRETS;
import util.STATIC;

public class cmdSlap implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if(args.length < 1) {
            event.getTextChannel().sendMessage("Argument missing: ```" + STATIC.PREFIX + "slap @user```").queue();
            return;
        }

        if(!args[0].startsWith("@")) {
            event.getTextChannel().sendMessage("Argument wrong: ```" + STATIC.PREFIX + "slap @user```").queue();
            return;
        }
        for(Member member: event.getTextChannel().getMembers()) {
            if("@".concat(member.getUser().getName()).equals(args[0])) {
                event.getTextChannel().sendMessage(event.getAuthor().getAsMention() + " slapped " + member.getAsMention()).queue();
                Giphy giphy = new Giphy(SECRETS.GIPHYKEY);
                try {
                    SearchRandom giphyData = giphy.searchRandom("slapping");
                    System.out.println(giphyData.getData().getType());
                    event.getTextChannel().sendMessage(giphyData.getData().getImageOriginalUrl()).queue();
                    event.getMessage().delete().queue();
                } catch (GiphyException e) {
                    System.out.println(e.getMessage());
                    event.getGuild().getTextChannelsByName("botlog", true).get(0).sendMessage(e.getMessage()).queue();
                }

            }
        }
    }

    @Override
    public void executed(boolean sucess, MessageReceivedEvent event) {
        event.getGuild().getTextChannelsByName("botlog", true).get(0).sendMessage("CMD: hug used by " + event.getMessage().getMember().getUser().getName()).queue();
        System.out.println("CMD: slap  used by " + event.getMessage().getMember().getUser().getName());
    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public String[] perms() {
        return new String[] {"Royality", "Wächter", "Owner"};
    }
}
