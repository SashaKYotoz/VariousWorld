package net.sashakyotoz.variousworld.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.world.item.ItemStack;
import net.sashakyotoz.variousworld.VariousWorld;
import net.sashakyotoz.variousworld.network.ArmorStationButtonMessage;
import net.sashakyotoz.variousworld.client.menus.ArmorStationMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;

public class ArmorStationScreen extends AbstractContainerScreen<ArmorStationMenu> {
	private final static HashMap<String, Object> guistate = ArmorStationMenu.guistate;
	private final int x, y, z;
	private final Player player;
	private boolean iconsFlag;
    ImageButton imagebutton_hammer;

	public ArmorStationScreen(ArmorStationMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
        this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.player = container.player;
		this.imageWidth = 200;
		this.imageHeight = 175;
	}

	private static final ResourceLocation BACKGROUND = new ResourceLocation("various_world:textures/screens/armor_station.png");
	private static final ResourceLocation FEATHER = new ResourceLocation("minecraft:textures/item/feather.png");
	private static final ResourceLocation MEMBRANE = new ResourceLocation("minecraft:textures/item/phantom_membrane.png");
	private static final ResourceLocation DARKNIUM_CHESTPLATE = new ResourceLocation("various_world:textures/item/darknium_chestplate.png");
	private static final ResourceLocation SCULK_CHESTPLATE = new ResourceLocation("various_world:textures/item/sculkchestplate.png");
	private static final ResourceLocation NETHER_STAR = new ResourceLocation("minecraft:textures/item/nether_star.png");
	private static final ResourceLocation SCALE_INGOT = new ResourceLocation("various_world:textures/item/fury_ingot.png");

	@Override
	public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(graphics);
		super.render(graphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(graphics, mouseX, mouseY);
	}
	@Override
	protected void renderBg(GuiGraphics graphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, BACKGROUND);
		graphics.blit(BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		graphics.blit(new ResourceLocation("various_world:textures/screens/feather.png"), this.leftPos + 55, this.topPos + 66, 0, 0, 16, 16, 16, 16);
		graphics.blit(new ResourceLocation("various_world:textures/screens/carcass.png"), this.leftPos + 55, this.topPos + 39, 0, 0, 16, 16, 16, 16);
		graphics.blit(new ResourceLocation("various_world:textures/screens/material.png"), this.leftPos + 55, this.topPos + 12, 0, 0, 16, 16, 16, 16);
		rendererIcons(graphics);
		RenderSystem.disableBlend();
	}
	private void rendererIcons(GuiGraphics graphics){
		if (this.getMenu().get() != null){
			ItemStack featherSlotStack = this.getMenu().get().get(0).getItem();
			ItemStack armorSlotStack = this.getMenu().get().get(1).getItem();
			ItemStack materialSlotStack = this.getMenu().get().get(2).getItem();
			if (this.player.tickCount % 40 == 0)
				iconsFlag = !iconsFlag;
            if (!featherSlotStack.isEmpty() && armorSlotStack.isEmpty() && materialSlotStack.isEmpty()){
				graphics.blit(iconsFlag ? SCULK_CHESTPLATE : DARKNIUM_CHESTPLATE, this.leftPos + 35, this.topPos + 39, 0, 0, 16, 16, 16, 16);
				graphics.blit(iconsFlag ? NETHER_STAR : SCALE_INGOT, this.leftPos + 35, this.topPos + 12, 0, 0, 16, 16, 16, 16);
			}else if (featherSlotStack.isEmpty() && !armorSlotStack.isEmpty() && materialSlotStack.isEmpty()){
				graphics.blit(iconsFlag ? FEATHER : MEMBRANE, this.leftPos + 35, this.topPos + 66, 0, 0, 16, 16, 16, 16);
				graphics.blit(iconsFlag ? NETHER_STAR : SCALE_INGOT, this.leftPos + 35, this.topPos + 12, 0, 0, 16, 16, 16, 16);
			} else if (featherSlotStack.isEmpty() && armorSlotStack.isEmpty() && !materialSlotStack.isEmpty()) {
				graphics.blit(iconsFlag ? FEATHER : MEMBRANE, this.leftPos + 35, this.topPos + 66, 0, 0, 16, 16, 16, 16);
				graphics.blit(iconsFlag ? SCULK_CHESTPLATE : DARKNIUM_CHESTPLATE, this.leftPos + 35, this.topPos + 39, 0, 0, 16, 16, 16, 16);
			}
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
	protected void renderLabels(GuiGraphics graphics, int mouseX, int mouseY) {
		graphics.drawString(this.font, Component.translatable("block.various_world.armor_station_block"), 82, 66, 0x000000, false);
	}

	@Override
	public void onClose() {
		super.onClose();
	}

	@Override
	public void init() {
		super.init();
		imagebutton_hammer = new ImageButton(this.leftPos + 90, this.topPos + 38, 16, 16, 0, 0, 16, new ResourceLocation("various_world:textures/screens/atlas/imagebutton_hammer.png"), 16, 32, e -> {
			VariousWorld.PACKET_HANDLER.sendToServer(new ArmorStationButtonMessage(0, x, y, z));
			ArmorStationButtonMessage.handleButtonAction(player, 0, x, y, z);
		});
		guistate.put("button:imagebutton_hammer", imagebutton_hammer);
		this.addRenderableWidget(imagebutton_hammer);
	}
}