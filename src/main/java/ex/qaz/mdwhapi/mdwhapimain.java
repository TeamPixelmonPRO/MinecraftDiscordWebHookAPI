package ex.qaz.mdwhapi;

import ex.qaz.mdwhapi.commands.CommandWHSend;
import ex.qaz.mdwhapi.proxy.CommonProxy;
import ex.qaz.mdwhapi.utils.FilesUtil;
import ex.qaz.mdwhapi.utils.WebHookEmptyFile;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Mod(modid = "mdwhapi")
public class mdwhapimain {
    public static String MODID = "mdwhapi";
    public static Logger logger;
    @Mod.Instance
    public static mdwhapimain INSTANCE;
    @SidedProxy(clientSide = "ex.qaz.mdwhapi.proxy.ClientProxy", serverSide = "ex.qaz.mdwhapi.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static String jsonlistpath = Minecraft.getMinecraft().gameDir.getPath() + "\\config\\mdwhapi";
    public static List<String> configDir;
    public static File configDirFile;
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) throws IOException
    {
        if (Files.exists(Paths.get(jsonlistpath))) {
            FilesUtil.reload(new File(jsonlistpath));
        } else {
            configDirFile = new File(jsonlistpath);
            configDirFile.mkdir();
            FilesUtil.reload(new File(jsonlistpath));
            WebHookEmptyFile.createEmptyExampleFileAt(jsonlistpath);
        }
        proxy.postInit(event);
    }

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public void onServerStart(FMLServerStartingEvent event) {
        MinecraftServer minecraftServer = event.getServer();
        registerCommands(event);
    }

    private void registerCommands(FMLServerStartingEvent event) {
        MinecraftServer server = event.getServer();
        ICommandManager command = server.getCommandManager();
        ServerCommandManager manager = (ServerCommandManager) command;
        manager.registerCommand(new CommandWHSend());
    }
}