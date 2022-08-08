package ex.qaz.mdwhapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import ex.qaz.mdwhapi.utils.webhooks.WebHookEmptyFile;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.LogManager;

@Mod(modid = "mdwhapi")
public class mdwhapimain {
    public static String MODID = "mdwhapi";
    public static Logger logger;
    @Mod.Instance
    public static mdwhapimain INSTANCE;

    private static String jsonlistpath = Minecraft.getMinecraft().gameDir.getPath() + "\\config\\mdwhapi";
    public static File configDir;
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) throws IOException {
        if (Files.exists(Paths.get(jsonlistpath))) {
            configDir = new File(jsonlistpath);
        } else {
            configDir = new File(jsonlistpath);
            configDir.mkdir();
            File emptyJsonExample = new File(jsonlistpath+"\\exampleWebhook.json");
            PrintWriter writer = new PrintWriter(new FileWriter(emptyJsonExample));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser jp = new JsonParser();
            String uglyJSONString = WebHookEmptyFile.getWebHookEmptyFileJson();
            JsonElement je = jp.parse(uglyJSONString);
            String prettyJsonString = gson.toJson(je);
            writer.write(prettyJsonString);
            writer.close();
            emptyJsonExample.createNewFile();
            if (Files.exists(Paths.get(jsonlistpath+"\\exampleWebhook.json"))) {
                logger.info("New empty example json webhook file created succesfully!");
            } else {
                logger.info("New empty example json webhook file didn't created!");
            }

        }
    }
}