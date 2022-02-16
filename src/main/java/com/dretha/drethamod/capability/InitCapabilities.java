package com.dretha.drethamod.capability;

import com.dretha.drethamod.capability.firearm.CapaFirearmHandler;
import com.dretha.drethamod.capability.firearm.CapaFirearmStorage;
import com.dretha.drethamod.capability.firearm.ICapaFirearmHandler;
import com.dretha.drethamod.capability.world.IWorldCapaHandler;
import com.dretha.drethamod.capability.world.WorldCapaHandler;
import com.dretha.drethamod.capability.world.WorldCapaStorage;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class InitCapabilities {
    public static void init() {
        CapabilityManager.INSTANCE.register(ICapaHandler.class, new CapaStorage(), CapaHandler.class);
        CapabilityManager.INSTANCE.register(ICapaFirearmHandler.class, new CapaFirearmStorage(), CapaFirearmHandler.class);
        CapabilityManager.INSTANCE.register(IWorldCapaHandler.class, new WorldCapaStorage(), WorldCapaHandler.class);
    }
}
