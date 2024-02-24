package com.dogeiscut.loptics.content.optics.laserEmitter;

import com.dogeiscut.loptics.AllBlocks;
import com.dogeiscut.loptics.content.optics.LaserHoldingBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
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
	protected ItemStack getOverheatItemStack() {
		return new ItemStack(AllBlocks.LASER_EMITTER.asItem());
	}

	@Override
	public void lazyTick() {
		super.lazyTick();
		setActive(getSpeed() != 0);
		setLaserDistance(Math.abs(getSpeed())/4d);
		setLaserStrength(Math.abs(getSpeed())/1000d);
	}
}
