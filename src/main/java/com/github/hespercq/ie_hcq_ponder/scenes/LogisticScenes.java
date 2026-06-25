package com.github.hespercq.ie_hcq_ponder.scenes;

import com.github.hespercq.ie_hcq_ponder.compat.Mods;
import com.github.hespercq.ie_hcq_ponder.foundation.IESceneBuilder;

import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.core.Direction;

public class LogisticScenes {

  public static void conveyor(SceneBuilder builder, SceneBuildingUtil util) {
    IESceneBuilder scene = new IESceneBuilder(builder.getScene());
    final Mods IE = Mods.IE;

    scene.title("conveyor", "Transporting Objects using Conveyors");
    scene.configureBasePlate(0, 0, 7);
    scene.showBasePlate();
    scene.idle(10);
    scene.overlay().showText(1000)
        .placeNearTarget()
        .text("Conveyers will move items that are dropped on them.")
        .pointAt(util.vector().of(3.5, 1, 3.5));
    scene.idle(20);
    scene.idle(20);
    scene.markAsFinished();
  }

}