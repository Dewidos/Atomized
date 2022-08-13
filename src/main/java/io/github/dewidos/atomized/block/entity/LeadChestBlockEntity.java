package io.github.dewidos.atomized.block.entity;

import io.github.dewidos.atomized.Atomized;
import io.github.dewidos.atomized.block.entity.util.InventoryBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.block.state.BlockState;

public class LeadChestBlockEntity extends InventoryBlockEntity {
    public static final Component TITLE = new TranslatableComponent("container." + Atomized.MOD_ID + ".lead_chest");

    public LeadChestBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.LEAD_CHEST.get(), pPos, pBlockState, 27);
    }
}
