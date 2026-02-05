package net.sashakyotoz.variousworld.common.blocks.entities.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.MapColor;
import net.sashakyotoz.variousworld.VariousWorld;

public class ArtifactTableScreen extends AbstractContainerScreen<ArtifactTableMenu> {
    private final Level level;
    private final Player player;

    public ArtifactTableScreen(ArtifactTableMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.level = inventory.player.level();
        this.player = inventory.player;
        this.imageWidth = 174;
        this.imageHeight = 164;
    }

    public static final Identifier BACKGROUND_LOCATION = VariousWorld.createVWLocation("textures/gui/artifact_table.png");
    public static final Identifier TORCH = VariousWorld.createVWLocation("artifact_table_torch");
    public static final Identifier EFFECT_ICON = VariousWorld.createVWLocation("artifact_table_effect");

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
        if (getMenu().isRefreshing())
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, EFFECT_ICON, 16,32, 0, 0, this.leftPos + 80, this.topPos + 16, 16, getMenu().getScaledProgress());
        if (getMenu().isPowered())
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, TORCH, 16,16, 0, 0, this.leftPos + 26, this.topPos + 48, 16, 16);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        if (Minecraft.getInstance().getLanguageManager().getSelected().equals("en_us"))
            guiGraphics.drawString(this.font, Component.translatable("block.various_world.artifact_table"), 56, 4, MapColor.COLOR_GRAY.col, false);
    }
}
