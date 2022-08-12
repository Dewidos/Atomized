package io.github.dewidos.atomized.block.entity;

import io.github.dewidos.atomized.util.AtomizedEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public abstract class ModAtomizedBlockEntities extends BlockEntity implements
        MenuProvider, IFluidHandlingBlockEntity, IEnergyHandlingBlockEntity, IInventoryHandlingBlockEntity {
    public ModSlimeBlockEntity(BlockEntityType<?> pType, BlockPos pWorldPosition, BlockState pBlockState) {
        super(pType, pWorldPosition, pBlockState);
    }

    abstract FluidTank createFluidTank();
    abstract AtomizedEnergyStorage createEnergyStorage();



}
