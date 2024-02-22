package com.dogeiscut.loptics;

import com.dogeiscut.loptics.content.optics.laserEmitter.LaserEmitterBlock;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.content.kinetics.crafter.CrafterCTBehaviour;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.material.MapColor;

import static com.dogeiscut.loptics.LightAndOptics.REGISTRATE;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;

public class AllBlocks {
	static {
		//REGISTRATE.creativeModeTab(() -> AllCreativeModeTabs.BASE_CREATIVE_TAB);
	}

	public static final BlockEntry<LaserEmitterBlock> LASER_EMITTER =
			REGISTRATE.block("laser_emitter", LaserEmitterBlock::new)
					.initialProperties(SharedProperties::softMetal)
					.properties(p -> p.noOcclusion().mapColor(MapColor.TERRACOTTA_YELLOW))
					.transform(axeOrPickaxe())
					.blockstate(BlockStateGen.directionalBlockProviderIgnoresWaterlogged(true))
					.transform(BlockStressDefaults.setImpact(5.0))
					.addLayer(() -> RenderType::cutoutMipped)
					.item()
					.transform(customItemModel())
					.register();

	public static void register() {}
}
