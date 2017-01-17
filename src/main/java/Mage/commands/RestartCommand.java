package Mage.commands;

import Mage.Mage;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Aidil on 2017-01-13.
 */
public class RestartCommand extends Command {

    @Override
    public void onCommand(String[] args, MessageReceivedEvent e){
        if (e.isFromType(ChannelType.PRIVATE) && e.getAuthor().getId().equals(Mage.settings.getAuthor())){
            sendMessage("Restarting...", e);
            try {
                restart(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void restart(MessageReceivedEvent e) throws InterruptedException, UnsupportedEncodingException {
        String[] command = new String[] {"java", "-jar", Mage.getJar().getAbsolutePath(), e.getChannel().getId()};

        try {
            Runtime.getRuntime().exec(command);
            System.exit(Mage.RESTART);
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public List<String> getAliases(){
        return Arrays.asList("!restart");
    }

    @Override
    public String getDescription(){
        return "Restarts Mage. Author only.";
    }

    @Override
    public String getName(){
        return "Restart";
    }

    @Override
    public List<String> getUsageInfo(){
        return Arrays.asList("!restart");
    }
}
