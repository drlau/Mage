package Mage;

import Mage.commands.*;
import Mage.music.PlayerControl;
import Mage.util.Settings;
import Mage.util.SettingsManager;
import Mage.util.StatusListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by Aidil on 2017-01-13.
 */
public class Mage {

    private static JDA jda;
    public static Settings settings;
    //TODO: Getters and setters for settings

    public static final int NEW_CONFIG = 2;
    public static final int KILL = 10;
    public static final int RESTART = 11;

    public static void main(String[] args){
        SettingsManager settingsManager = new SettingsManager();
        settings = settingsManager.getSettings();
        try {
            settings.setPath(getPath());
            JDABuilder jdaBuilder = new JDABuilder(AccountType.BOT).setToken(settings.getBotToken());
            HelpCommand help = new HelpCommand();

            jdaBuilder.addListener(new PlayerControl());
            jdaBuilder.addListener(new StatusListener());

            jdaBuilder.addListener(help.addCommand(new XIVAccuracyCommand()));
            jdaBuilder.addListener(help.addCommand(new AutoGreenCommand()));
            jdaBuilder.addListener(help.addCommand(help));
            jdaBuilder.addListener(help.addCommand(new InfoCommand()));
            jdaBuilder.addListener(help.addCommand(new PingCommand()));
            jdaBuilder.addListener(help.addCommand(new RollCommand()));
            jdaBuilder.addListener(help.addCommand(new SmugCommand()));
            jdaBuilder.addListener(help.addCommand(new UptimeCommand()));
            jdaBuilder.addListener(help.addCommand(new XIVWeightCommand()));
            jdaBuilder.addListener(help.addCommand(new GQCommand()));
            jdaBuilder.addListener(new KillCommand());
            jdaBuilder.addListener(new RestartCommand());
            jdaBuilder.addListener(help.addCommand(new MeowCommand()));
            jdaBuilder.addListener(help.addCommand(new FriendshipCommand()));

            //TODO: Add music help

            jda = jdaBuilder.buildBlocking();
            jda.getPresence().setGame(Game.of("Explosion!"));

            if (args.length == 1) {
                jda.getPrivateChannelById(args[0]).sendMessage("Restart complete!").queue();
            }

            jda.setAutoReconnect(true);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static String getPath() throws UnsupportedEncodingException {
        String path = Mage.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        // Additional checks for Windows
        // TODO: Linux testing
        if (path.endsWith(".jar")){
            path = (new File(path)).getParentFile().getPath();
        }
        if (!path.endsWith("/")) {
            path = path + "/";
        }
        if (path.startsWith("/") && (System.getProperty("os.name").contains("Windows"))){
            path = path.substring(1, path.length());
        }
        return URLDecoder.decode(path, "UTF-8");
    }

    public static File getJar() throws UnsupportedEncodingException {
        String path = Mage.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decoded = URLDecoder.decode(path, "UTF-8");

        // Additional checks for Windows
        // TODO: Linux testing
        if (!decoded.endsWith(".jar"))
        {
            return new File("Mage.jar");
        }
        return new File(decoded);
    }

//    public Settings getSettings(){
//        return settings;
//    }
}
