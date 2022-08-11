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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandWHReload extends CommandBase {
    public static final String
            NAME = "webhookreload",
            ALIAS = "whreload",
            USAGE = "/webhookreload";

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
        FilesUtil.reload(new File(mdwhapimain.jsonlistpath));
        sender.sendMessage(new TextComponentTranslation("commands.reload.success"));
    }
}
