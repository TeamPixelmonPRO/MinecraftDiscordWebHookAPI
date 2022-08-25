package ex.qaz.mdwhapi.commands;

import ex.qaz.mdwhapi.utils.FilesUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import org.json.JSONObject;

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
                String filename = args[0].replace("name:","");
                String outputJson = FilesUtil.readJsonFromFile(filename);
                JSONObject json = new JSONObject(outputJson);
                if (args[1].contains(":")) {
                    String oldValue = json.getString(args[1].substring(0,args[1].lastIndexOf(":")));
                    json.put(args[1].substring(0,args[1].lastIndexOf(":")),args[1].substring(args[1].lastIndexOf(":")+1));
                    FilesUtil.writeJsonToFile(filename,json.toString());
                    sender.sendMessage(new TextComponentTranslation("commands.edit.succesfuledit"));
                    sender.sendMessage(new TextComponentString("`"+args[1].substring(0,args[1].lastIndexOf(":"))+":\""+oldValue+"\"` -> `"+args[1].substring(0,args[1].lastIndexOf(":"))+":\""+args[1].substring(args[1].lastIndexOf(":")+1)+"\"`"));
                } else {
                    throw new CommandException("commands.edit.errorNoTag");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            player.sendMessage(new TextComponentTranslation("commands.createEmpty.errorNoNameGiven"));
        }
    }
}
