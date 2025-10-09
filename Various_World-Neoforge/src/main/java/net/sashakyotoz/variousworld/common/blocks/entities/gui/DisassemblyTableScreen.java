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

public class DisassemblyTableScreen extends AbstractContainerScreen<DisassemblyTableMenu> {
    private final Level level;
    private final Player player;

    public DisassemblyTableScreen(DisassemblyTableMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.level = inventory.player.level();
        this.player = inventory.player;
        this.imageWidth = 174;
        this.imageHeight = 164;
    }

    public static final ResourceLocation BACKGROUND_LOCATION = VariousWorld.createVWLocation("textures/gui/disassembly_table.png");

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
        if (getMenu().isCrafting()) {
            int i = Mth.ceil(this.menu.getScaledProgress() * 17.0F);
            i = Math.min(i, 17);
            graphics.blit(BACKGROUND_LOCATION, this.leftPos + 61, this.topPos + 39, 61, 172, i, 16);
            graphics.blit(BACKGROUND_LOCATION, this.leftPos + 98, this.topPos + 39, 98, 172, i, 16);
        }
        if (getMenu().isFueled()) {
            int l = Mth.ceil(this.menu.getFuelProgress() * 60.0F);
            graphics.blit(BACKGROUND_LOCATION, this.leftPos + 59, this.topPos + 11, 61, 166, l, 6);
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
            guiGraphics.drawString(this.font, Component.translatable("block.various_world.disassembly_table"), 44, 3, MapColor.COLOR_GRAY.col, false);
    }
}
