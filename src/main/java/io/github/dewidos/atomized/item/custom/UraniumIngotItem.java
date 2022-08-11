package io.github.dewidos.atomized.item.custom;

import io.github.dewidos.atomized.effect.ModEffects;
import io.github.dewidos.atomized.item.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class UraniumIngotItem extends Item {
    public UraniumIngotItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity entity, int pSlotId, boolean pIsSelected) {

        Player p = (Player) entity;

        if (p instanceof Player) {
            if (!((p.getInventory().getArmor(0).getItem().equals(ModItems.hazmat_boots.get())) && (p.getInventory().getArmor(1).getItem().equals(ModItems.hazmat_leg.get())) && (p.getInventory().getArmor(2).getItem().equals(ModItems.hazmat_chest.get())) && (p.getInventory().getArmor(3).getItem().equals(ModItems.hazmat_helmet.get())))) {
                p.addEffect(new MobEffectInstance(ModEffects.RADIATION.get(), 200, 1, false, false));
            }
        }

    }
}
