package Mage.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Aidil on 2017-01-13.
 */
public class RollCommand extends Command {
    private final Random random = new Random();

    @Override
    public void onCommand(String[] args, MessageReceivedEvent e){
        int roll;
        int lower = 1;
        int upper = 99;

        if (args.length == 2){
            try {
                upper = Integer.parseInt(args[1]);
            } catch (NumberFormatException ex) {
                sendMessage("Provided upper bound **" + args[1] + "** is not an integer!", e);
                return;
            }
        } else if (args.length == 3) {
            try {
                lower = Integer.parseInt(args[1]);
            } catch (NumberFormatException ex) {
                sendMessage("Provided lower bound **" + args[1] + "** is not an integer!", e);
                return;
            }

            try {
                upper = Integer.parseInt(args[2]);
            } catch (NumberFormatException ex) {
                sendMessage("Provided upper bound **" + args[2] + "** is not an integer!", e);
                return;
            }
        }

        if (lower > upper){
            int temp = lower;
            lower = upper;
            upper = temp;
        } else if (lower == upper) {
            sendMessage("You roll " + lower + "!", e);
            return;
        } else if (upper - lower < 0) {
            sendMessage("Invalid inputs! Upper - lower must be positive.", e);
            return;
        }

        roll = random.nextInt(upper - lower) + lower;
        sendMessage("You roll " + roll + "!", e);
    }

    @Override
    public List<String> getAliases(){
        return Arrays.asList("!roll");
    }

    @Override
    public String getDescription(){
        return "Rolls a number between the lower and upper inputs. Defaults to 1-99.";
    }

    @Override
    public String getName(){
        return "Roll";
    }

    @Override
    public List<String> getUsageInfo(){
        return Arrays.asList("!roll [lower] [upper]");
    }
}
