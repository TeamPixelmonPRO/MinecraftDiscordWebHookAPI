package ex.qaz.mdwhapi.commands;

import ex.qaz.mdwhapi.mdwhapimain;
import ex.qaz.mdwhapi.utils.FilesUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import scala.swing.TextComponent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CommandWHList extends CommandBase {
    public static final String
            NAME = "webhooklist",
            ALIAS = "whlist",
            USAGE = "/webhooklist";

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
        String list = String.join(", ",mdwhapimain.configDir);
        sender.sendMessage(new TextComponentTranslation("commands.list.current"));
        sender.sendMessage(ITextComponent.Serializer.jsonToComponent(list));
    }
}
