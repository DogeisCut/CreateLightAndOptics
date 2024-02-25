package com.dogeiscut.loptics.content.optics;

import com.dogeiscut.loptics.content.optics.laserEmitter.LaserEmitterBlockEntity;
import com.dogeiscut.loptics.content.optics.laserRedirector.LaserRedirectorBlockEntity;
import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;

import com.simibubi.create.foundation.utility.Components;
import com.simibubi.create.foundation.utility.Lang;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.List;

public class LaserHoldingBlockEntity extends KineticBlockEntity implements IHaveGoggleInformation {
	private Boolean active;
	private Direction laserDirection;
	private double laserDistance;
	private double laserHitDistance;
	private double laserStrength;

	public int recursionValue = 0;

	public LaserHoldingBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
		super(typeIn, pos, state);
		active = false;
		laserDirection = state.get(Properties.FACING);
		laserDistance = 0.0;
		laserHitDistance = 0.0;
		laserStrength = 0.0;
		setLazyTickRate(1);
	}

	protected ItemStack getOverheatItemStack() {
		// Default implementation, override in subclasses
		return new ItemStack(Items.STONE);
	}

	private void overheat() {
		if (world != null && !world.isClient) {
			Block.dropStack(world, pos, getOverheatItemStack());
			world.breakBlock(pos, false);
		}
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
		recursionValue = 0;
		raycastLaserCheck();
	}

	@Override
	public void markDirty() {
		super.markDirty();
		recursionValue = 0;
		findAndUpdateSurroundingLaserHolders();
	}

	protected void findAndUpdateSurroundingLaserHolders() {
		double maxDistance = 64.0;

		if (world == null) return;
		for (Direction direction : Direction.values()) {
			recursionValue = 0;
			BlockHitResult hitResult = raycastLaser(direction, maxDistance);
			if (hitResult == null) {
				continue;
			}
			if (hitResult.getType() == BlockHitResult.Type.BLOCK) {
				BlockPos hitPos = hitResult.getBlockPos();
				BlockEntity neighborBlockEntity = world.getBlockEntity(hitPos);

				if (neighborBlockEntity instanceof LaserHoldingBlockEntity) {
					((LaserHoldingBlockEntity) neighborBlockEntity).raycastLaserCheck();
				}
			}
		}
	}

	protected void raycastLaserCheck() {
		BlockPos hitBlock = raycastLaser(getLaserDirection(), laserDistance).getBlockPos();
		setLaserHitDistance(hitBlock.toCenterPos().distanceTo(pos.toCenterPos()));
		if (world == null) return;
		BlockEntity blockEntity = world.getBlockEntity(hitBlock);
		if (blockEntity == null) {
			recursionValue = 0;
			return;
		}
		if (recursionValue > 64) {
			overheat();
			return;
		}
		if (blockEntity instanceof LaserRedirectorBlockEntity) {
			if (!((LaserRedirectorBlockEntity) blockEntity).getActive()) {
				((LaserRedirectorBlockEntity) blockEntity).setActive(true);
				((LaserRedirectorBlockEntity) blockEntity).setLaserStrength(getLaserStrength());
				((LaserRedirectorBlockEntity) blockEntity).setLaserDistance(getLaserDistance()-getLaserHitDistance());
				((LaserRedirectorBlockEntity) blockEntity).recursionValue += 1;
				((LaserRedirectorBlockEntity) blockEntity).raycastLaserCheck();
			}
		};
	}

	protected BlockHitResult raycastLaser(Direction direction, double distance) {
		double maxDistance = getLaserDistance();
		Vec3d start = new Vec3d(
				pos.getX() + 0.5,
				pos.getY() + 0.5,
				pos.getZ() + 0.5).add(
				direction.getOffsetX()*0.5,
				direction.getOffsetY()*0.5,
				direction.getOffsetZ()*0.5
		);
		Vec3d end = start.add(
				direction.getOffsetX() * maxDistance,
				direction.getOffsetY() * maxDistance,
				direction.getOffsetZ() * maxDistance);

		BlockHitResult result = world.raycast(new RaycastContext(start, end, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, null));

		return result;
	}

	public boolean addToGoggleTooltip(List<Text> tooltip, boolean isPlayerSneaking) {

			super.addToGoggleTooltip(tooltip, isPlayerSneaking);

			Lang.translate("tooltip.laser.strength", getLaserStrength()*1000)
					.style(Formatting.GREEN)
					.forGoggles(tooltip);

			Lang.translate("tooltip.laser.distance", getLaserDistance())
					.style(Formatting.GREEN)
					.forGoggles(tooltip);

		return true;
	}
}

