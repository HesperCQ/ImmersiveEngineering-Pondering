package com.github.hespercq.ie_hcq_ponder;

import com.github.hespercq.ie_hcq_ponder.compat.Mods;
import com.github.hespercq.ie_hcq_ponder.scenes.CokeOvenScenes;
import com.github.hespercq.ie_hcq_ponder.scenes.ConveyorScenes;
import com.github.hespercq.ie_hcq_ponder.scenes.CrusherScenes;
import com.github.hespercq.ie_hcq_ponder.scenes.MetalPressScenes;

import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class IEPonderScenes {
    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        final Mods IE = Mods.IE;
        PonderSceneRegistrationHelper<String> ieHelper = helper.withKeyFunction(IE::rl);

        ieHelper.forComponents("conveyor_basic")
                .addStoryBoard("conveyors/standard",
                        ConveyorScenes::conveyor,
                        IEPonderTags.LOGISTICS);

        ieHelper.forComponents("coke_oven", "cokebrick")
                .addStoryBoard("coke_oven/forming",
                        CokeOvenScenes::forming,
                        IEPonderTags.MULTI_BLOCKS)
                .addStoryBoard("coke_oven/operating",
                        CokeOvenScenes::operating,
                        IEPonderTags.MULTI_BLOCKS);

        ieHelper.forComponents("crusher")
                .addStoryBoard("coke_oven/forming",
                        CrusherScenes::forming,
                        IEPonderTags.ELECTRIC_APPLIANCES, IEPonderTags.MULTI_BLOCKS);

        ieHelper.forComponents("metal_press")
                .addStoryBoard("coke_oven/forming",
                        MetalPressScenes::forming,
                        IEPonderTags.ELECTRIC_APPLIANCES, IEPonderTags.MULTI_BLOCKS);
    }
}
