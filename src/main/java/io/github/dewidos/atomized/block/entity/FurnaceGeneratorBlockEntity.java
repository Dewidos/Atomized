package io.github.dewidos.atomized.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FurnaceGeneratorBlockEntity extends BlockEntity {
    public FurnaceGeneratorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.furnace_generator_block_entity.get(), pPos, pBlockState);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
    }

    public void tick() {
        System.out.println("Cos pierdole nwm co");
    }
}
