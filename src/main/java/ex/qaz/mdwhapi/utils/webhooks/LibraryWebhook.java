/*package ex.qaz.mdwhapi.utils.webhooks;

import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class LibraryWebhook {
    public static String Mods = "https://discord.com/api/webhooks/997190339540570262/PYsh5f0o7UX9uLeFhk9hVcVt-qNylNZwxJZur_aS6opXh-o06W5RBbKpVIoCFwWK_QjX";
    public static void sendResourcepacks(String msg) {
        String nick = Minecraft.getMinecraft().getSession().getUsername();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        DiscordWebhook discordWebhook = new DiscordWebhook(Mods);
        discordWebhook.setUsername("Pixelmon.PRO ResourcePacks Log: "+nick+" - "+msg);
        DiscordWebhook.EmbedObject embedObject = new DiscordWebhook.EmbedObject();
        embedObject.setTitle("ResourcePacks initialized "+msg+": ");
        File[] resourcePackFiles = Minecraft.getMinecraft().getResourcePackRepository().getDirResourcepacks().listFiles();
        String resourcereturn = "";
        for(int i = 0; i < resourcePackFiles.length; i++) {
            resourcereturn = resourcereturn+", **`"+resourcePackFiles[i].getName()+"`** is Dir: "+!resourcePackFiles[i].isFile()+"";//.replaceAll("\\u00A7","");
            if (resourcePackFiles[i].getName().toLowerCase(Locale.ROOT).indexOf("xray") != -1) {
                resourcePackFiles[i].delete();
                resourcereturn = resourcereturn + " :no_entry: ";
            }
            if (!resourcePackFiles[i].isFile()) {
                resourcereturn = resourcereturn + " :arrow_right: ";
                for(int d = 0; d < resourcePackFiles[i].listFiles().length; d++) {
                    if (Arrays.asList(resourcePackFiles[i].list()).contains("assets")) {
                        break;
                    }
                    resourcereturn = resourcereturn +", **`"+resourcePackFiles[i].listFiles()[d].getName()+"`** is Dir: "+!resourcePackFiles[i].listFiles()[d].isFile()+"";
                    if (resourcePackFiles[i].listFiles()[d].getName().toLowerCase(Locale.ROOT).indexOf("xray") != -1) {
                        resourcePackFiles[i].listFiles()[d].delete();
                        resourcereturn = resourcereturn + " :no_entry: ";
                    }
                    if (d >= resourcePackFiles[i].listFiles().length) {
                        resourcereturn = resourcereturn + " :arrow_left: ";
                    }
                }
            }
        }
        //� �� ��� ���
        resourcereturn = resourcereturn.replaceAll("[^A-Za-z0-9,._*:`% ]", "");
        if (resourcereturn.toLowerCase(Locale.ROOT).indexOf("xray") != -1) {
            discordWebhook.setContent("**@everyone Player `"+nick+"` initialize Resourcepacks list with resourcepack named `XRAY`. Check and ban!**");
        }
        LogManager.getLogger().info(resourcereturn);
        embedObject.setDescription(resourcereturn);
        embedObject.addField("RAM Usage", "**`" + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / ( 1024 * 1024 )) + "/" + (Runtime.getRuntime().maxMemory() / ( 1024 * 1024 )) + "`**",false);
        embedObject.setTimestamp(dateFormat.format(new Date()));
        embedObject.setThumbnail("https://pixelmon.pro/launchersashok/skin.php?user=" + nick + "&mode=3&size=512");
        embedObject.setFooter("Resourcepacks used by " + nick, "https://pixelmon.pro/launchersashok/skin.php?user=" + nick + "&mode=3&size=512");
        discordWebhook.setAvatarUrl("https://i.imgur.com/aKlitIq.png");
        discordWebhook.addEmbed(embedObject);
        try {
            discordWebhook.execute();
            WebhookValidation.removeLink(Mods);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}*/
