package com.github.hespercq.ie_hcq_ponder.foundation.instruction;

import com.github.hespercq.ie_hcq_ponder.foundation.element.GUIElement;

import net.createmod.ponder.foundation.PonderScene;
import net.createmod.ponder.foundation.instruction.FadeInOutInstruction;

public class ShowGUIInstruction extends FadeInOutInstruction {

	private final GUIElement element;

	public ShowGUIInstruction(GUIElement element, int ticks) {
		super(ticks);
		this.element = element;
	}

	@Override
	protected void show(PonderScene scene) {
		scene.addElement(element);
		element.setVisible(true);
	}

	@Override
	protected void hide(PonderScene scene) {
		element.setVisible(false);
	}

	@Override
	protected void applyFade(PonderScene scene, float fade) {
		element.setFade(fade);
	}

}
