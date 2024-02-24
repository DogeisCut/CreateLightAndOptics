package com.dogeiscut.loptics.content.optics.laserRedirector;

import com.dogeiscut.loptics.AllBlockEntityTypes;
import com.dogeiscut.loptics.content.optics.laserEmitter.LaserEmitterBlockEntity;
import com.simibubi.create.content.kinetics.base.DirectionalKineticBlock;

import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.block.ProperWaterloggedBlock;
import com.simibubi.create.foundation.utility.Iterate;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.Objects;

public class LaserRedirectorBlock extends DirectionalKineticBlock implements Waterloggable, IBE<LaserRedirectorBlockEntity>, ICogWheel {
	public LaserRedirectorBlock(Settings settings) {
		super(settings);
		this.setDefaultState(super.getDefaultState().with(Properties.WATERLOGGED, false));
	}

	@Override
	public BlockState getRotatedBlockState(BlockState originalState, Direction targetedFace) {
		return originalState.with(FACING, originalState.get(FACING)
				.getOpposite());
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(Properties.WATERLOGGED);
		super.appendProperties(builder);
	}

	@Override
	public BlockState updateAfterWrenched(BlockState newState, ItemUsageContext context) {
		((LaserEmitterBlockEntity) Objects.requireNonNull(context.getWorld().getBlockEntity(context.getBlockPos()))).setLaserDirection(newState.get(FACING));
		return super.updateAfterWrenched(newState, context);
	}

	@Override
	public Direction.Axis getRotationAxis(BlockState state) {
		return state.get(FACING)
				.getAxis();
	}

	@Override
	public Class<LaserRedirectorBlockEntity> getBlockEntityClass() {
		return LaserRedirectorBlockEntity.class;
	}

	@Override
	public boolean hasShaftTowards(WorldView world, BlockPos pos, BlockState state, Direction face) {
		return face == state.get(FACING).getOpposite();
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext context) {
		BlockState toPlace = super.getPlacementState(context);
		World world = context.getWorld();
		BlockPos pos = context.getBlockPos();
		PlayerEntity player = context.getPlayer();
		toPlace = ProperWaterloggedBlock.withWater(world, toPlace, pos);

		Direction nearestLookingDirection = context.getPlayerLookDirection();
		Direction targetDirection = context.getPlayer() != null && context.getPlayer()
				.isSneaking() ? nearestLookingDirection : nearestLookingDirection.getOpposite();
		Direction bestConnectedDirection = null;
		double bestDistance = Double.MAX_VALUE;

		for (Direction d : Iterate.directions) {
			BlockPos adjPos = pos.offset(d);
			BlockState adjState = world.getBlockState(adjPos);
			double distance = Vec3d.of(d.getVector())
					.distanceTo(Vec3d.of(targetDirection.getVector()));
			if (distance > bestDistance)
				continue;
			bestDistance = distance;
			bestConnectedDirection = d;
		}

		if (bestConnectedDirection == null)
			return toPlace;
		if (bestConnectedDirection.getAxis() == targetDirection.getAxis())
			return toPlace;
		if (player.isSneaking() && bestConnectedDirection.getAxis() != targetDirection.getAxis())
			return toPlace;

		return toPlace.with(FACING, bestConnectedDirection);
	}

	@Override
	public BlockEntityType<? extends LaserRedirectorBlockEntity> getBlockEntityType() {
		return AllBlockEntityTypes.LASER_REDIRECTOR.get();
	}
}
