package net.sashakyotoz.variousworld.mixin.block.sodium;

import net.caffeinemc.mods.sodium.client.render.chunk.compile.pipeline.BlockRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.caffeinemc.mods.sodium.client.render.frapi.render.AbstractBlockRenderContext;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.sashakyotoz.variousworld.common.blocks.BlockUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(BlockRenderer.class)
public abstract class BlockRendererMixin extends AbstractBlockRenderContext {
    @Unique
    private static final BlockModelShaper BLOCK_MODEL_SHAPER = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper();

    @Shadow
    public abstract void renderModel(BlockStateModel model, BlockState state, BlockPos pos, BlockPos origin);

    @Inject(method = "renderModel", at = @At("HEAD"), remap = false, require = 0)
    public void renderReclaimite(BlockStateModel model, BlockState state, BlockPos pos, BlockPos origin, CallbackInfo info) {
        if (BlockUtils.isReclamited(state)) {
            final BlockState snowState = BlockUtils.getReclamiteEquivalent(state);
            final BlockStateModel stateModel = BLOCK_MODEL_SHAPER.getBlockModel(snowState);
            this.renderModel(stateModel, snowState, pos, origin);
        }
    }
}