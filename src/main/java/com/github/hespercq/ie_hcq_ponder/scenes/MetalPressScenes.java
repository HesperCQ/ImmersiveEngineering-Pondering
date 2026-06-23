package com.github.hespercq.ie_hcq_ponder.scenes;

import com.github.hespercq.ie_hcq_ponder.compat.Mods;
import com.github.hespercq.ie_hcq_ponder.foundation.IESceneBuilder;

import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

public class MetalPressScenes {
    
  public static void forming(SceneBuilder builder, SceneBuildingUtil util) {
    IESceneBuilder scene = new IESceneBuilder(builder.getScene());
    final Mods IE = Mods.IE;
    scene.title("metal_press/forming", "Forming the Metal Press Multiblock");
    scene.configureBasePlate(0, 0, 5);
    scene.world().showSection(util.select().layer(0), Direction.UP);

    BlockPos origin = util.grid().at(1, 1, 2);
    scene.assembleMultiblockTemplate(IE.rl("multiblocks/metal_press"), origin, 30, 0);
    scene.idle(30);

    scene.markAsFinished();
  }
}
