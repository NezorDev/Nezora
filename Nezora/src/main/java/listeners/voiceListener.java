package listeners;

import net.dv8tion.jda.core.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class voiceListener extends ListenerAdapter {
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
        event.getGuild().getTextChannelsByName("botlog", true).get(0).sendMessage(
                "VOICE: Member " + event.getVoiceState().getMember().getUser().getName() + " joined " + event.getChannelJoined().getName()
        ).queue();
    }

    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
        event.getGuild().getTextChannelsByName("botlog", true).get(0).sendMessage(
                "VOICE: Member " + event.getVoiceState().getMember().getUser().getName() + " leaved " + event.getChannelLeft().getName()
        ).queue();
    }
}
