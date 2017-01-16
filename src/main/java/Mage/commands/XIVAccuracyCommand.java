package Mage.commands;

import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Aidil on 2017-01-14.
 */
public class XIVAccuracyCommand extends Command {

    private HashMap<String, int[]> caps = new HashMap<>();
    //Front, Flank, Caster

    public XIVAccuracyCommand(){
        caps.put("a1s", new int[]{595, 565, 495});
        caps.put("a2s", new int[]{609, 575, 520});
        caps.put("a3s", new int[]{635, 610, 530});
        caps.put("a4s", new int[]{647, 620, 540});
        caps.put("a5s", new int[]{660, 640, 560});
        caps.put("a6s", new int[]{676, 650, 575});
        caps.put("a7s", new int[]{688, 670, 590});
        caps.put("a8s", new int[]{699, 682, 593});
        caps.put("a9s", new int[]{699, 682, 593});
        caps.put("a10s", new int[]{699, 682, 593});
        caps.put("a11s", new int[]{699, 682, 593});
        caps.put("a12s", new int[]{699, 682, 593});
        caps.put("bisex", new int[]{595, 565, 495});
        caps.put("ravex", new int[]{595, 565, 495});
        caps.put("thorex", new int[]{609, 575, 520});
        caps.put("sephex", new int[]{647, 620, 540});
        caps.put("nidex", new int[]{676, 650, 575});
        caps.put("sophex", new int[]{699, 682, 593});
    }


    @Override
    public void onCommand(String[] args, MessageReceivedEvent e){
        if (args.length == 1) {
            return;
        }

        if (caps.containsKey(args[1])){
            int[] acc = caps.get(args[1]);
            MessageBuilder m = new MessageBuilder();
            m.append("Accuracy for " + args[1] + ":\n")
                    .append("Front: **" + acc[0] + "**\n")
                    .append("Flank: **" + acc[1] + "**\n")
                    .append("Caster: **" + acc[2] + "**");
            sendMessage(m.build(), e);
        } else {
            sendMessage("Fight not found.", e);
        }
    }

    @Override
    public List<String> getAliases(){
        return Arrays.asList("!accuracy", "!acc");
    }

    @Override
    public String getDescription(){
        return "Displays the accuracy cap for a particular fight in FFXIV.";
    }

    @Override
    public String getName(){
        return "Accuracy";
    }

    @Override
    public List<String> getUsageInfo(){
        return Arrays.asList("!accuracy *<fight>*  **OR** !acc *<fight>*");
    }
}
