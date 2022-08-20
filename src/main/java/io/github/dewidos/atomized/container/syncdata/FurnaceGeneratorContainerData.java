package io.github.dewidos.atomized.container.syncdata;

import io.github.dewidos.atomized.block.entity.FurnaceGeneratorBlockEntity;
import net.minecraft.world.inventory.SimpleContainerData;

public class FurnaceGeneratorContainerData extends SimpleContainerData {
    private final FurnaceGeneratorBlockEntity blockEntity;

    public FurnaceGeneratorContainerData(FurnaceGeneratorBlockEntity blockEntity, int pSize) {
        super(pSize);
        this.blockEntity = blockEntity;
    }

    @Override
    public int get(int pIndex) {
        return switch (pIndex) {
            case 0 -> blockEntity.getProgress();
            case 1 -> blockEntity.getMaxProgress();
            case 2 -> blockEntity.getEnergy();
            case 3 -> blockEntity.energyStorage.getMaxEnergyStored();
            default -> throw new UnsupportedOperationException("Unable to get key: '" + pIndex + "' for block entity: '" + blockEntity + "' at pos: '" + blockEntity.getBlockPos() + "'");
        };
    }


    @Override
    public void set(int pIndex, int pValue) {
        super.set(pIndex, pValue);
        switch (pIndex) {
            case 0 -> blockEntity.progress = pValue;
        }
    }
}
