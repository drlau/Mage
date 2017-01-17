package Mage.commands;

import Mage.Mage;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Aidil on 2017-01-13.
 */
public class KillCommand extends Command {

    @Override
    public void onCommand(String[] args, MessageReceivedEvent e){
        if (e.isFromType(ChannelType.PRIVATE) && e.getAuthor().getId().equals(Mage.settings.getAuthor())){
            sendMessage("Shutting down...", e);
            System.exit(Mage.KILL);
        }
    }

    @Override
    public List<String> getAliases(){
        return Arrays.asList("!kill");
    }

    @Override
    public String getDescription(){
        return "Kills Mage. Author only.";
    }

    @Override
    public String getName(){
        return "Kill";
    }

    @Override
    public List<String> getUsageInfo(){
        return Arrays.asList("!kill");
    }
}
