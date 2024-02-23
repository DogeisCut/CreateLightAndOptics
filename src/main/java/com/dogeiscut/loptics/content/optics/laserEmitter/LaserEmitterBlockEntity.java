package com.dogeiscut.loptics.content.optics.laserEmitter;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class LaserEmitterBlockEntity extends KineticBlockEntity {
	private Direction laserDirection;
	private double laserDistance;
	private double laserStrength;

	public LaserEmitterBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
		super(typeIn, pos, state);
		laserDirection = Direction.NORTH;
		laserDistance = 10.0;
		laserStrength = 0.5;
		setLazyTickRate(10);
	}

	public Direction getLaserDirection() {
		return laserDirection;
	}

	public double getLaserDistance() {
		return laserDistance;
	}

	public double getLaserStrength() {
		return laserStrength;
	}

	@Override
	public void write(NbtCompound compound, boolean clientPacket) {
		compound.putString("LaserDirection", laserDirection.getName());
		compound.putDouble("LaserDistance", laserDistance);
		compound.putDouble("LaserStrength", laserStrength);
		super.write(compound, clientPacket);
	}

	@Override
	public void read(NbtCompound compound, boolean clientPacket) {
		laserDirection = Direction.byName(compound.getString("LaserDirection"));
		laserDistance = compound.getDouble("LaserDistance");
		laserStrength = compound.getDouble("LaserStrength");
		super.read(compound, clientPacket);
	}

	@Override
	public void lazyTick() {
		super.lazyTick();
		if (world.isClient())
			return;

	}
}
