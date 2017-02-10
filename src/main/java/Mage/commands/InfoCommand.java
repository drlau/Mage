package Mage.commands;

import Mage.Mage;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Aidil on 2017-01-13.
 */
public class InfoCommand extends Command {

    @Override
    public void onCommand(String[] args, MessageReceivedEvent e){
        MessageBuilder builder = new MessageBuilder();
        builder.append("__Mage Information__\n")
                .append("    **Version**:        " + Mage.settings.getVersion() + "\n")
                .append("    **ID**:                  " + e.getJDA().getSelfUser().getId() + "\n");
                if (e.getGuild() != null) {
                    builder.append("    **Creator**:        " + e.getGuild().getMemberById(Mage.settings.getAuthor()).getEffectiveName() + "\n");
                } else { // In a private channel
                    builder.append("    **Creator**:        Violet\n");
                }
                builder.append("    **Library**:         JDA 3.0.BETA2_120\n");
        sendMessage(builder.build(), e);
    }

    @Override
    public List<String> getAliases(){
        return Arrays.asList("!info");
    }

    @Override
    public String getDescription(){
        return "Displays information about Mage.";
    }

    @Override
    public String getName(){
        return "Info";
    }

    @Override
    public List<String> getUsageInfo(){
        return Arrays.asList("!info");
    }
}
