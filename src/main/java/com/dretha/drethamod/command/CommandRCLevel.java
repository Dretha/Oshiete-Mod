package com.dretha.drethamod.command;

import com.dretha.drethamod.utils.stats.PersonStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandRCLevel extends AbstractOneArgumentCommand {
    public CommandRCLevel() {
        super("rclevel");
    }

    @Override
    protected boolean perform(MinecraftServer server, EntityPlayer player, PersonStats stats, int points) {
        if (points<0)
            stats.removeRClevel(points);
        else
            stats.addRClevel(points);
        return points>0 && points<=stats.maxRClevel();
    }
}
