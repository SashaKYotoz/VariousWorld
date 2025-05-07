package net.sashakyotoz.variousworld.common.blocks.entities.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
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

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTicks);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        guiGraphics.blit(BACKGROUND_LOCATION, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        renderIconsProgress(guiGraphics);
        RenderSystem.disableBlend();
    }

    private void renderIconsProgress(GuiGraphics graphics) {
        if (getMenu().isCrafting())
            graphics.blit(BACKGROUND_LOCATION, this.leftPos + 85, this.topPos + 48, 176, 49, getMenu().getScaledProgress(), 16);
        if (getMenu().isLit()) {
            int l = Mth.ceil(this.menu.getLitProgress() * 14.0F);
            int k = l - 14;
            VariousWorld.LOGGER.info(k);
            graphics.blit(BACKGROUND_LOCATION, this.leftPos + 90, this.topPos + 66 - k, 176, 66 - k, 14, 14);
        }
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
