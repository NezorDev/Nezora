package listeners;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class readyListener extends ListenerAdapter {
    public void onReady(ReadyEvent event) {
        String out = "\nThis bot is running on following servers: \n";
        for (Guild guild : event.getJDA().getGuilds()) {
            out += guild.getName() + " (" + guild.getId() + ") \n";
        }

        System.out.println(out);

        for (Guild guild : event.getJDA().getGuilds()) {
            guild.getTextChannelsByName("botlog", true).get(0).
                    sendMessage("CORE: Nezora: Started").queue();
        }
    }
}
