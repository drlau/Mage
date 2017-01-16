package Mage.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.lang.management.ManagementFactory;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Aidil on 2017-01-15.
 */
public class UptimeCommand extends Command {

    @Override
    public void onCommand(String[] args, MessageReceivedEvent e){
        final long duration = ManagementFactory.getRuntimeMXBean().getUptime();

        final long years = duration / 31104000000L;
        final long months = duration / 2592000000L % 12;
        final long days = duration / 86400000L % 30;
        final long hours = duration / 3600000L % 24;
        final long minutes = duration / 60000L % 60;
        final long seconds = duration / 1000L % 60;

        String uptime = (years == 0 ? "" : "**" + years + "** Years, ")
                + (months == 0 ? "" : "**" + months + "** Month" + (months == 1 ? ", ": "s, "))
                + (days == 0 ? "" : "**" + days + "** Day" + (days == 1 ? ", ": "s, "))
                + (hours == 0 ? "" : "**" + hours + "** Hour" + (hours == 1 ? ", ": "s, "))
                + (minutes == 0 ? "" : "**" + minutes + "** Minute" + (minutes == 1 ? ", ": "s, "))
                + (seconds == 0 ? "" : "**" + seconds + "** Second" + (seconds == 1 ? ", ": "s, "));

        uptime = replaceLast(uptime, ", ", "");
        uptime = replaceLast(uptime, ",", " and");

        sendMessage("I've been online for:\n" + uptime, e);
    }

    @Override
    public List<String> getAliases(){
        return Arrays.asList("!uptime");
    }

    @Override
    public String getDescription(){
        return "Displays how long Mage has been up.";
    }

    @Override
    public String getName(){
        return "Uptime";
    }

    @Override
    public List<String> getUsageInfo(){
        return Arrays.asList("!uptime");
    }

    private String replaceLast(final String text, final String regex, final String replacement) {
        return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
    }
}