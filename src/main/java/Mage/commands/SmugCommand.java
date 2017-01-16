package Mage.commands;

import Mage.Mage;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by Aidil on 2017-01-14.
 */
public class SmugCommand extends Command {

    private Random random = new Random();

    @Override
    public void onCommand(String[] args, MessageReceivedEvent e){
        try {
            List<File> imgs = Files.walk(Paths.get(Mage.settings.getPath() + "img/smug/"))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
            for (Iterator<File> iterator = imgs.iterator(); iterator.hasNext();) {
                File file = iterator.next();
                if (file.isHidden() || file.getName().startsWith(".")){
                    iterator.remove();
                }
            }
            if (imgs.size() == 0){
                throw new IOException();
            }
            int r = random.nextInt(imgs.size());
            sendFile(imgs.get(r), e);
        } catch (IOException ex) {
            sendMessage("Something went wrong while fetching the file!", e);
        }
    }

    @Override
    public List<String> getAliases(){
        return Arrays.asList("!smug");
    }

    @Override
    public String getDescription(){
        return "Sends a smug picture.";
    }

    @Override
    public String getName(){
        return "Smug";
    }

    @Override
    public List<String> getUsageInfo(){
        return Arrays.asList("!smug");
    }
}
