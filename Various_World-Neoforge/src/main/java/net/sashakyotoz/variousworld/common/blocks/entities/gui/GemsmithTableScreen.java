package net.sashakyotoz.variousworld.common.blocks.entities.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.material.MapColor;
import net.sashakyotoz.variousworld.VariousWorld;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GemsmithTableScreen extends AbstractContainerScreen<GemsmithTableMenu> {
    public static final ResourceLocation BACKGROUND_LOCATION = VariousWorld.createVWLocation("textures/gui/gemsmith_table.png");
    private static final int OUTPUT_X = 119;
    private static final int OUTPUT_Y = 48;

    public GemsmithTableScreen(GemsmithTableMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.imageWidth = 174;
        this.imageHeight = 164;
    }

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
        renderPreview(guiGraphics);
    }

    private void renderIconsProgress(GuiGraphics graphics) {
        if (getMenu().isCrafting())
            graphics.blit(BACKGROUND_LOCATION, this.leftPos + 85, this.topPos + 48, 176, 49, getMenu().getScaledProgress(), 16);
        if (getMenu().isLit()) {
            int k = Mth.ceil(this.menu.getLitProgress() * 14.0F) - 14;
            graphics.blit(BACKGROUND_LOCATION, this.leftPos + 90, this.topPos + 66 - k, 176, 66 - k, 14, 14);
        }
    }

    private boolean hasPreview() {
        return !this.menu.getPreview().isEmpty() && this.menu.isOutputEmpty();
    }

    private void renderPreview(GuiGraphics graphics) {
        if (!hasPreview())
            return;
        int x = this.leftPos + OUTPUT_X;
        int y = this.topPos + OUTPUT_Y;
        graphics.renderFakeItem(this.menu.getPreview(), x, y);
        graphics.pose().pushPose();
        graphics.pose().translate(0, 0, 200);
        graphics.fill(RenderType.guiGhostRecipeOverlay(), x, y, x + 16, y + 16, 0x80888888);
        graphics.pose().popPose();
    }

    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        if (this.menu.getCarried().isEmpty() && hasPreview() && this.isHovering(OUTPUT_X, OUTPUT_Y, 16, 16, mouseX, mouseY)) {
            guiGraphics.renderComponentTooltip(this.font, previewTooltip(this.menu.getTool(), this.menu.getPreview()), mouseX, mouseY);
            return;
        }
        super.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    private List<Component> previewTooltip(ItemStack tool, ItemStack preview) {
        List<Component> lines = new ArrayList<>();
        lines.add(Component.translatable("gui.various_world.gemsmith_table.preview").withStyle(ChatFormatting.GOLD));
        Map<Holder<Attribute>, Double> before = flatBonuses(tool);
        flatBonuses(preview).forEach((attribute, value) -> {
            double delta = value - before.getOrDefault(attribute, 0.0D);
            if (Math.abs(delta) > 1.0E-6D)
                lines.add(Component.translatable("gui.various_world.gemsmith_table.bonus",
                        (delta > 0 ? "+" : "") + ItemAttributeModifiers.ATTRIBUTE_MODIFIER_FORMAT.format(delta),
                        Component.translatable(attribute.value().getDescriptionId())).withStyle(ChatFormatting.BLUE));
        });
        lines.addAll(Screen.getTooltipFromItem(Minecraft.getInstance(), preview));
        return lines;
    }

    private static Map<Holder<Attribute>, Double> flatBonuses(ItemStack stack) {
        Map<Holder<Attribute>, Double> bonuses = new LinkedHashMap<>();
        for (ItemAttributeModifiers.Entry entry : stack.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY).modifiers())
            if (entry.modifier().operation() == AttributeModifier.Operation.ADD_VALUE)
                bonuses.merge(entry.attribute(), entry.modifier().amount(), Double::sum);
        return bonuses;
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
