package net.sashakyotoz.variousworld.common.blocks.entities.render;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.item.ItemStack;

public class TableBlockRenderState extends BlockEntityRenderState {
    public ItemStackRenderState item = new ItemStackRenderState();
    public ItemStack input = ItemStack.EMPTY;
    public ItemStackRenderState item1 = new ItemStackRenderState();
    public ItemStack input1 = ItemStack.EMPTY;
    public ItemStackRenderState item2 = new ItemStackRenderState();
    public ItemStack input2 = ItemStack.EMPTY;
    public ItemStackRenderState item3 = new ItemStackRenderState();
    public ItemStack output = ItemStack.EMPTY;

    public int progress = 0;
    public boolean hasRecipe = false;
}