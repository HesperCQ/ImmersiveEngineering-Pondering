package com.github.hespercq.ie_hcq_ponder.foundation.element;

import net.createmod.ponder.foundation.element.AnimatedOverlayElementBase;

import java.util.ArrayList;
import java.util.List;

import net.createmod.ponder.foundation.PonderScene;
import net.createmod.ponder.foundation.ui.PonderUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class GUIElement extends AnimatedOverlayElementBase {
  private final ResourceLocation texture;
  private final int width = 176;
  private final int height = 166;
  private final int textureWidth = 256;
  private final int textureHeight = 256;

  private int age = 0;
  private boolean visible = true;
  private final List<Entry> entries = new ArrayList<>();

  public GUIElement(ResourceLocation texture) {
    this.texture = texture;
  }

  public GUIElement addItem(ItemStack stack, int x, int y, int startTick, int duration) {
      System.out.println("HCQ - ADD ITEM");
    entries.add(new ItemEntry(stack, x, y, startTick, startTick + duration));
    return this;
  }

  public GUIElement addText(String text, int x, int y, int startTick, int duration) {
      System.out.println("HCQ - ADD TEXT");
    entries.add(new TextEntry(text, x, y, startTick, startTick + duration));
    return this;
  }

  @Override
  public boolean isVisible() {
    return visible;
  }

  @Override
  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  @Override
  public void render(PonderScene scene, PonderUI screen,
      GuiGraphics graphics,
      float partialTicks,
      float fade) {

    if (!visible)
      return;

    age++;

    int x0 = (screen.width - width) / 2;
    int y0 = (screen.height - height) / 2;

    // GUI background
    graphics.blit(texture, x0, y0, 0, 0,
        width, height, textureWidth, textureHeight);

    // Render timeline entries
    for (Entry e : entries) {

      if (age < e.start || age > e.end)
        continue;

      float localFade = fade;

      e.render(graphics, localFade);
    }
  }

  // ----------------------------
  // Timeline entry system
  // ----------------------------

  private static abstract class Entry {
    final int x, y;
    final int start;
    final int end;

    Entry(int x, int y, int start, int end) {
      this.x = x;
      this.y = y;
      this.start = start;
      this.end = end;
    }

    abstract void render(GuiGraphics g, float fade);
  }

  private static class ItemEntry extends Entry {
    final ItemStack stack;

    ItemEntry(ItemStack stack, int x, int y, int start, int end) {
      super(x, y, start, end);
      this.stack = stack;
    }

    @Override
    void render(GuiGraphics g, float fade) {
      System.out.println("HCQ - RENDER ITEM");
      g.setColor(1, 1, 1, fade);
      g.renderItem(stack, x, y);
      g.renderItemDecorations(Minecraft.getInstance().font, stack, x, y);
      g.setColor(1, 1, 1, 1);
    }
  }

  private static class TextEntry extends Entry {
    final String text;

    TextEntry(String text, int x, int y, int start, int end) {
      super(x, y, start, end);
      this.text = text;
    }

    @Override
    void render(GuiGraphics g, float fade) {
      System.out.println("HCQ - RENDER TEXT");
      g.setColor(1, 1, 1, fade);
      g.drawString(Minecraft.getInstance().font, text, x, y, 0x404040, false);
      g.setColor(1, 1, 1, 1);
    }
  }

}
