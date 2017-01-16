package Mage.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Mage.Mage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Aidil on 2017-01-15.
 */
public class SettingsManager {
    private static SettingsManager instance;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Settings settings;
    private final Path configFile = new File(".").toPath().resolve("Config.json");

    public static SettingsManager getInstance() {
        if (instance == null) {
            instance = new SettingsManager();
        }
        return instance;
    }

    public SettingsManager() {
        if (!configFile.toFile().exists()) {
            System.out.println("SettingsManager: Creating default settings");
            System.out.println("SettingsManager: You will need to edit the Config.json with your login information.");
            this.settings = getDefaultSettings();
            saveSettings();
            System.exit(Mage.NEW_CONFIG);
        }
        loadSettings();
    }

    public void loadSettings() {
        try {
            checkBadEscapes(configFile);

            BufferedReader reader = Files.newBufferedReader(configFile, StandardCharsets.UTF_8);
            this.settings = gson.fromJson(reader, Settings.class);
            reader.close();
            System.out.println("SettingsManager: Settings loaded");
            checkOldSettingsFile();
        } catch (IOException e) {
            System.out.println("SettingsManager: Error Loading Settings");
            e.printStackTrace();
        }
    }

    public Settings getSettings() {
        return settings;
    }

    public void saveSettings() {
        String jsonOut = gson.toJson(this.settings);
        try {
            BufferedWriter writer = Files.newBufferedWriter(configFile, StandardCharsets.UTF_8);
            writer.append(jsonOut);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Settings getDefaultSettings() {
        Settings newSettings = new Settings();
        newSettings.setBotToken("");
        return newSettings;
    }

    private void checkOldSettingsFile()
    {
        boolean modified = false;
        Settings defaults = getDefaultSettings();
        if (settings.getBotToken() == null)
        {
            settings.setBotToken(defaults.getBotToken());
            modified = true;
        }

        if (modified)
            saveSettings();
    }

    private void checkBadEscapes(Path filePath) throws IOException
    {
        final byte FORWARD_SOLIDUS = 47;    //  /
        final byte BACKWARDS_SOLIDUS = 92;  //  \

        boolean modified = false;
        byte[] bytes = Files.readAllBytes(filePath);
        for (int i = 0; i < bytes.length; i++)
        {
            if (bytes[i] == BACKWARDS_SOLIDUS)
            {
                modified = true;
                bytes[i] = FORWARD_SOLIDUS;
            }
        }

        if (modified)
        {
            Files.write(filePath, bytes);
        }
    }
}