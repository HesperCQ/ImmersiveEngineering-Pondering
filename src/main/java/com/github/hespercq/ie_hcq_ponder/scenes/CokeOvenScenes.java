package com.github.hespercq.ie_hcq_ponder.scenes;

import com.github.hespercq.ie_hcq_ponder.IEPonder;
import com.github.hespercq.ie_hcq_ponder.IESceneBuilder;
import com.github.hespercq.ie_hcq_ponder.compat.Mods;

import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.element.ElementLink;
import net.createmod.ponder.api.element.WorldSectionElement;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

public class CokeOvenScenes {

  public static void forming(SceneBuilder builder, SceneBuildingUtil util) {
    IESceneBuilder scene = new IESceneBuilder(builder.getScene());
    final Mods IE = Mods.IE;
    scene.title("coke_oven/forming", "Forming the Coke Oven Multiblock");
    scene.configureBasePlate(0, 0, 5);
    scene.world().showSection(util.select().layer(0), Direction.UP);

    Selection coke_oven = util.select().fromTo(1, 4, 1, 3, 6, 3);
    Selection coke_oven_template = util.select().fromTo(1, 1, 1, 3, 3, 3);
    BlockPos coke_oven_hammer_pos = util.grid().at(2, 2, 1);

    scene.world().placeMultiblockStructure(IE.rl("multiblocks/coke_oven"), util.grid().at(1, 1, 1));

    scene.idle(20);

    scene.overlay().showOutlineWithText(coke_oven_template, 40 + (3 * 30))
        .placeNearTarget().sharedText(IEPonder.rl("multiblock_forming_place"))
        .pointAt(util.vector().blockSurface(util.grid().at(2, 3, 2), Direction.UP));
    scene.idle(40);

    scene.world().showSection(util.select().layer(1), Direction.DOWN);
    scene.idle(30);

    scene.addKeyframe();
    scene.world().showSection(util.select().layer(2), Direction.DOWN);
    scene.idle(30);

    scene.addKeyframe();
    scene.world().showSection(util.select().layer(3), Direction.DOWN);
    scene.idle(30);

    scene.addKeyframe();
    scene.idle(20);


    scene.overlay().showOutlineWithText(util.select().position(coke_oven_hammer_pos), 100)
        .placeNearTarget().sharedText(IEPonder.rl("multiblock_forming_hammer"))
        .pointAt(util.vector().blockSurface(util.grid().at(2, 2, 1), Direction.NORTH))
        .attachKeyFrame();
    scene.idle(60);

    scene.overlay()
        .showControls(util.vector().blockSurface(coke_oven_hammer_pos, Direction.NORTH), Pointing.DOWN, 30)
        .withItem(new ItemStack(IE.getItem("hammer")))
        .rightClick();
    scene.idle(20);

    scene.world().replaceBlocks(coke_oven_template, Blocks.AIR.defaultBlockState(), true);
    ElementLink<WorldSectionElement> coke_oven_element = scene.world().showIndependentSectionImmediately(coke_oven);
    scene.world().moveSection(coke_oven_element, util.vector().of(0, -3, 0), 0);
    scene.idle(30);

    scene.markAsFinished();
  }

}