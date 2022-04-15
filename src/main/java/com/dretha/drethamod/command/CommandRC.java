package com.dretha.drethamod.command;

import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.command.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandRC extends AbstractOneArgumentCommand {

    public CommandRC() {
        super("rc");
    }

    @Override
    protected boolean perform(MinecraftServer server, EntityPlayer player, PersonStats stats, int points) {
        stats.addRCpoints(points, player);
        return points + stats.getRCpoints() < 1000;
    }
}