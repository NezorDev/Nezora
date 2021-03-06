package core;

import commands.cmdHug;
import commands.cmdPing;
import commands.cmdSlap;
import listeners.commandListener;
import listeners.readyListener;
import listeners.voiceListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import util.SECRETS;
import util.STATIC;

import javax.security.auth.login.LoginException;

public class main {

    public static JDABuilder builder;

    public static void main(String[] Args) {
        builder = new JDABuilder(AccountType.BOT);

        builder.setToken(SECRETS.TOKEN);
        builder.setAutoReconnect(true);

        builder.setStatus(OnlineStatus.ONLINE);

        builder.setGame(Game.of("v" + STATIC.VERSION));


        addListener();
        addCommands();

        try {
            JDA jda = builder.buildBlocking();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RateLimitedException e) {
            e.printStackTrace();
        }
    }

    private static void addCommands() {
        commandHandler.commands.put("ping", new cmdPing());
        commandHandler.commands.put("hug", new cmdHug());
        commandHandler.commands.put("slap", new cmdSlap());

    }


    private static void addListener() {
        builder.addEventListener(new readyListener());
        builder.addEventListener(new voiceListener());
        builder.addEventListener(new commandListener());

    }
}
