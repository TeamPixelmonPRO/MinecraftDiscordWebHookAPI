package ex.qaz.mdwhapi.commands;

import ex.qaz.mdwhapi.utils.FilesUtil;
import ex.qaz.mdwhapi.utils.DiscordWebhook;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandWHEditExisting extends CommandBase {
    public static final String
            NAME = "webhookedit",
            ALIAS = "whedit",
            USAGE = "/webhookedit name:<FileName> {tag}:<value> - available tags [\"avatar_url\",\"content\",\"username\"]. Embed editiong available only from file.";

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
    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayer player = this.getCommandSenderAsPlayer(sender);
        if (args[0].startsWith("name:")) {
            try {
                String outputJson = FilesUtil.readJsonFromFile(args[0].replace("name:",""));
                
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            player.sendMessage(new TextComponentTranslation("commands.createEmpty.errorNoNameGiven"));
        }
    }
}
