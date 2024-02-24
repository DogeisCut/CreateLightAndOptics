package com.dogeiscut.loptics.content.optics.laserRedirector;

import com.dogeiscut.loptics.content.optics.laserEmitter.LaserEmitterBlockEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class LaserRedirectorBlockEntity extends LaserEmitterBlockEntity {
	public LaserRedirectorBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
		super(typeIn, pos, state);
	}
}
