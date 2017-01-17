package Mage.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Aidil on 2017-01-13.
 */
public class MeowCommand extends Command {

    @Override
    public void onCommand(String[] args, MessageReceivedEvent e){
        try {
            InputStream is = new URL("http://random.cat/meow").openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            sendMessage(json.getString("file"), e);
        } catch (Exception ex) {
            sendMessage("Something went wrong while fetching the picture!", e);
            ex.printStackTrace();
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    @Override
    public List<String> getAliases(){
        return Arrays.asList("!meow");
    }

    @Override
    public String getDescription(){
        return "Sends a random cat picture.";
    }

    @Override
    public String getName(){
        return "Meow";
    }

    @Override
    public List<String> getUsageInfo(){
        return Arrays.asList("!meow");
    }
}
