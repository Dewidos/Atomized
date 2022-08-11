package io.github.dewidos.atomized.block.entity.util;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.energy.EnergyStorage;

public class CustomEnergyStorage extends EnergyStorage {
    private final BlockEntity blockEntity;

    public CustomEnergyStorage(int capacity, BlockEntity blockEntity) {
        super(capacity);
        this.blockEntity = blockEntity;
    }

    public CustomEnergyStorage(int capacity, int maxTransfer, BlockEntity blockEntity) {
        super(capacity, maxTransfer);
        this.blockEntity = blockEntity;
    }

    public CustomEnergyStorage(int capacity, int maxReceive, int maxExtract, BlockEntity blockEntity) {
        super(capacity, maxReceive, maxExtract);
        this.blockEntity = blockEntity;
    }

    public CustomEnergyStorage(int capacity, int maxReceive, int maxExtract, int energy, BlockEntity blockEntity) {
        super(capacity, maxReceive, maxExtract, energy);
        this.blockEntity = blockEntity;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        blockEntity.setChanged();
        return super.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        blockEntity.setChanged();
        return super.receiveEnergy(maxReceive, simulate);
    }

    public void setEnergy(int energy) {
        this.energy = Math.max(0, Math.min(energy, capacity));
    }
}
