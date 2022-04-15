package com.dretha.drethamod.command;

import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

public abstract class AbstractOneArgumentCommand extends CommandBase {
    public final String
            NAME,//Имя команды, используется при вызове
            USAGE;//Шаблон вызова, выводится при выбрасывании WrongUsageException

    public AbstractOneArgumentCommand(String name) {
        NAME = name;
        USAGE = "/" + name + " <amount> OR /" + name + " <amount> [player]";
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return USAGE;
    }

    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender instanceof EntityPlayer && server.getPlayerList().canSendCommands(((EntityPlayer) sender).getGameProfile()) && PersonStats.getStats((EntityPlayer) sender).isGhoul();
    }


    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length <= 0) {
            throw new WrongUsageException(USAGE, new Object[0]);
        } else {
            String s = args[0];

            int points = parseInt(s);

            EntityPlayer player = args.length > 1 ? getPlayer(server, sender, args[1]) : getCommandSenderAsPlayer(sender);
            PersonStats stats = PersonStats.getStats(player);

            if (!perform(server, player, stats, points))
                throw new CommandException("Too few number", new Object[0]);

            player.sendMessage(new TextComponentTranslation("Added " + NAME + " " + points));
        }
    }

    protected abstract boolean perform(MinecraftServer server, EntityPlayer player, PersonStats stats, int points);
}
