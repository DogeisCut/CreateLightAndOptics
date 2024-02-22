package com.dogeiscut.loptics.content.optics.laserEmitter;

import com.dogeiscut.loptics.AllBlockEntityTypes;
import com.simibubi.create.content.kinetics.base.DirectionalKineticBlock;
import com.simibubi.create.content.kinetics.base.KineticBlock;
import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;

import com.simibubi.create.foundation.block.IBE;

import com.simibubi.create.foundation.block.ProperWaterloggedBlock;

import com.simibubi.create.foundation.utility.Iterate;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;

public class LaserEmitterBlock extends DirectionalKineticBlock implements SimpleWaterloggedBlock, IBE<LaserEmitterBlockEntity>, ICogWheel {
	public LaserEmitterBlock(Properties properties) {
		super(properties);
		registerDefaultState(super.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, false));
	}

	@Override
	public BlockState getRotatedBlockState(BlockState originalState, Direction targetedFace) {
		return originalState.setValue(FACING, originalState.getValue(FACING)
				.getOpposite());
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.WATERLOGGED);
		super.createBlockStateDefinition(builder);
	}

	@Override
	public BlockState updateAfterWrenched(BlockState newState, UseOnContext context) {
		return super.updateAfterWrenched(newState, context);
	}

	@Override
	public Direction.Axis getRotationAxis(BlockState state) {
		return state.getValue(FACING)
				.getAxis();
	}

	@Override
	public Class<LaserEmitterBlockEntity> getBlockEntityClass() {
		return LaserEmitterBlockEntity.class;
	}

	@Override
	public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
		return face == state.getValue(FACING).getOpposite();
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState toPlace = super.getStateForPlacement(context);
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		Player player = context.getPlayer();
		toPlace = ProperWaterloggedBlock.withWater(level, toPlace, pos);

		Direction nearestLookingDirection = context.getNearestLookingDirection();
		Direction targetDirection = context.getPlayer() != null && context.getPlayer()
				.isShiftKeyDown() ? nearestLookingDirection : nearestLookingDirection.getOpposite();
		Direction bestConnectedDirection = null;
		double bestDistance = Double.MAX_VALUE;

		for (Direction d : Iterate.directions) {
			BlockPos adjPos = pos.relative(d);
			BlockState adjState = level.getBlockState(adjPos);
			double distance = Vec3.atLowerCornerOf(d.getNormal())
					.distanceTo(Vec3.atLowerCornerOf(targetDirection.getNormal()));
			if (distance > bestDistance)
				continue;
			bestDistance = distance;
			bestConnectedDirection = d;
		}

		if (bestConnectedDirection == null)
			return toPlace;
		if (bestConnectedDirection.getAxis() == targetDirection.getAxis())
			return toPlace;
		if (player.isShiftKeyDown() && bestConnectedDirection.getAxis() != targetDirection.getAxis())
			return toPlace;

		return toPlace.setValue(FACING, bestConnectedDirection);
	}

	@Override
	public BlockEntityType<? extends LaserEmitterBlockEntity> getBlockEntityType() {
		return AllBlockEntityTypes.LASER_EMITTER.get();
	}
}
