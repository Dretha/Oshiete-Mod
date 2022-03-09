package com.dretha.drethamod.command;

import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandRC extends CommandBase {
    public static final String
            NAME = "rc",//Имя команды, используется при вызове
            USAGE = "/rc <amount> OR /rc <amount> [player]";//Шаблон вызова, выводится при выбрасывании WrongUsageException

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

            if (points + stats.getRCpoints() < 1000)
                throw new CommandException("Too few number", new Object[0]);

            stats.addRCpoints(points, player);
            player.sendMessage(new TextComponentTranslation("Added RC " + points));
        }
    }
}

