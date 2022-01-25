package com.dretha.drethamod.client.keybinds;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

public class KeybindsRegister {
	private static final String category = "Oshiete Mod";

	private static final ArrayList<KeyBinding> BINDS = new ArrayList<>();

    public static final KeyBinding
            KEY_ACTIVATE_KAGUNE = initializeBind("Activate Kagune", Keyboard.KEY_G, category),
            KEY_ACTIVATE_GHOUL_SPEED_MODE = initializeBind("Ghoul Speed Mode", Keyboard.KEY_X, category),
            KEY_HIT_KAGUNE = initializeBind("Hit Kagune", Keyboard.KEY_Q, category),
            KEY_BLOCK_KAGUNE = initializeBind("Block Kagune", Keyboard.KEY_E, category),
            KEY_HIT_MODE = initializeBind("Change Impact Mode & Reload Firearm", Keyboard.KEY_R, category),
    		KEY_SMELL = initializeBind("Smell", Keyboard.KEY_X, category),
			KEY_ACTIVITY = initializeBind("Activity Key (+WASD - Force Speed)", Keyboard.KEY_LMENU, category),
    		KEY_OPEN_CLOTHES_INVENTORY = initializeBind("Open Clothes Inventory", Keyboard.KEY_C, category);

    public static void register()
    {
		/*
    	ClientRegistry.registerKeyBinding(KEY_ACTIVATE_KAGUNE);
    	ClientRegistry.registerKeyBinding(KEY_ACTIVATE_GHOUL_SPEED_MODE);
    	ClientRegistry.registerKeyBinding(KEY_HIT_KAGUNE);
    	ClientRegistry.registerKeyBinding(KEY_BLOCK_KAGUNE);
    	ClientRegistry.registerKeyBinding(KEY_HIT_MODE);
    	ClientRegistry.registerKeyBinding(KEY_SMELL);
    	ClientRegistry.registerKeyBinding(KEY_OPEN_CLOTHES_INVENTORY);
		ClientRegistry.registerKeyBinding(KEY_ACTIVITY);*/

		for (KeyBinding bind : BINDS) {
			ClientRegistry.registerKeyBinding(bind);
		}
    }

	public static KeyBinding initializeBind(String description, int keyCode, String category) {
		KeyBinding bind = new KeyBinding(description, keyCode, category);
		BINDS.add(bind);
		return bind;
	}
}
