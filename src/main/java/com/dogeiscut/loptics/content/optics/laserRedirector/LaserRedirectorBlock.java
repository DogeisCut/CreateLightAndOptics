package com.dogeiscut.loptics.content.optics.laserRedirector;

import com.dogeiscut.loptics.AllBlockEntityTypes;
import com.dogeiscut.loptics.AllBlocks;
import com.dogeiscut.loptics.content.optics.laserEmitter.LaserEmitterBlockEntity;
import com.simibubi.create.content.contraptions.elevator.ElevatorColumn;
import com.simibubi.create.content.kinetics.base.DirectionalKineticBlock;

import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import com.simibubi.create.content.redstone.contact.RedstoneContactBlock;
import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.block.ProperWaterloggedBlock;
import com.simibubi.create.foundation.block.WrenchableDirectionalBlock;
import com.simibubi.create.foundation.utility.BlockHelper;
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
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.Objects;

public class LaserRedirectorBlock extends WrenchableDirectionalBlock implements Waterloggable, IBE<LaserRedirectorBlockEntity> {
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
	public ActionResult onWrenched(BlockState state, ItemUsageContext context) {
		ActionResult onWrenched = super.onWrenched(state, context);
		if (onWrenched != ActionResult.SUCCESS)
			return onWrenched;

		World level = context.getWorld();
		if (level.isClient())
			return onWrenched;

		BlockPos pos = context.getBlockPos();
		state = level.getBlockState(pos);
		Direction facing = state.get(RedstoneContactBlock.FACING);
		if (facing.getAxis() == Direction.Axis.Y)
			return onWrenched;

		level.setBlockState(pos, BlockHelper.copyProperties(state, AllBlocks.LASER_REDIRECTOR.getDefaultState()));

		return onWrenched;
	}

	@Override
	public Class<LaserRedirectorBlockEntity> getBlockEntityClass() {
		return LaserRedirectorBlockEntity.class;
	}


	@Override
	public BlockState getPlacementState(ItemPlacementContext context) {
		BlockState state = getDefaultState().with(FACING, context.getPlayerLookDirection()
				.getOpposite());
		Direction placeDirection = context.getSide()
				.getOpposite();

		if ((context.getPlayer() != null && context.getPlayer()
				.isSneaking()))
			state = state.with(FACING, placeDirection);

		return state;
	}

	@Override
	public BlockEntityType<? extends LaserRedirectorBlockEntity> getBlockEntityType() {
		return AllBlockEntityTypes.LASER_REDIRECTOR.get();
	}
}
