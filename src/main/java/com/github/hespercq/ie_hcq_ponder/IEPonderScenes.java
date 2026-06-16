package com.github.hespercq.ie_hcq_ponder;

import com.github.hespercq.ie_hcq_ponder.compat.Mods;
import com.github.hespercq.ie_hcq_ponder.scenes.CokeOvenScenes;
import com.github.hespercq.ie_hcq_ponder.scenes.ConveyorScenes;

import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class IEPonderScenes {
  public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
    final Mods IE = Mods.IE;
    PonderSceneRegistrationHelper<String> ieHelper = helper.withKeyFunction(IE::rl);

    ieHelper.forComponents("conveyor_basic")
        .addStoryBoard("conveyors/standard", ConveyorScenes::conveyor, IEPonderTags.LOGISTICS);
    ieHelper.forComponents("cokebrick")
        .addStoryBoard("coke_oven/forming", CokeOvenScenes::forming, IEPonderTags.MULTI_BLOCKS);
  }
}
