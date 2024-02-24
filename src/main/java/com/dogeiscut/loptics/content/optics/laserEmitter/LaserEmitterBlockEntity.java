package com.dogeiscut.loptics.content.optics.laserEmitter;

import com.dogeiscut.loptics.content.optics.LaserHoldingBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;

public class LaserEmitterBlockEntity extends LaserHoldingBlockEntity {
	public LaserEmitterBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
		super(typeIn, pos, state);
	}

	@Override
	public void lazyTick() {
		super.lazyTick();
		if (world.isClient)
			return;
		if (getSpeed() == 0)
			return;
		setLaserDistance(getSpeed()/4d);
		setLaserStrength(Math.max(getSpeed()/10d, 1d));
		BlockPos hitBlock = raycastLaser();
		laserInteractions(hitBlock);
	}
}
