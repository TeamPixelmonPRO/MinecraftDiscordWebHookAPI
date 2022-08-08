package ex.qaz.mdwhapi;

import ex.qaz.mdwhapi.utils.webhooks.WebHookEmptyFile;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    public void postInit(FMLPostInitializationEvent event) {
        if (Files.exists(Paths.get(jsonlistpath))) {
            configDir = new File(jsonlistpath);
        } else {
            configDir = new File(jsonlistpath);
            configDir.mkdir();
            File emptyJsonExample = new File(jsonlistpath+"\\exampleWebhook.json");
            BufferedWriter writer = new BufferedWriter(new FileWriter(emptyJsonExample, false));
            writer.write(WebHookEmptyFile.getWebHookEmptyFileJson());
            writer.close();

            if (emptyJsonExample.createNewFile()) {
                logger.info("New empty example json webhook file created succesfully!");
            } else {
                logger.info("New empty example json webhook file didn't created!");
            }

        }
    }
}