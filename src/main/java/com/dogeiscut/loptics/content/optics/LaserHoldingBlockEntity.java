package com.dogeiscut.loptics.content.optics;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;

public class LaserHoldingBlockEntity extends KineticBlockEntity {
	private Direction laserDirection;
	private double laserDistance;
	private double laserStrength;

	public LaserHoldingBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
		super(typeIn, pos, state);
		laserDirection = state.get(Properties.FACING);
		laserDistance = 10.0;
		laserStrength = 0.5;
		setLazyTickRate(3);
	}

	public Direction getLaserDirection() {
		return laserDirection;
	}

	public void setLaserDirection(Direction direction) {
		laserDirection = direction;
	}

	public double getLaserDistance() {
		return laserDistance;
	}

	public void setLaserDistance(Double distance) { laserDistance = distance; }

	public double getLaserStrength() {
		return laserStrength;
	}

	public void setLaserStrength(Double strength) { laserStrength = strength; }

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
		if (world.isClient)
			return;
	}

	protected void laserInteractions(BlockPos laserHit) {

	}

	protected BlockPos raycastLaser() {
		double maxDistance = laserDistance;
		Vec3d start = new Vec3d(
				pos.getX() + 0.5,
				pos.getY() + 0.5,
				pos.getZ() + 0.5).add(
				laserDirection.getOffsetX()*0.5,
				laserDirection.getOffsetY()*0.5,
				laserDirection.getOffsetZ()*0.5
		);
		Vec3d end = start.add(
				laserDirection.getOffsetX() * maxDistance,
				laserDirection.getOffsetY() * maxDistance,
				laserDirection.getOffsetZ() * maxDistance);

		BlockHitResult result = world.raycast(new RaycastContext(start, end, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, null));

		return result.getBlockPos();
	}
}

