
package net.sashakyotoz.variousworld.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.phys.Vec3;
import net.sashakyotoz.variousworld.init.VariousWorldModEnchantments;
import net.sashakyotoz.variousworld.init.VariousWorldModItems;
import net.sashakyotoz.variousworld.init.VariousWorldModParticleTypes;

public class CrystalicSlimeballPickaxeItem extends PickaxeItem {
    public CrystalicSlimeballPickaxeItem() {
        super(ModTiers.CRYSTALIC_SLIME, 1, -2.8f, new Item.Properties());
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        super.useOn(context);
        Player player = context.getPlayer();
        if (player != null && player.getXRot() <= 90 && player.getXRot() >= 70)
            player.setDeltaMovement(new Vec3(0, 1+player.getItemBySlot(EquipmentSlot.FEET).getEnchantmentLevel(VariousWorldModEnchantments.JUMPER.get())/2f, 0));
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
