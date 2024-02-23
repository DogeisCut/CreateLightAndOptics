package com.dogeiscut.loptics.content.redstone.pixelScreen;

import com.simibubi.create.content.kinetics.base.DirectionalKineticBlock;
import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import com.simibubi.create.foundation.block.IBE;

import net.minecraft.block.BlockState;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.Direction;

public class PixelScreenBlock extends DirectionalKineticBlock implements Waterloggable, IBE<PixelScreenBlockEntity>, ICogWheel {
	public PixelScreenBlock(Settings settings) {
		super(settings);
	}

	@Override
	public Direction.Axis getRotationAxis(BlockState state) {
		return null;
	}

	@Override
	public Class<PixelScreenBlockEntity> getBlockEntityClass() {
		return null;
	}

	@Override
	public BlockEntityType<? extends PixelScreenBlockEntity> getBlockEntityType() {
		return null;
	}
}
