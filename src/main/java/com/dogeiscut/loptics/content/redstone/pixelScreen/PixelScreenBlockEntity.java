package com.dogeiscut.loptics.content.redstone.pixelScreen;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class PixelScreenBlockEntity extends KineticBlockEntity {
	public PixelScreenBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
		super(typeIn, pos, state);
	}
}
