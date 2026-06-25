package com.github.hespercq.ie_hcq_ponder;

import com.github.hespercq.ie_hcq_ponder.compat.Mods;

import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class IEPonderTags {

    // STATIC FIELDS
    public static final ResourceLocation LOGISTICS = IEPonder.rl("logistics");
    public static final ResourceLocation ELECTRIC_SOURCES = IEPonder.rl("electric_sources");
    public static final ResourceLocation ELECTRIC_RELAYS = IEPonder.rl("electric_relays");
    public static final ResourceLocation ELECTRIC_APPLIANCES = IEPonder.rl("electric_appliances");
    public static final ResourceLocation FLUIDS = IEPonder.rl("fluids");
    public static final ResourceLocation MULTI_BLOCKS = IEPonder.rl("multi_blocks");

    // PUBLIC METHODS
    public static void register(PonderTagRegistrationHelper<ResourceLocation> helper) {
        final Mods IE = Mods.IE;
        PonderTagRegistrationHelper<String> ieHelper = helper.withKeyFunction(IE::rl);

        helper.registerTag(LOGISTICS).addToIndex().item(IE.getItem("conveyor_basic"), true, false)
                .title("Item Transportation").description("Components which help moving items about")
                .register();

        helper.registerTag(ELECTRIC_SOURCES).addToIndex().item(IE.getItem("dynamo"), true, false)
                .title("Electric Sources").description("Components which generate Energy")
                .register();

        helper.registerTag(ELECTRIC_APPLIANCES).addToIndex().item(IE.getItem("furnace_heater"), true, false)
                .title("Electric Appliances").description("Components which make use of Energy")
                .register();

        helper.registerTag(MULTI_BLOCKS).addToIndex().item(IE.getItem("steel_scaffolding_standard"), true, false)
                .title("Multiblocks").description("Large machines for large factories")
                .register();

        ieHelper.addToTag(LOGISTICS).add(
                "conveyor_basic")
                .add("conveyor_dropper")
                .add("conveyor_extract")
                .add("conveyor_redstone")
                .add("conveyor_splitter")
                .add("conveyor_vertical")
                .add("crate")
                .add("silo")
                .add("sorter")
                .add("electromagnet")
                .add("item_batcher");

        ieHelper.addToTag(ELECTRIC_SOURCES).add(
                "dynamo")
                .add("thermoelectric_generator")
                .add("diesel_generator")
                .add("lightning_rod");

        ieHelper.addToTag(ELECTRIC_APPLIANCES).add(
                "furnace_heater")
                .add("electromagnet")
                .add("fluid_pump")
                .add("cloche")
                .add("charging_station")
                .add("blastfurnace_preheater")
                .add("tesla_coil")
                .add("turret_gun")
                .add("turret_chem")
                .add("sample_drill")
                .add("crusher")
                .add("metal_press")
                .add("assembler")
                .add("auto_workbench")
                .add("squeezer")
                .add("fermenter")
                .add("mixer")
                .add("refinery")
                .add("arc_furnace")
                .add("excavator");

        ieHelper.addToTag(MULTI_BLOCKS)
                .add("coke_oven")
                .add("blast_furnace")
                .add("advanced_blast_furnace")
                .add("alloy_smelter")
                .add("crusher")
                .add("metal_press")
                .add("assembler")
                .add("auto_workbench")
                .add("metal_press")
                .add("silo")
                .add("tank")
                .add("squeezer")
                .add("fermenter")
                .add("mixer")
                .add("refinery")
                .add("diesel_generator")
                .add("lightning_rod")
                .add("arc_furnace")
                .add("excavator");
    }
}
