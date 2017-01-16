package Mage.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Aidil on 2017-01-13.
 */
public abstract class Command extends ListenerAdapter {
    public abstract void onCommand(String[] Args, MessageReceivedEvent e);
    public abstract List<String> getAliases();
    public abstract String getDescription();
    public abstract String getName();
    public abstract List<String> getUsageInfo();

    @Override
    public void onMessageReceived(MessageReceivedEvent e){
        if (e.getAuthor().isBot() && ignoreBots()){
            return;
        }
        if (containsCommand(e.getMessage())) onCommand(commandArgs(e.getMessage()), e);
    }

    protected boolean containsCommand(Message message){
        return getAliases().contains(commandArgs(message)[0].toLowerCase());
    }

    protected String[] commandArgs(Message message){
        return commandArgs(message.getContent());
    }

    protected String[] commandArgs(String string){
        return string.split(" ");
    }

    protected void sendMessage(Message m, MessageReceivedEvent e){
        if (e.isFromType(ChannelType.PRIVATE)) {
            e.getPrivateChannel().sendMessage(m).queue();
        } else {
            e.getTextChannel().sendMessage(m).queue();
        }
    }

    protected void sendMessage(String s, MessageReceivedEvent e){
        if (e.isFromType(ChannelType.PRIVATE)) {
            e.getPrivateChannel().sendMessage(s).queue();
        } else {
            e.getTextChannel().sendMessage(s).queue();
        }
    }

    protected void sendFile(File f, MessageReceivedEvent e) throws IOException{
        if (e.isFromType(ChannelType.PRIVATE)){
            e.getPrivateChannel().sendFile(f, null).queue();
        } else {
            e.getTextChannel().sendFile(f, null).queue();
        }
    }

    protected boolean ignoreBots(){
        return true;
    }
}
