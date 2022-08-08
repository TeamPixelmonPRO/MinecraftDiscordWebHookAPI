package ex.qaz.mdwhapi.utils.webhooks;

import scala.util.parsing.json.JSONObject;

import java.awt.*;
import java.io.IOException;

public class WebHookEmptyFile {
    public static String getWebHookEmptyFileJson() throws IOException {
        DiscordWebhook wh = new DiscordWebhook();
        DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject();
        wh.setAvatarUrl("WebHook Avatar URL");
        wh.setTts(false);
        wh.setContent("WebHook main content");
        wh.setUsername("WebHook username");
        embed.addField("Embed's Field1 name","Embed's Field1 value",false);
        embed.setAuthor("Embed's Author name","Embed's Author url(What was open when user click on this text)","Icon url");
        embed.setColor(new Color(255,255,255));
        embed.setDescription("Embed description");
        embed.setFooter("Embed footer text","Embed footer icon url");
        embed.setImage("Embed image url");
        embed.setThumbnail("Embed thumbnail url");
        embed.setTimestamp("Embed timestamp");
        embed.setTitle("Embed title text");
        wh.addEmbed(embed);
        return wh.getJson();
    }
}
