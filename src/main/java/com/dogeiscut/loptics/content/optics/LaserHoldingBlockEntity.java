package com.dogeiscut.loptics.content.optics;

import com.dogeiscut.loptics.content.optics.laserEmitter.LaserEmitterBlockEntity;
import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;

import com.simibubi.create.foundation.utility.Components;
import com.simibubi.create.foundation.utility.Lang;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;

import java.util.List;

public class LaserHoldingBlockEntity extends KineticBlockEntity implements IHaveGoggleInformation {
	private Boolean active;
	private Direction laserDirection;
	private double laserDistance;
	private double laserHitDistance;
	private double laserStrength;

	public LaserHoldingBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
		super(typeIn, pos, state);
		active = false;
		laserDirection = state.get(Properties.FACING);
		laserDistance = 10.0;
		laserHitDistance = 10.0;
		laserStrength = 0.5;
		setLazyTickRate(3);
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean act) { active = act; }

	public Direction getLaserDirection() {
		return laserDirection;
	}

	public void setLaserDirection(Direction direction) { laserDirection = direction; }

	public double getLaserDistance() {
		return laserDistance;
	}

	public void setLaserDistance(Double distance) { laserDistance = distance; }

	public double getLaserHitDistance() {
		return laserHitDistance;
	}

	public void setLaserHitDistance(Double distance) { laserHitDistance = distance; }

	public double getLaserStrength() {
		return laserStrength;
	}

	public void setLaserStrength(Double strength) { laserStrength = strength; }

	@Override
	public void write(NbtCompound compound, boolean clientPacket) {
		compound.putBoolean("Active", active);
		compound.putString("LaserDirection", laserDirection.getName());
		compound.putDouble("LaserDistance", laserDistance);
		compound.putDouble("LaserHitDistance", laserHitDistance);
		compound.putDouble("LaserStrength", laserStrength);
		super.write(compound, clientPacket);
	}

	@Override
	public void read(NbtCompound compound, boolean clientPacket) {
		active = compound.getBoolean("Active");
		laserDirection = Direction.byName(compound.getString("LaserDirection"));
		laserDistance = compound.getDouble("LaserDistance");
		laserHitDistance = compound.getDouble("LaserHitDistance");
		laserStrength = compound.getDouble("LaserStrength");
		super.read(compound, clientPacket);
	}

	@Override
	public void lazyTick() {
		super.lazyTick();
		if (world.isClient)
			return;
		if (!getActive())
			return;
		raycastLaserCheck();
	}

	protected void raycastLaserCheck() {
		BlockPos hitBlock = raycastLaser().getBlockPos();
		setLaserHitDistance(hitBlock.toCenterPos().distanceTo(pos.toCenterPos()));
		if (world == null) return;
		BlockEntity blockEntity = world.getBlockEntity(hitBlock);
		if (blockEntity instanceof LaserEmitterBlockEntity) {
			((LaserEmitterBlockEntity) blockEntity).setActive(true);
			((LaserEmitterBlockEntity) blockEntity).setLaserStrength(getLaserStrength());
			((LaserEmitterBlockEntity) blockEntity).setLaserDistance(getLaserDistance()-getLaserHitDistance());
			((LaserEmitterBlockEntity) blockEntity).notifyUpdate(); //todo: do this but actually good
		};
	}

	protected BlockHitResult raycastLaser() {
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

		return result;
	}

	public boolean addToGoggleTooltip(List<Text> tooltip, boolean isPlayerSneaking) {
			Lang.translate("tooltip.laser.strength", getLaserStrength())
					.style(Formatting.GREEN)
					.forGoggles(tooltip);

			Lang.translate("tooltip.laser.distance", getLaserDistance())
					.style(Formatting.GREEN)
					.forGoggles(tooltip);

		return true;
	}
}

