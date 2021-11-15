package com.dretha.drethamod.client.keybinds;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeybindsRegister {
	private static final String category = "Oshiete Mod";
    public static final KeyBinding
            KEY_ACTIVATE_KAGUNE = new KeyBinding("Activate Kagune", Keyboard.KEY_G, category),
            KEY_ACTIVATE_GHOUL_SPEED_MODE = new KeyBinding("Ghoul Speed Mode", Keyboard.KEY_X, category),
            KEY_HIT_KAGUNE = new KeyBinding("Hit Kagune", Keyboard.KEY_Q, category),
            KEY_BLOCK_KAGUNE = new KeyBinding("Block Kagune", Keyboard.KEY_E, category),
            KEY_HIT_MODE = new KeyBinding("Change Hit Mode", Keyboard.KEY_R, category);

    public static void register()
    {
    	ClientRegistry.registerKeyBinding(KEY_ACTIVATE_KAGUNE);
    	ClientRegistry.registerKeyBinding(KEY_ACTIVATE_GHOUL_SPEED_MODE);
    	ClientRegistry.registerKeyBinding(KEY_HIT_KAGUNE);
    	ClientRegistry.registerKeyBinding(KEY_BLOCK_KAGUNE);
    	ClientRegistry.registerKeyBinding(KEY_HIT_MODE);
    }
}
