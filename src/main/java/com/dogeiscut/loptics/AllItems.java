package com.dogeiscut.loptics;

import com.tterrag.registrate.util.entry.ItemEntry;

import net.minecraft.item.Item;

import static com.dogeiscut.loptics.LightAndOptics.REGISTRATE;

public class AllItems {

	public static final ItemEntry<Item> LASER_EMITTING_MECHANISM =
			REGISTRATE.item("laser_emitting_mechanism", Item::new)
					.register();

	public static void register() {}
}
