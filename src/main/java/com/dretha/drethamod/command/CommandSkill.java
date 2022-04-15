package com.dretha.drethamod.command;

import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandSkill extends AbstractOneArgumentCommand {

    public CommandSkill() {
        super("skill");
    }

    @Override
    protected boolean perform(MinecraftServer server, EntityPlayer player, PersonStats stats, int points) {
        stats.addSkill(points, player);
        return stats.getSkill()+points>=0;
    }
}
