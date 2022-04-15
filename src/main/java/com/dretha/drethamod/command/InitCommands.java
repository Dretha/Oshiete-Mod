package com.dretha.drethamod.command;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class InitCommands {
    public static void init(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandRC());
        event.registerServerCommand(new CommandSkill());
        event.registerServerCommand(new CommandRCLevel());
    }
}
