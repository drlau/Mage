package Mage.commands;

import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Aidil on 2017-01-15.
 */
public class XIVWeightCommand  extends Command {

    private HashMap<String, ArrayList<String>> weights = new HashMap<>();
    private String patch = "3.4";

    public XIVWeightCommand(){
        weights.put("PLD", new ArrayList<String>(){{
            add("[WPD](26.523)");
            add("[STR](1.000)");
            add("[VIT](1.000)");
            add("[DET](0.396)");
            add("[CRT](0.541)");
            add("[Sw/Oath SKS](0.274)");
            add("[Sh/Oath SKS](0.330)");
        }});
        weights.put("WAR", new ArrayList<String>(){{
            add("[WPD](27.095)");
            add("[STR](1.000)");
            add("[VIT](1.000)");
            add("[DET](0.387)");
            add("[CRT](0.546)");
            add("[Min SKS for 3x Fell](550)");
            add("[SKS After Min](0.350)");
        }});
        weights.put("DRK", new ArrayList<String>(){{
            add("[WPD](26.547)");
            add("[STR](1.000)");
            add("[VIT](1.000)");
            add("[DET](0.380)");
            add("[CRT](0.530)");
            add("[SKS](0.315)");
            add("[PAR](0.011)");
        }});
        weights.put("DRG", new ArrayList<String>(){{
            add("[WPD](14.956)");
            add("[STR](1.000)");
            add("[DET](0.207)");
            add("[CRT](0.279)");
            add("[SKS](0.170)");
        }});
        weights.put("MNK", new ArrayList<String>(){{
            add("[WPD](15.459)");
            add("[STR](1.000)");
            add("[DET](0.212)");
            add("[CRT](0.279)");
            add("[SKS](0.173)");
        }});
        weights.put("NIN", new ArrayList<String>(){{
            add("[WPD](15.195)");
            add("[DEX](1.000)");
            add("[DET](0.210)");
            add("[CRT](0.282)");
            add("[SKS](0.141)");
        }});
        weights.put("BRD", new ArrayList<String>(){{
            add("[WPD](16.824)");
            add("[DEX](1.000)");
            add("[DET](0.215)");
            add("[CRT](0.366)");
            add("[SKS](0.233)");
        }});
        weights.put("BLM", new ArrayList<String>(){{
            add("[WPD](11.884)");
            add("[DEX](1.000)");
            add("[DET](0.206)");
            add("[CRT](0.256)");
            add("[SPS](0.283)");
        }});
        weights.put("SMN", new ArrayList<String>(){{
            add("[WPD](15.195)");
            add("[DEX](1.000)");
            add("[DET](0.192)");
            add("[CRT](0.287)");
            add("[SPS](0.195)");
        }});
    }

    @Override
    public void onCommand(String[] args, MessageReceivedEvent e){
        if (args.length == 1) {
            return;
        }

        if (weights.containsKey(args[1].toUpperCase())){
            ArrayList<String> a = weights.get(args[1].toUpperCase());
            MessageBuilder m = new MessageBuilder();
            m.append("Stat weights for **").append(args[1].toUpperCase())
                    .append("** for patch ").append(patch).append(":\n").append("```md\n");
            for (String s : a){
                m.append(s).append("\n");
            }
            m.replaceLast("\n", "");
            m.append("```");
            sendMessage(m.build(), e);
        } else {
            sendMessage("No data :(", e);
        }
    }

    @Override
    public List<String> getAliases(){
        return Arrays.asList("!sweight");
    }

    @Override
    public String getDescription(){
        return "Displays the stat weights of the specified class in FFXIV.";
    }

    @Override
    public String getName(){
        return "Stat Weight";
    }

    @Override
    public List<String> getUsageInfo(){
        return Arrays.asList("!sweight *<3 letter class name>*");
    }
}