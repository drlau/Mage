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

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by Aidil on 2017-01-13.
 */
public class Mage {

    private static JDA jda;
    public static Settings settings;
    public static final int NEW_CONFIG = 2;

    public static void main(String[] args){
        SettingsManager settingsManager = new SettingsManager();
        settings = settingsManager.getSettings();

        try {
            String path = Mage.class.getProtectionDomain().getCodeSource().getLocation().getPath();

            // Additional checks for Windows
            // TODO: Linux testing
            if (path.endsWith(".jar")){
                path = (new File(path)).getParentFile().getPath();
            }
            if (!path.endsWith("/")) {
                path = path + "/";
            }
            if (path.startsWith("/")){
                path = path.substring(1, path.length());
            }
            String decodedPath = URLDecoder.decode(path, "UTF-8");
            settings.setPath(decodedPath);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }

        try {
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

            //TODO: Add music help

            jda = jdaBuilder.buildBlocking();
            jda.getPresence().setGame(Game.of("Explosion!"));

            jda.setAutoReconnect(true);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
