package Mage.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Aidil on 2017-01-13.
 */
public class PingCommand extends Command {

    @Override
    public void onCommand(String[] args, MessageReceivedEvent e){
        sendMessage("Pong", e);
    }

    @Override
    public List<String> getAliases(){
        return Arrays.asList("!ping");
    }

    @Override
    public String getDescription(){
        return "Ping Mage to check if it's online(responds with Pong).";
    }

    @Override
    public String getName(){
        return "Ping";
    }

    @Override
    public List<String> getUsageInfo(){
        return Arrays.asList("!ping");
    }
}
