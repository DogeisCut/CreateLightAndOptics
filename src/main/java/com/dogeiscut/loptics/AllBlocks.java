package com.dogeiscut.loptics;

import com.dogeiscut.loptics.content.optics.laserEmitter.LaserEmitterBlock;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.content.kinetics.crafter.CrafterCTBehaviour;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.MapColor;
import net.minecraft.client.render.RenderLayer;

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
					.properties(p -> p.mapColor(MapColor.TERRACOTTA_YELLOW))
					.properties(AbstractBlock.Settings::nonOpaque)
					.transform(axeOrPickaxe())
					.blockstate(BlockStateGen.directionalBlockProviderIgnoresWaterlogged(true))
					.transform(BlockStressDefaults.setImpact(5.0))
					.addLayer(() -> RenderLayer::getCutoutMipped)
					.item()
					.transform(customItemModel())
					.register();

	public static void register() {}
}
