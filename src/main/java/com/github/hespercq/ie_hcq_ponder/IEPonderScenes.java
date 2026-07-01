package com.github.hespercq.ie_hcq_ponder;

import com.github.hespercq.ie_hcq_ponder.compat.Mods;
import com.github.hespercq.ie_hcq_ponder.scenes.LogisticScenes;
import com.github.hespercq.ie_hcq_ponder.scenes.multiblocks.AdvancedBlastFurnaceScenes;
import com.github.hespercq.ie_hcq_ponder.scenes.multiblocks.AlloySmelterScenes;
import com.github.hespercq.ie_hcq_ponder.scenes.multiblocks.AssemblerScenes;
import com.github.hespercq.ie_hcq_ponder.scenes.multiblocks.AutoWorkbenchScenes;
import com.github.hespercq.ie_hcq_ponder.scenes.multiblocks.BlastFurnaceScenes;
import com.github.hespercq.ie_hcq_ponder.scenes.multiblocks.CokeOvenScenes;
import com.github.hespercq.ie_hcq_ponder.scenes.multiblocks.CrusherScenes;
import com.github.hespercq.ie_hcq_ponder.scenes.multiblocks.MetalPressScenes;
import com.github.hespercq.ie_hcq_ponder.scenes.multiblocks.SiloScenes;
import com.github.hespercq.ie_hcq_ponder.scenes.multiblocks.TankScenes;

import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class IEPonderScenes {
        public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
                final Mods IE = Mods.IE;
                PonderSceneRegistrationHelper<String> ieHelper = helper.withKeyFunction(IE::rl);

                ieHelper.forComponents("conveyor_basic")
                                .addStoryBoard("conveyors/standard",
                                                LogisticScenes::conveyor,
                                                IEPonderTags.LOGISTICS);
                // ===========================================================================================
                // #region Multiblocks

                ieHelper.forComponents("coke_oven", "cokebrick")
                                .addStoryBoard("coke_oven/forming",
                                                CokeOvenScenes::forming,
                                                IEPonderTags.MULTI_BLOCKS)
                                .addStoryBoard("coke_oven/operating",
                                                CokeOvenScenes::operating,
                                                IEPonderTags.MULTI_BLOCKS);

                ieHelper.forComponents("blast_furnace")
                                .addStoryBoard("blast_furnace/forming",
                                                BlastFurnaceScenes::forming,
                                                IEPonderTags.MULTI_BLOCKS);

                ieHelper.forComponents("advanced_blast_furnace")
                                .addStoryBoard("advanced_blast_furnace/forming",
                                                AdvancedBlastFurnaceScenes::forming,
                                                IEPonderTags.MULTI_BLOCKS);

                ieHelper.forComponents("alloy_smelter")
                                .addStoryBoard("alloy_smelter/forming",
                                                AlloySmelterScenes::forming,
                                                IEPonderTags.MULTI_BLOCKS);

                ieHelper.forComponents("crusher")
                                .addStoryBoard("crusher/forming",
                                                CrusherScenes::forming,
                                                IEPonderTags.ELECTRIC_APPLIANCES, IEPonderTags.MULTI_BLOCKS);

                ieHelper.forComponents("metal_press")
                                .addStoryBoard("metal_press/forming",
                                                MetalPressScenes::forming,
                                                IEPonderTags.ELECTRIC_APPLIANCES, IEPonderTags.MULTI_BLOCKS);

                ieHelper.forComponents("assembler")
                                .addStoryBoard("assembler/forming",
                                                AssemblerScenes::forming,
                                                IEPonderTags.ELECTRIC_APPLIANCES, IEPonderTags.MULTI_BLOCKS);

                ieHelper.forComponents("auto_workbench")
                                .addStoryBoard("auto_workbench/forming",
                                                AutoWorkbenchScenes::forming,
                                                IEPonderTags.ELECTRIC_APPLIANCES, IEPonderTags.MULTI_BLOCKS);

                ieHelper.forComponents("silo")
                                .addStoryBoard("silo/forming",
                                                SiloScenes::forming,
                                                IEPonderTags.MULTI_BLOCKS);

                ieHelper.forComponents("tank")
                                .addStoryBoard("tank/forming",
                                                TankScenes::forming,
                                                IEPonderTags.MULTI_BLOCKS);
                // #endregion
        }
}
