package Mage.commands;

import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Aidil on 2017-01-14.
 */
public class AutoGreenCommand extends Command {

    private boolean enabled = false;

    @Override
    public void onMessageReceived(MessageReceivedEvent e){
        //TODO: guild specific setting
        if (e.getAuthor().isBot() && ignoreBots()){
            return;
        }
        if (e.getMessage().getContent().startsWith(">") && enabled &&
                !e.getMessage().isFromType(ChannelType.PRIVATE)
                && e.getMessage().getContent().length() > 1) autoGreen(e);
        if (containsCommand(e.getMessage())) onCommand(commandArgs(e.getMessage()), e);
    }

    @Override
    public void onCommand(String[] args, MessageReceivedEvent e){
        if (e.isFromType(ChannelType.PRIVATE)) return;
        if (args.length == 2){
            if (args[1].equalsIgnoreCase("true") || args[1].equalsIgnoreCase("on")) {
                enabled = true;
                e.getTextChannel().sendMessage("AutoGreen has been enabled.").queue();
            } else if (args[1].equalsIgnoreCase("false") || args[1].equalsIgnoreCase("off")){
                enabled = false;
                e.getTextChannel().sendMessage("AutoGreen has been disabled.").queue();
            }
        }
    }

    @Override
    public List<String> getAliases(){
        return Arrays.asList("!autogreen");
    }

    @Override
    public String getDescription(){
        return "Mage creates a greentext version of non-greentext'd text, if that makes sense.";
    }

    @Override
    public String getName(){
        return "AutoGreen";
    }

    @Override
    public List<String> getUsageInfo(){
        return Arrays.asList("!autogreen *<on/off>*");
    }

    private void autoGreen(MessageReceivedEvent e){
        e.getTextChannel().sendMessage(new MessageBuilder()
                .append(e.getAuthor())
                .append(" :\n")
                .append("```css\n")
                .append(e.getMessage().getContent())
                .append("\n```")
                .build()).queue();
    }
}

