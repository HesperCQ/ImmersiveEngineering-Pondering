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

public class AdvancedBlastFurnaceScenes {

  public static void forming(SceneBuilder builder, SceneBuildingUtil util) {
    final Mods IE = Mods.IE;

    final String sceneId = "advanced_blast_furnace/forming";
    final String sceneTitle = "Forming the Improved Blast Furnace Multiblock";
    final ResourceLocation multiBlockRL = IE.rl("multiblocks/improved_blast_furnace");

    final Selection multiBlockSelection = util.select().fromTo(1, 5, 1, 3, 7, 3).add(util.select().position(2, 8, 2));
    final Vec3 multiBlockOffset = util.vector().of(0, -4, 0);

    final BlockPos templateOrigin = util.grid().at(1, 1, 1);
    final int basePlateSize = 5;

    final int layerDelay = 20;
    final int blockDelay = 2;

    // Init Scene
    IESceneBuilder scene = new IESceneBuilder(builder.getScene());
    scene.title(sceneId, sceneTitle);
    scene.configureBasePlate(0, 0, basePlateSize);
    scene.showBasePlate();
    scene.setSceneOffsetY(-2);
    scene.idle(20);

    // Assemble + Form
    Selection multiBlockTemplate = scene.assembleMultiblockTemplate(multiBlockRL, templateOrigin, layerDelay,
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
