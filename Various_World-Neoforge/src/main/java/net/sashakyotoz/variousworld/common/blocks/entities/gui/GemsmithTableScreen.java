package net.sashakyotoz.variousworld.common.blocks.entities.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.MapColor;
import net.sashakyotoz.variousworld.VariousWorld;

public class GemsmithTableScreen extends AbstractContainerScreen<GemsmithTableMenu> {
    private final Level level;
    private final Player player;

    public GemsmithTableScreen(GemsmithTableMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.level = inventory.player.level();
        this.player = inventory.player;
        this.imageWidth = 174;
        this.imageHeight = 164;
    }

    public static final ResourceLocation BACKGROUND_LOCATION = VariousWorld.createVWLocation("textures/gui/gemsmith_table.png");
    private static final ResourceLocation LIT_PROGRESS = ResourceLocation.withDefaultNamespace("container/furnace/lit_progress");
    private static final ResourceLocation BURN_PROGRESS = ResourceLocation.withDefaultNamespace("container/furnace/burn_progress");

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND_LOCATION, this.leftPos, this.topPos, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);
        renderIconsProgress(guiGraphics);
    }

    private void renderIconsProgress(GuiGraphics graphics) {
        if (getMenu().isCrafting())
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, BURN_PROGRESS, 22, 16, 22-getMenu().getScaledProgress(), 0, this.leftPos + 85, this.topPos + 48, getMenu().getScaledProgress(), 16);
        if (getMenu().getLitProgress() > 0)
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, LIT_PROGRESS, 14, Math.round(14 * getMenu().getLitProgress()), 0, 14-Math.round(14 * getMenu().getLitProgress()), this.leftPos + 90, this.topPos + 66, 14, Math.round(14 * getMenu().getLitProgress()));
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            this.minecraft.player.closeContainer();
            return true;
        }
        return super.keyPressed(key, b, c);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        if (Minecraft.getInstance().getLanguageManager().getSelected().equals("en_us"))
            guiGraphics.drawString(this.font, Component.translatable("block.various_world.gemsmith_table"), 56, 4, MapColor.COLOR_GRAY.col, false);
    }
}