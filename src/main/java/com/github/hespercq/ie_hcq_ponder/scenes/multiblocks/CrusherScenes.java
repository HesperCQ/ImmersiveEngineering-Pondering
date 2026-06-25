package com.github.hespercq.ie_hcq_ponder.scenes.multiblocks;

import com.github.hespercq.ie_hcq_ponder.compat.Mods;
import com.github.hespercq.ie_hcq_ponder.foundation.IESceneBuilder;

import net.createmod.ponder.api.element.ElementLink;
import net.createmod.ponder.api.element.WorldSectionElement;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class CrusherScenes {

  public static void forming(SceneBuilder builder, SceneBuildingUtil util) {
    final Mods IE = Mods.IE;

    final String sceneId = "crusher/forming";
    final String sceneTitle = "Forming the Metal Press Multiblock";
    final ResourceLocation multiBlockRL = IE.rl("multiblocks/crusher");

    final Selection multiBlockSelection = util.select().fromTo(1, 4, 2, 5, 6, 4);
    final Vec3 multiBlockOffset = util.vector().of(0, -3, 0);

    final BlockPos templateOrigin = util.grid().at(1, 1, 2);
    final int basePlateSize = 7;
    final int layerDelay = 20;
    final int blockDelay = 2;

    // Init Scene
    IESceneBuilder scene = new IESceneBuilder(builder.getScene());
    scene.title(sceneId, sceneTitle);
    scene.configureBasePlate(0, 0, basePlateSize);
    scene.showBasePlate();
    scene.idle(20);

    // Assemble + Form
    Selection multiBlockTemplate = scene.assembleMultiblockTemplate(multiBlockRL, templateOrigin,
        layerDelay,
        blockDelay);
    // Simulate Form Result
    scene.world().replaceBlocks(multiBlockTemplate, Blocks.AIR.defaultBlockState(), true);
    ElementLink<WorldSectionElement> multiBlockElement = scene.world()
        .showIndependentSectionImmediately(multiBlockSelection);
    scene.world().moveSection(multiBlockElement, multiBlockOffset, 0);
    scene.idle(30);

    scene.markAsFinished();
  }

}
