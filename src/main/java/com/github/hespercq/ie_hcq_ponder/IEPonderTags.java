package com.github.hespercq.ie_hcq_ponder;

import com.github.hespercq.ie_hcq_ponder.compat.Mods;

import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class IEPonderTags {

    // STATIC FIELDS
    public static final ResourceLocation LOGISTICS = IEPonder.rl("logistics");
    public static final ResourceLocation ELECTRIC_RELAYS = IEPonder.rl("electric_relays");
    public static final ResourceLocation ELECTRIC_SOURCES = IEPonder.rl("electric_sources");
    public static final ResourceLocation ELECTRIC_APPLIANCES = IEPonder.rl("electric_appliances");
    public static final ResourceLocation FLUIDS = IEPonder.rl("fluids");
    public static final ResourceLocation MULTI_BLOCKS = IEPonder.rl("multi_blocks");

    // PUBLIC METHODS
    public static void register(PonderTagRegistrationHelper<ResourceLocation> helper) {
        final Mods IE = Mods.IE;
        PonderTagRegistrationHelper<String> ieHelper = helper.withKeyFunction(IE::rl);

        helper.registerTag(LOGISTICS).addToIndex().item(IE.getItem("conveyor_basic"), true, false)
                .title("Item Transportation").description("Components which help moving items abatound")
                .register();

        ieHelper.addToTag(LOGISTICS).add(
                "conveyor_basic")
                .add("conveyor_dropper")
                .add("conveyor_extract")
                .add("conveyor_redstone")
                .add("conveyor_splitter")
                .add("conveyor_vertical")
                .add("sorter")
                .add("item_batcher")
                .add("electromagnet");
        
        // TODO: Check how it works with multiblock item
    }
}
