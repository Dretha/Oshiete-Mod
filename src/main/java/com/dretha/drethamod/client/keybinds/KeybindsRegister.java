package com.dretha.drethamod.client.keybinds;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class KeybindsRegister {
	private static final String category = "Oshiete Mod";
    public static final KeyBinding
            KEY_ACTIVATE_KAGUNE = new KeyBinding("Activate Kagune", Keyboard.KEY_G, category),
            KEY_ACTIVATE_GHOUL_SPEED_MODE = new KeyBinding("Ghoul Speed Mode", Keyboard.KEY_X, category),
            KEY_HIT_KAGUNE = new KeyBinding("Hit Kagune", Keyboard.KEY_Q, category),
            KEY_BLOCK_KAGUNE = new KeyBinding("Block Kagune", Keyboard.KEY_E, category),
            KEY_HIT_MODE = new KeyBinding("Change Impact Mode & Reload Firearm", Keyboard.KEY_R, category),
    		KEY_SMELL = new KeyBinding("Smell", Keyboard.KEY_X, category),
    		KEY_OPEN_CLOTHES_INVENTORY = new KeyBinding("Open Clothes Inventory", Keyboard.KEY_C, category);

    public static void register()
    {
    	ClientRegistry.registerKeyBinding(KEY_ACTIVATE_KAGUNE);
    	ClientRegistry.registerKeyBinding(KEY_ACTIVATE_GHOUL_SPEED_MODE);
    	ClientRegistry.registerKeyBinding(KEY_HIT_KAGUNE);
    	ClientRegistry.registerKeyBinding(KEY_BLOCK_KAGUNE);
    	ClientRegistry.registerKeyBinding(KEY_HIT_MODE);
    	ClientRegistry.registerKeyBinding(KEY_SMELL);
    	ClientRegistry.registerKeyBinding(KEY_OPEN_CLOTHES_INVENTORY);
    }
}
