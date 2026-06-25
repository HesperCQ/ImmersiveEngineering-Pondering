package com.github.hespercq.ie_hcq_ponder.scenes.multiblocks;

import com.github.hespercq.ie_hcq_ponder.IEPonder;
import com.github.hespercq.ie_hcq_ponder.compat.Mods;
import com.github.hespercq.ie_hcq_ponder.foundation.IESceneBuilder;

import blusunrize.immersiveengineering.api.IEProperties;
import net.createmod.catnip.math.Pointing;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.element.ElementLink;
import net.createmod.ponder.api.element.EntityElement;
import net.createmod.ponder.api.element.WorldSectionElement;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.phys.Vec3;

public class CokeOvenScenes {

        public static void forming(SceneBuilder builder, SceneBuildingUtil util) {
                final Mods IE = Mods.IE;

                final String sceneId = "coke_oven/forming";
                final String sceneTitle = "Forming the Coke Oven Multiblock";
                final ResourceLocation multiBlockRL = IE.rl("multiblocks/coke_oven");

                final Selection multiBlockSelection = util.select().fromTo(1, 4, 1, 3, 6, 3);
                final Vec3 multiBlockOffset = util.vector().of(0, -3, 0);

                final BlockPos templateOrigin = util.grid().at(1, 1, 1);
                final int basePlateSize = 5;

                final int layerDelay = 20;
                final int blockDelay = 2;

                // Init Scene
                IESceneBuilder scene = new IESceneBuilder(builder.getScene());
                scene.title(sceneId, sceneTitle);
                scene.configureBasePlate(0, 0, basePlateSize);
                scene.world().showSection(util.select().layer(0), Direction.UP);
                scene.idle(20);

                // Assemble + Form
                Selection multiBlockTemplate = scene.assembleMultiblockTemplate(multiBlockRL, templateOrigin,
                                layerDelay,
                                blockDelay);
                // Simulate Form Result
                scene.world().replaceBlocks(multiBlockTemplate, Blocks.AIR.defaultBlockState(), true);
                ElementLink<WorldSectionElement> multiBlockElement = scene.world()
                                .showIndependentSectionImmediately(multiBlockSelection);
                scene.world().moveSection(multiBlockElement, multiBlockOffset, 0);
                scene.idle(30);

                scene.markAsFinished();
        }

        public static void operating(SceneBuilder builder, SceneBuildingUtil util) {
                IESceneBuilder scene = new IESceneBuilder(builder.getScene());
                final Mods IE = Mods.IE;
                scene.title("coke_oven/operating", "Operating the Coke Oven");
                scene.configureBasePlate(0, 0, 7);
                scene.world().showSection(util.select().layer(0), Direction.UP);

                Selection coke_oven = util.select().fromTo(3, 1, 2, 5, 3, 4);
                Vec3 coke_oven_front = util.vector().blockSurface(util.grid().at(4, 2, 2), Direction.NORTH);
                Vec3 coke_oven_side = util.vector().blockSurface(util.grid().at(3, 2, 3), Direction.WEST);

                Selection input_scaffolding = util.select().fromTo(0, 1, 4, 2, 2, 6);
                Selection input_conveyors = util.select().fromTo(2, 3, 4, 2, 3, 6)
                                .add(util.select().fromTo(3, 4, 4, 5, 4, 4));
                Selection input_hopper = util.select().position(6, 3, 4);
                Vec3 input_item_spawn = util.vector().centerOf(2, 3, 5);

                Selection output_scaffolding = util.select().fromTo(5, 1, 0, 6, 1, 0)
                                .add(util.select().fromTo(6, 1, 1, 6, 1, 2));
                Selection output_conveyors = util.select().fromTo(3, 1, 0, 4, 1, 1)
                                .add(util.select().fromTo(5, 2, 0, 6, 2, 1))
                                .add(util.select().position(6, 2, 2));
                Vec3 output_item_spawn = util.vector().centerOf(3, 1, 1);

                Selection fluid_scaffolding = util.select().fromTo(0, 1, 3, 2, 1, 3);
                Selection fluid_pipes = util.select().fromTo(0, 2, 3, 2, 3, 3)
                                .add(util.select().fromTo(0, 1, 2, 0, 2, 2));
                BlockPos fluid_lever_pos = util.grid().at(1, 2, 2);
                Selection fluid_lever_section = util.select().position(fluid_lever_pos);

                ItemStack item_stack_coal = new ItemStack(Items.COAL);
                ItemStack item_stack_log = new ItemStack(Items.OAK_LOG);
                ItemStack item_stack_charcoal = new ItemStack(Items.CHARCOAL);
                ItemStack item_stack_coal_coke = new ItemStack(IE.getItem("coal_coke"));
                ItemStack item_stack_bucket = new ItemStack(Items.BUCKET);
                ItemStack item_stack_bucket_creosote = new ItemStack(IE.getItem("creosote_bucket"));

                scene.world().showSection(coke_oven, Direction.DOWN);
                scene.idle(20);

                // GUI
                scene.overlay()
                                .showOutlineWithText(coke_oven, 60)
                                .placeNearTarget().text("Interact with the multiblock to open its GUI.")
                                .pointAt(coke_oven_side);
                scene.idle(20);

                scene.overlay().showControls(coke_oven_front, Pointing.DOWN, 20).rightClick();
                scene.idle(60);

                // GUI Inputs
                scene.overlay()
                                .showOutlineWithText(coke_oven, 200)
                                .placeNearTarget()
                                .text("In the GUI you can input items like coal and logs to create coke coal and charcoal. This process will also create Creosote Oil which will be stored in the Coke Oven.")
                                .pointAt(coke_oven_side)
                                .attachKeyFrame();
                scene.overlay()
                                .showText(200)
                                .placeNearTarget()
                                .pointAt(util.vector().of(0, 0, 0))
                                .sharedText(IEPonder.rl("disclaimer_recipes"))
                                .colored(PonderPalette.RED);
                scene.overlay()
                                .showControls(coke_oven_front.add(1, 1.5, 0),
                                                Pointing.DOWN, 200)
                                .withItem(item_stack_coal);
                scene.overlay()
                                .showControls(coke_oven_front.add(1, 0, 0),
                                                Pointing.DOWN, 200)
                                .withItem(item_stack_coal_coke);

                scene.overlay()
                                .showControls(coke_oven_front.add(-1, 1.5, 0),
                                                Pointing.DOWN, 200)
                                .withItem(item_stack_log);
                scene.overlay()
                                .showControls(coke_oven_front.add(-1, 0, 0),
                                                Pointing.DOWN, 200)
                                .withItem(item_stack_charcoal);
                scene.idle(220);

                // GUI Fluids
                scene.overlay()
                                .showOutlineWithText(coke_oven, 200)
                                .placeNearTarget()
                                .text("If there is enough Creosote Oil in the oven you can put an empty bucket or jerry can into the blue slot to transfer the Oil to the item.")
                                .pointAt(coke_oven_side)
                                .attachKeyFrame();
                scene.overlay()
                                .showControls(coke_oven_front.add(0, 1.5, 0),
                                                Pointing.DOWN, 200)
                                .withItem(item_stack_bucket);
                scene.overlay()
                                .showControls(coke_oven_front.add(0, 0, 0),
                                                Pointing.DOWN, 200)
                                .withItem(item_stack_bucket_creosote);
                scene.idle(220);

                scene.world().modifyBlocks(coke_oven, state -> state.setValue(IEProperties.ACTIVE, true), false);
                scene.idle(20);

                // Automate Input
                scene.world().showSection(input_scaffolding, Direction.DOWN);
                scene.idle(20);

                scene.world().showSection(input_conveyors, Direction.DOWN);
                scene.idle(20);

                scene.world().showSection(input_hopper, Direction.WEST);
                scene.idle(20);

                scene.overlay()
                                .showText(140)
                                .placeNearTarget()
                                .sharedText(IEPonder.rl("multiblock_item_input_all"))
                                .attachKeyFrame();
                scene.idle(20);

                ElementLink<EntityElement> input_item_1 = scene.world().createItemEntity(input_item_spawn,
                                util.vector().of(0, 0, 0), new ItemStack(Items.COAL));
                scene.idle(10);

                ElementLink<EntityElement> input_item_2 = scene.world().createItemEntity(input_item_spawn,
                                util.vector().of(0, 0, 0), new ItemStack(Items.COAL));
                scene.idle(10);

                ElementLink<EntityElement> input_item_3 = scene.world().createItemEntity(input_item_spawn,
                                util.vector().of(0, 0, 0), new ItemStack(Items.OAK_LOG));
                scene.idle(30);

                scene.world().modifyEntity(input_item_1, Entity::discard);
                scene.idle(10);

                scene.world().modifyEntity(input_item_2, Entity::discard);
                scene.idle(35);

                scene.world().modifyEntity(input_item_3, Entity::discard);
                scene.idle(20);

                // Automate Output
                scene.world().showSection(output_scaffolding, Direction.DOWN);
                scene.idle(20);

                scene.world().showSection(output_conveyors, Direction.DOWN);
                scene.idle(20);

                scene.overlay()
                                .showText(90)
                                .placeNearTarget()
                                .sharedText(IEPonder.rl("multiblock_item_output_all"))
                                .attachKeyFrame();
                scene.idle(20);

                ElementLink<EntityElement> output_item_1 = scene.world().createItemEntity(output_item_spawn,
                                util.vector().of(0, 0, 0), new ItemStack(IE.getItem("coal_coke")));
                scene.idle(65);

                scene.world().modifyEntity(output_item_1, Entity::discard);
                scene.idle(20);

                // Automate Fluids
                scene.world().showSection(fluid_scaffolding, Direction.DOWN);
                scene.idle(20);

                scene.world().showSection(fluid_pipes, Direction.EAST);
                scene.idle(20);

                scene.world().showSection(fluid_lever_section, Direction.SOUTH);
                scene.idle(20);

                scene.world().modifyBlock(fluid_lever_pos, state -> state.setValue(LeverBlock.POWERED, true), false);

                scene.overlay()
                                .showText(100)
                                .placeNearTarget()
                                .sharedText(IEPonder.rl("multiblock_fluid_output_all"))
                                .attachKeyFrame();
                scene.idle(40);

                scene.markAsFinished();
        }

}