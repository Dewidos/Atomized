package io.github.dewidos.atomized.block.custom;

import io.github.dewidos.atomized.effect.ModEffects;
import io.github.dewidos.atomized.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class UraniumBlock extends Block {
    public UraniumBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void stepOn(@NotNull Level level, @NotNull BlockPos blockPos, @NotNull BlockState blockState, @NotNull Entity entity) {
        if (entity instanceof Player p) {
            if (!((p.getInventory().getArmor(0).getItem().equals(ModItems.hazmat_boots.get())) && (p.getInventory().getArmor(1).getItem().equals(ModItems.hazmat_leg.get())) && (p.getInventory().getArmor(2).getItem().equals(ModItems.hazmat_chest.get())) && (p.getInventory().getArmor(3).getItem().equals(ModItems.hazmat_helmet.get())))) {
                p.addEffect(new MobEffectInstance(ModEffects.RADIATION.get(), 200, 1, false, false));
            }
            super.stepOn(level, blockPos, blockState, entity);
        }

    }
}
