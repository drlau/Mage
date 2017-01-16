package Mage.util;

import Mage.Mage;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * Created by Aidil on 2017-01-14.
 */
public class StatusListener extends ListenerAdapter {
    public void onGuildJoin(GuildJoinEvent e) {
        String message = "Hello! I'm Mage, a bot created by " + e.getGuild().getMemberById(Mage.settings.getAuthor()).getNickname() + "!\n"
                + "I'm still in development and you can find my commands by typing !help";
        e.getGuild().getPublicChannel().sendMessage(message).queue();
    }
}