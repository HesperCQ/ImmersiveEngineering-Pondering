package com.github.hespercq.ie_hcq_ponder;

import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler;
import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler.IMultiblock;
import net.createmod.ponder.api.level.PonderLevel;
import net.createmod.ponder.foundation.PonderScene;
import net.createmod.ponder.foundation.PonderSceneBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate.StructureBlockInfo;

public class IESceneBuilder extends PonderSceneBuilder {

  private final EffectInstructions effects;
  private final WorldInstructions world;

  public IESceneBuilder(PonderScene ponderScene) {
    super(ponderScene);
    effects = new EffectInstructions();
    world = new WorldInstructions();
  }

  public EffectInstructions effects() {
    return effects;
  }

  public WorldInstructions world() {
    return world;
  }

  public class WorldInstructions extends PonderWorldInstructions {
    public void placeMultiblockStructure(ResourceLocation uniqueName, BlockPos origin) {
      IMultiblock multiBlock = MultiblockHandler.getByUniqueName(uniqueName);
      PonderLevel world = scene.getWorld();
      for (StructureBlockInfo block : multiBlock.getStructure(world)) {
        setBlock(
            origin.offset(block.pos()),
            block.state(),
            false);
      }
    }
  }

  public class EffectInstructions extends PonderEffectInstructions {

  }
}
