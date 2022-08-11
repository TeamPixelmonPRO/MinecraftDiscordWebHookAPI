package ex.qaz.mdwhapi.commands;

import ex.qaz.mdwhapi.mdwhapimain;
import ex.qaz.mdwhapi.utils.FilesUtil;
import ex.qaz.mdwhapi.utils.SenderUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandWHSend extends CommandBase {
    public static final String
            NAME = "webhooksend",
            ALIAS = "whsend",
            USAGE = "/webhooksend json:<WebhookJsonFilename> url:<DiscordWebhookURL>";

    @Override
    public String getName() {

        return this.NAME;
    }

    @Override
    public String getUsage(ICommandSender sender) {

        return this.USAGE;
    }

    @Override
    public List<String> getAliases() {
        List<String> aliases = new ArrayList<String>();
        aliases.add(this.ALIAS);
        return aliases;
    }
    //TODO Create Placeholder integration(User-friendly - Every user can create placeholders)
    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (sender instanceof EntityPlayer) {
            EntityPlayer player = this.getCommandSenderAsPlayer(sender);
            TextComponentTranslation chatMessage = new TextComponentTranslation("commands.chat.emptyMessage");
            if (FilesUtil.isExist(args[0].replace("json:",""),mdwhapimain.configDir)) {
                try {
                    String outputJson = FilesUtil.getJsonFromFile(args[0].replace("json:",""));
                    boolean isSended = SenderUtil.sendWebhookByJSON(FilesUtil.getJsonFromFile(args[0].replace("json:","")),args[1].replace("url:",""));
                    if (isSended) {
                        chatMessage = new TextComponentTranslation("commands.send.succesful");
                    } else {
                        chatMessage = new TextComponentTranslation("commands.send.errorCantSend");
                        mdwhapimain.logger.warn(outputJson);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                chatMessage = new TextComponentTranslation("commands.send.errorNoJson");
            }
            player.sendMessage(chatMessage);
        }
    }
}
