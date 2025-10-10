package net.sashakyotoz.variousworld.client.models.states;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class SquealingSpiderRenderState extends LivingEntityRenderState {
    public AnimationState slide = new AnimationState();
    public AnimationState slideBack = new AnimationState();
    public AnimationState longJump = new AnimationState();
    public AnimationState shoot = new AnimationState();
    public AnimationState inhale = new AnimationState();
    public boolean canConnectToRoof = false;
}