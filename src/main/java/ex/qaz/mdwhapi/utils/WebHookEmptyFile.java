package ex.qaz.mdwhapi.utils;

import ex.qaz.mdwhapi.mdwhapimain;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WebHookEmptyFile {
    public static String getWebHookEmptyFileJson() throws IOException {
        DiscordWebhook wh = new DiscordWebhook();
        DiscordWebhook.EmbedObject embed = new DiscordWebhook.EmbedObject();
        wh.setAvatarUrl("https://i.imgur.com/Yz0JZne.png");
        wh.setTts(false);
        wh.setContent("WebHook main content");
        wh.setUsername("WebHook username");
        embed.addField("Embed Field1 name","Embed Field1 value",false);
        embed.setAuthor("Embed Author name","https://i.imgur.com/Yz0JZne.png","https://i.imgur.com/Yz0JZne.png");
        embed.setColor(new Color(255,255,255));
        embed.setDescription("Embed description");
        embed.setFooter("Embed footer text","https://i.imgur.com/Yz0JZne.png");
        embed.setImage("https://i.imgur.com/Yz0JZne.png");
        embed.setThumbnail("https://i.imgur.com/Yz0JZne.png");
        embed.setTitle("Embed title text");
        wh.addEmbed(embed);
        return wh.getJson();
    }
    public static void createEmptyExampleFileAt(String path) throws IOException {
        File emptyJsonExample = new File(path+"\\emptyExample.json");
        PrintWriter writer = new PrintWriter(new FileWriter(emptyJsonExample));
        writer.write(getWebHookEmptyFileJson());
        writer.close();
        emptyJsonExample.createNewFile();
        if (Files.exists(Paths.get(path+"\\emptyExample.json"))) {
            mdwhapimain.logger.info("New empty example json webhook file created succesfully!");
        } else {
            mdwhapimain.logger.info("New empty example json webhook file didn't created!");
        }
    }

    public static File createEmptyFileAtWithName(String name, String path) throws IOException {
        File emptyJsonFile = new File(path+"\\"+name+".json");
        PrintWriter writer = new PrintWriter(new FileWriter(emptyJsonFile));
        writer.write(getWebHookEmptyFileJson());
        writer.close();
        emptyJsonFile.createNewFile();
        if (Files.exists(Paths.get(path))) {
            mdwhapimain.logger.info("New empty example json webhook file created succesfully!");
        } else {
            mdwhapimain.logger.info("New empty example json webhook file didn't created!");
        }
        return emptyJsonFile;
    }
}
