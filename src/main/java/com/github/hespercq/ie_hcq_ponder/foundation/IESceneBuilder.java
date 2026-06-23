package com.github.hespercq.ie_hcq_ponder.foundation;

import com.github.hespercq.ie_hcq_ponder.IEPonder;
import com.github.hespercq.ie_hcq_ponder.compat.Mods;
import com.github.hespercq.ie_hcq_ponder.foundation.element.GUIElement;
import com.github.hespercq.ie_hcq_ponder.foundation.instruction.ShowGUIInstruction;

import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler;
import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler.IMultiblock;
import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.level.PonderLevel;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.createmod.ponder.foundation.PonderScene;
import net.createmod.ponder.foundation.PonderSceneBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate.StructureBlockInfo;
import net.minecraft.world.phys.Vec3;

public class IESceneBuilder extends PonderSceneBuilder {

  private final EffectInstructions effects;
  private final WorldInstructions world;
  private final OverlayInstructions overlay;

  public IESceneBuilder(PonderScene ponderScene) {
    super(ponderScene);
    effects = new EffectInstructions();
    world = new WorldInstructions();
    overlay = new OverlayInstructions();
  }

  public EffectInstructions effects() {
    return effects;
  }

  public WorldInstructions world() {
    return world;
  }

  public OverlayInstructions overlay() {
    return overlay;
  }

  public class WorldInstructions extends PonderWorldInstructions {

  }

  public class EffectInstructions extends PonderEffectInstructions {

  }

  public class OverlayInstructions extends PonderOverlayInstructions {
    public GUIElement showGUI(ResourceLocation texture, int duration) {
      GUIElement guiElement = new GUIElement(texture);
      addInstruction(new ShowGUIInstruction(guiElement, duration));
      return guiElement;
    }
  }

  public Selection assembleMultiblockTemplate(ResourceLocation uniqueName, BlockPos origin, int layerDelay,
      int blockDelay) {
    final Mods IE = Mods.IE;

    PonderSceneBuilder builder = this;
    PonderLevel world = scene.getWorld();
    SceneBuildingUtil util = scene.getSceneBuildingUtil();

    IMultiblock multiBlock = MultiblockHandler.getByUniqueName(uniqueName);
    Vec3i multiBlockSize = multiBlock.getSize(world);

    // Get Absolute Trigger Block Pos from origin and rotated offset
    BlockPos multiBlockTriggerPos = origin.offset(
        multiBlockSize.getX() - 1 - multiBlock.getTriggerOffset().getX(),
        multiBlock.getTriggerOffset().getY(),
        multiBlockSize.getZ() - 1 - multiBlock.getTriggerOffset().getZ());

    Selection multiBlockSelection = util.select().cuboid(origin, multiBlockSize.offset(-1, -1, -1));
    Vec3 multiBlockSelectionTextAnchor = multiBlockSelection.getCenter().add(0.5, multiBlockSize.getY() / 2, 0.5);

    // Set blocks rotated 180° so they face the camera
    for (StructureBlockInfo block : multiBlock.getStructure(world)) {
      final BlockPos relativePos = new BlockPos(
          multiBlockSize.getX() - 1 - block.pos().getX(),
          block.pos().getY(),
          multiBlockSize.getZ() - 1 - block.pos().getZ());
      final BlockPos absolutePos = origin.offset(relativePos);
      final BlockState blockState = block.state().rotate(world, absolutePos, Rotation.CLOCKWISE_180);

      builder.world().setBlock(
          absolutePos,
          blockState,
          false);
    }

    // Show outline with text for build duration + 1 Delays (before first)
    builder.overlay().showOutlineWithText(multiBlockSelection, (multiBlockSize.getY() + 1) * layerDelay)
        .placeNearTarget().sharedText(IEPonder.rl("multiblock_forming_place"))
        .pointAt(multiBlockSelectionTextAnchor);
    builder.idle(layerDelay);

    for (int layerIndex = origin.getY(); layerIndex < origin.getY() + multiBlockSize.getY(); layerIndex++) {
      builder.world().showSection(util.select().layer(layerIndex), Direction.DOWN);
      builder.idle(layerDelay);
      builder.addKeyframe();
    }

    builder.idle(layerDelay);

    builder.overlay().showOutlineWithText(util.select().position(multiBlockTriggerPos), 3 * layerDelay)
        .placeNearTarget().sharedText(IEPonder.rl("multiblock_forming_hammer"))
        .pointAt(util.vector().blockSurface(multiBlockTriggerPos, Direction.NORTH))
        .attachKeyFrame();
    builder.idle(2 * layerDelay);

    builder.overlay()
        .showControls(util.vector().blockSurface(multiBlockTriggerPos, Direction.NORTH), Pointing.DOWN, 30)
        .withItem(new ItemStack(IE.getItem("hammer")))
        .rightClick();
    builder.idle(layerDelay);

    multiBlock.createStructure(world, multiBlockTriggerPos, Direction.NORTH, null);

    return multiBlockSelection;

  }

}
