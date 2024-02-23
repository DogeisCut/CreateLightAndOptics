package com.dogeiscut.loptics.content.optics.laserEmitter;

import com.jozufozu.flywheel.api.Instancer;
import com.jozufozu.flywheel.api.MaterialManager;
import com.dogeiscut.loptics.AllPartialModels;
import com.jozufozu.flywheel.api.instance.DynamicInstance;
import com.simibubi.create.content.kinetics.base.SingleRotatingInstance;
import com.simibubi.create.content.kinetics.base.flwdata.RotatingData;

import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;


public class LaserEmitterCogInstance extends SingleRotatingInstance<LaserEmitterBlockEntity> implements DynamicInstance {

	public LaserEmitterCogInstance(MaterialManager materialManager, LaserEmitterBlockEntity blockEntity) {
		super(materialManager, blockEntity);
	}

	@Override
	public void beginFrame() {}

	@Override
	protected Instancer<RotatingData> getModel() {
		BlockState referenceState = blockEntity.getCachedState();
		Direction facing = referenceState.get(Properties.FACING);
		return getRotatingMaterial().getModel(AllPartialModels.LASER_EMITTER_COG, referenceState, facing);
	}
}
