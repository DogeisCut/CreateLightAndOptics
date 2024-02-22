package com.dogeiscut.loptics.content.optics.laserEmitter;

import com.jozufozu.flywheel.api.Instancer;
import com.jozufozu.flywheel.api.MaterialManager;
import com.dogeiscut.loptics.AllPartialModels;
import com.jozufozu.flywheel.api.instance.DynamicInstance;
import com.simibubi.create.content.kinetics.base.SingleRotatingInstance;
import com.simibubi.create.content.kinetics.base.flwdata.RotatingData;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class LaserEmitterCogInstance extends SingleRotatingInstance<LaserEmitterBlockEntity> implements DynamicInstance {

	public LaserEmitterCogInstance(MaterialManager materialManager, LaserEmitterBlockEntity blockEntity) {
		super(materialManager, blockEntity);
	}

	@Override
	public void beginFrame() {}

	@Override
	protected Instancer<RotatingData> getModel() {
		BlockState referenceState = blockEntity.getBlockState();
		Direction facing = referenceState.getValue(BlockStateProperties.FACING);
		return getRotatingMaterial().getModel(AllPartialModels.LASER_EMITTER_COG, referenceState, facing);
	}
}
