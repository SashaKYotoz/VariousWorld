package net.sashakyotoz.variousworld.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.sashakyotoz.variousworld.client.menus.DisenchantTableGUIMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.HashMap;

public class DisenchantTableGUIScreen extends AbstractContainerScreen<DisenchantTableGUIMenu> {
	private final static HashMap<String, Object> guistate = DisenchantTableGUIMenu.guistate;
	private final Player player;

	public DisenchantTableGUIScreen(DisenchantTableGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.player = container.player;
		this.imageWidth = 186;
		this.imageHeight = 166;
	}

	private static final ResourceLocation texture = new ResourceLocation("various_world:textures/screens/disenchant_table.png");

	@Override
	public void render(GuiGraphics ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		ms.blit(texture, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}
}
