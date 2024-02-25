package com.dogeiscut.loptics.infastructure.ponder.scenes;

import com.dogeiscut.loptics.content.optics.laserEmitter.LaserEmitterBlockEntity;
import com.simibubi.create.foundation.ponder.ElementLink;
import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;

import com.simibubi.create.foundation.ponder.Selection;
import com.simibubi.create.foundation.ponder.element.EntityElement;

import io.github.fabricators_of_create.porting_lib.util.NBTSerializer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class LaserEmitterScenes {

	public static void powering(SceneBuilder scene, SceneBuildingUtil util) {
		scene.title("powering", "Powering Laser Emitters");
		scene.configureBasePlate(0, 0, 5);
		scene.showBasePlate();
		scene.idle(20);
		Selection laserEmit = util.select.position(3, 1, 2);
		scene.world.modifyBlockEntityNBT(laserEmit, LaserEmitterBlockEntity.class,
				nbt -> nbt.putBoolean("Active", false));
		scene.world.showSection(laserEmit, Direction.DOWN);
		scene.overlay.showText(60)
				.text("A Laser Emitter requires Cog or Shaft power")
				.attachKeyFrame()
				.placeNearTarget()
				.pointAt(util.vector.topOf(3, 1, 2));
		scene.idle(60);
		scene.world.showSection(util.select.fromTo(2, 1, 2, 1, 1, 2), Direction.DOWN);
		scene.world.modifyBlockEntityNBT(laserEmit, LaserEmitterBlockEntity.class,
				nbt -> nbt.putBoolean("Active", true));
		scene.idle(70);
		scene.world.showSection(util.select.fromTo(3, 2, 2, 3, 3, 2), Direction.DOWN);
		scene.overlay.showText(90)
				.text("Because Laser Emitters can also take Cog power, they can power each other in a chain")
				.attachKeyFrame()
				.placeNearTarget()
				.pointAt(util.vector.topOf(3, 3, 2));
		scene.idle(90);
	}

	public static void redirecting(SceneBuilder scene, SceneBuildingUtil util) {
		scene.title("redirecting", "Redirecting Lasers");
		scene.configureBasePlate(0, 0, 5);
		scene.showBasePlate();
		scene.world.showSection(util.select.fromTo(0, 1, 0, 4, 5, 4), Direction.DOWN);
		scene.idle(90);
	}

	public static void combiningPower(SceneBuilder scene, SceneBuildingUtil util) {
		scene.title("combining_power", "Combining Laser Power");
		scene.configureBasePlate(0, 0, 5);
		scene.showBasePlate();
		scene.world.showSection(util.select.fromTo(0, 1, 0, 4, 5, 4), Direction.DOWN);
		scene.idle(90);
	}

	public static void laserCutting(SceneBuilder scene, SceneBuildingUtil util) {
		scene.title("laser_cutting", "Laser Cutting");
		scene.configureBasePlate(0, 0, 5);
		scene.showBasePlate();
		scene.world.showSection(util.select.fromTo(0, 1, 0, 4, 5, 4), Direction.DOWN);

		scene.idle(90);

		ItemStack stack = new ItemStack(Items.IRON_INGOT);
		ElementLink<EntityElement> item =
				scene.world.createItemEntity(util.vector.centerOf(4, 4, 2), util.vector.of(0, 0, 0), stack);
		scene.idle(13);
		scene.world.modifyEntity(item, Entity::discard);
		BlockPos beltEnd = util.grid.at(4, 1, 2);
		scene.world.createItemOnBelt(beltEnd, Direction.DOWN, stack);

		scene.idle(90);
	}
}
