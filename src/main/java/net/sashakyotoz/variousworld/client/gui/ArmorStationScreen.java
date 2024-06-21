package net.sashakyotoz.variousworld.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.sashakyotoz.variousworld.VariousWorldMod;
import net.sashakyotoz.variousworld.network.ArmorStationButtonMessage;
import net.sashakyotoz.variousworld.client.menus.ArmorStationMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.HashMap;

public class ArmorStationScreen extends AbstractContainerScreen<ArmorStationMenu> {
	private final static HashMap<String, Object> guistate = ArmorStationMenu.guistate;
	private final int x, y, z;
	private final Player entity;
	ImageButton imagebutton_hammer;

	public ArmorStationScreen(ArmorStationMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 200;
		this.imageHeight = 175;
	}

	private static final ResourceLocation texture = new ResourceLocation("various_world:textures/screens/armor_station.png");

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
		RenderSystem.setShaderTexture(0, new ResourceLocation("various_world:textures/screens/feather.png"));
		ms.blit(new ResourceLocation("various_world:textures/screens/feather.png"), this.leftPos + 55, this.topPos + 66, 0, 0, 16, 16, 16, 16);

		RenderSystem.setShaderTexture(0, new ResourceLocation("various_world:textures/screens/carcass.png"));
		ms.blit(new ResourceLocation("various_world:textures/screens/carcass.png"), this.leftPos + 55, this.topPos + 39, 0, 0, 16, 16, 16, 16);

		RenderSystem.setShaderTexture(0, new ResourceLocation("various_world:textures/screens/material.png"));
		ms.blit(new ResourceLocation("various_world:textures/screens/material.png"), this.leftPos + 55, this.topPos + 12, 0, 0, 16, 16, 16, 16);

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
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();
		imagebutton_hammer = new ImageButton(this.leftPos + 90, this.topPos + 38, 16, 16, 0, 0, 16, new ResourceLocation("various_world:textures/screens/atlas/imagebutton_hammer.png"), 16, 32, e -> {
			VariousWorldMod.PACKET_HANDLER.sendToServer(new ArmorStationButtonMessage(0, x, y, z));
			ArmorStationButtonMessage.handleButtonAction(entity, 0, x, y, z);
		});
		guistate.put("button:imagebutton_hammer", imagebutton_hammer);
		this.addRenderableWidget(imagebutton_hammer);
	}
}
