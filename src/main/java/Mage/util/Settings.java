package Mage.util;

/**
 * Created by Aidil on 2017-01-14.
 */
public class Settings {
    private String botToken = "";
    private static final String AUTHOR = "78905029032947712";
    private static final String VERSION = "1.0.1";
    private String path = "";

    public String getBotToken(){
        return botToken;
    }

    public void setBotToken(String botToken){
        this.botToken = botToken;
    }

    public String getAuthor() {
        return AUTHOR;
    }

    public String getAuthorAsMention() {
        return "<@" + AUTHOR + ">";
    }

    public String getVersion() {
        return VERSION;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
