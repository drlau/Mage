package Mage.commands;

import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Created by Aidil on 2017-01-14.
 */
public class HelpCommand extends Command {

    private final String NO_NAME = "No name found";
    private final String NO_DESC = "No description found.";
    private final String NO_USAGE = "No usage instructions found.";
    private TreeMap<String, Command> commands;

    public HelpCommand(){
        commands = new TreeMap<>();
    }

    public Command addCommand(Command command){
        commands.put(command.getAliases().get(0), command);
        return command;
    }

    @Override
    public void onCommand(String[] args, MessageReceivedEvent e){
        if (args.length == 1) {
            if(!e.isFromType(ChannelType.PRIVATE)) {
                e.getAuthor().openPrivateChannel().complete();
                e.getTextChannel().sendMessage(new MessageBuilder()
                        .append(e.getAuthor())
                        .append(", I sent you a private message!")
                        .build()).queue();
            }
            sendPrivate(e.getAuthor().getPrivateChannel());
        } else if (args.length == 2) {
            String command = args[1].charAt(0) == '!' ? args[1] : "!" + args[1];
            for (Command c:commands.values()){
                if (c.getAliases().contains(command)){
                    String name = c.getName();
                    String desc = c.getDescription();
                    List<String> usage = c.getUsageInfo();
                    name = (name == null || name.isEmpty()) ? NO_NAME : name;
                    desc = (desc == null || desc.isEmpty()) ? NO_DESC : desc;
                    usage = (usage == null || usage.isEmpty()) ? Collections.singletonList(NO_USAGE) : usage;

                    sendMessage(new MessageBuilder()
                            .append("**Name:** " + name + "\n")
                            .append("**Description:** " + desc + "\n")
                            .append("**Aliases:** " + StringUtils.join(c.getAliases(), ", ") + "\n")
                            .append("**Usage:** ")
                            .append(usage.get(0)).build(), e);
                }
            }
        }
    }

    @Override
    public List<String> getAliases(){
        return Arrays.asList("!help", "!commands");
    }

    @Override
    public String getDescription(){
        return "Helps you use other commands.";
    }

    @Override
    public String getName(){
        return "Help";
    }

    @Override
    public List<String> getUsageInfo(){
        return Arrays.asList("!help  **OR** !help *<command>*");
    }

    private void sendPrivate(PrivateChannel p){
        StringBuilder s = new StringBuilder();
        s.append("```md\n");
        for (Command c : commands.values()){
            String desc = c.getDescription();
            desc = (desc == null || desc.isEmpty()) ? NO_DESC : desc;

            s.append("[").append(c.getAliases().get(0)).append("](").append(desc).append(")\n");
        }

        p.sendMessage(new MessageBuilder().append("Hello! Here's what I can do!\n")
                .append(s.toString()).append("```").build()).queue();
    }
}