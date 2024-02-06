
package net.sashakyotoz.variousworld.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.phys.Vec3;
import net.sashakyotoz.variousworld.init.VariousWorldModItems;
import net.sashakyotoz.variousworld.init.VariousWorldModParticleTypes;

public class CrystalicSlimeballPickaxeItem extends PickaxeItem {
    public CrystalicSlimeballPickaxeItem() {
        super(new Tier() {
            public int getUses() {
                return 1036;
            }

            public float getSpeed() {
                return 9f;
            }

            public float getAttackDamageBonus() {
                return 3f;
            }

            public int getLevel() {
                return 3;
            }

            public int getEnchantmentValue() {
                return 15;
            }

            public Ingredient getRepairIngredient() {
                return Ingredient.of(new ItemStack(VariousWorldModItems.SLIME_CRYSTALIC.get()));
            }
        }, 1, -2.8f, new Item.Properties());
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        super.useOn(context);
        Player entity = context.getPlayer();
        if (entity.getXRot() <= 90 && entity.getXRot() >= 75) {
            entity.setDeltaMovement(new Vec3(0, 1, 0));
        }
        spawnFoundParticles(context, context.getClickedPos());
        return InteractionResult.SUCCESS;
    }

    private void spawnFoundParticles(UseOnContext pContext, BlockPos positionClicked) {
        for (int i = 0; i < 360; i++) {
            if (i % 20 == 0) {
                pContext.getLevel().addParticle(VariousWorldModParticleTypes.PEACEFUL_PARTICLE.get(),
                        positionClicked.getX() + 0.5d, positionClicked.getY() + 1, positionClicked.getZ() + 0.5d,
                        Math.cos(i) * 0.15d, 0.15d, Math.sin(i) * 0.15d);
            }
        }
    }
}
