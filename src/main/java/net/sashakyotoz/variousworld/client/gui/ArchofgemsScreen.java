package net.sashakyotoz.variousworld.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.sashakyotoz.variousworld.client.menus.ArchOfGemsMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.HashMap;

public class ArchofgemsScreen extends AbstractContainerScreen<ArchOfGemsMenu> {
	private final static HashMap<String, Object> guistate = ArchOfGemsMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;

	public ArchofgemsScreen(ArchOfGemsMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.level;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.player;
		this.imageWidth = 185;
		this.imageHeight = 185;
	}

	private static final ResourceLocation texture = new ResourceLocation("various_world:textures/screens/arch_of_gems.png");

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
		RenderSystem.setShaderTexture(0, new ResourceLocation("various_world:textures/screens/dragon_eye_effect_icon.png"));
		ms.blit(new ResourceLocation("various_world:textures/screens/dragon_eye_effect_icon.png"), this.leftPos + 82, this.topPos + 42, 0, 0, 16, 16, 16, 16);

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

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();
	}

}
