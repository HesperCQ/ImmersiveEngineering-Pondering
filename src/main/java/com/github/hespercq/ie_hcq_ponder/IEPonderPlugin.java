package com.github.hespercq.ie_hcq_ponder;

import net.createmod.ponder.api.level.PonderLevel;
import net.createmod.ponder.api.registration.IndexExclusionHelper;
import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.createmod.ponder.api.registration.SharedTextRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class IEPonderPlugin implements PonderPlugin {
  @Override
  public String getModId() {
    return IEPonder.MODID;
  }

  @Override
  public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
    IEPonderScenes.register(helper);
  }

  @Override
  public void registerTags(PonderTagRegistrationHelper<ResourceLocation> helper) {
    IEPonderTags.register(helper);
  }

  @Override
  public void registerSharedText(SharedTextRegistrationHelper helper) {
    // Recipes disclaimer
    helper.registerSharedText("disclaimer_recipes", "Recipes can differ if changed by other mods or datapacks.");

    // MB - Forming
    helper.registerSharedText("multiblock_forming_place", "Place all the required blocks.");
    helper.registerSharedText("multiblock_forming_hammer", "Finish forming the multiblock by hammering this spot.");
    // MB - Items
    helper.registerSharedText("multiblock_item_input_all", "Items can be inserted to any side.");
    helper.registerSharedText("multiblock_item_input_point", "Items are inserted here.");
    helper.registerSharedText("multiblock_item_output_all", "Items can be extracted from any side.");
    helper.registerSharedText("multiblock_item_output_auto",
        "Items are automatically pushed to connected conveyors, machines & other blocks that can receive items.");
    
        // MB - Fluids
    helper.registerSharedText("multiblock_fluid_input",
        "Fluids are inserted here.");
    helper.registerSharedText("multiblock_fluid_output_all",
        "Fluids can be extracted from any side.");
    helper.registerSharedText("multiblock_fluid_output_auto",
        "Fluids are automatically pushed to connected compatible pipes, fluid containers & machines.");
    helper.registerSharedText("multiblock_fluid_output_manual",
        "Fluids can be pulled by connected pumps");
    // MB - Redstone Panel
    helper.registerSharedText("multiblock_redstone_panel",
        "The machine will stop if the panel receives a redstone signal.");
    // OTHER
    helper.registerSharedText("screwdriver_invert", "This behaviour can be inverted using a screwdriver.");
    // Hammer InOut
    helper.registerSharedText("hammer_in_out", "Use a hammer to change faces output modes between \"Input\", \"Output\" & \"None\".");
    helper.registerSharedText("hammer_in_out_sneak", "Sneaking while using the hammer will change the output of the opposite side face.");
  }

  @Override
  public void onPonderLevelRestore(PonderLevel ponderLevel) {
    // Unused
  }

  @Override
  public void indexExclusions(IndexExclusionHelper helper) {
    // Unused
  }
}
