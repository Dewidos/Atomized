package io.github.dewidos.atomized.block.entity;

import io.github.dewidos.atomized.Atomized;
import io.github.dewidos.atomized.block.entity.util.CustomEnergyStorage;
import io.github.dewidos.atomized.screen.FurnaceGeneratorBlockMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FurnaceGeneratorBlockEntity extends InventoryBlockEntity implements BlockEntityTicker<FurnaceGeneratorBlockEntity> {
    public static final Component TITLE = new TranslatableComponent("container." + Atomized.MOD_ID + ".furnace_generator");

    public final CustomEnergyStorage energyStorage;
    private int capacity = 2000, maxExtract = 100;
    private int progress, maxProgress = 0;
    private LazyOptional<CustomEnergyStorage> energy;

    public FurnaceGeneratorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.FURNACE_GENERATOR.get(), pPos, pBlockState, 1);
        energyStorage = createEnergyStorage();
        energy = LazyOptional.of(() -> energyStorage);
    }

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        super.onLoad();
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        energy.invalidate();
        lazyItemHandler.invalidate();
    }

    public int getEnergyForStack(ItemStack stack) {
        return ForgeHooks.getBurnTime(stack, RecipeType.SMELTING);
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public int getProgress() {
        return progress;
    }

    @Override
    public void load(@NotNull CompoundTag pTag) {
        super.load(pTag);

        progress = pTag.getInt("Progress");
        energyStorage.setEnergy(pTag.getInt("Energy"));
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag pTag) {
        super.saveAdditional(pTag);

        pTag.putInt("Progress", progress);
        pTag.putInt("Energy", getEnergy());
    }

    public int getEnergy() {
        return energyStorage.getEnergyStored();
    }

    public void outputEnergy() {
        if (this.energyStorage.getEnergyStored() >= this.maxExtract && this.energyStorage.canExtract()) {
            for (final var direction : Direction.values()) {
                final BlockEntity be = this.level.getBlockEntity(this.worldPosition.relative(direction));
                if (be == null) {
                    continue;
                }

                be.getCapability(CapabilityEnergy.ENERGY, direction.getOpposite()).ifPresent(storage -> {
                    if (be != this && storage.getEnergyStored() < storage.getMaxEnergyStored()) {
                        final int toSend = FurnaceGeneratorBlockEntity.this.energyStorage.extractEnergy(this.maxExtract,
                                false);
                        Atomized.LOGGER.info("Send: {}", toSend);
                        final int received = storage.receiveEnergy(toSend, false);
                        Atomized.LOGGER.info("Final Received: {}", received);

                        FurnaceGeneratorBlockEntity.this.energyStorage.setEnergy(
                                FurnaceGeneratorBlockEntity.this.energyStorage.getEnergyStored() + toSend - received);
                    }
                });
            }
        }
    }

    private CustomEnergyStorage createEnergyStorage() {
        return new CustomEnergyStorage(capacity, 0, maxExtract, this);
    }

    public void tick() {
        if (this.energyStorage.getEnergyStored() <= this.energyStorage.getMaxEnergyStored() - 100) {
            if (!this.itemHandler.getStackInSlot(0).isEmpty() && (this.progress <= 0 || this.progress > this.maxProgress)) {
                this.maxProgress = getEnergyForStack(this.itemHandler.getStackInSlot(0));
                this.itemHandler.extractItem(0, 1, false);
                this.progress++;
            } else if (this.progress > 0) {
                this.progress++;
                if (this.progress >= this.maxProgress) {
                    this.progress = 0;
                    this.energyStorage.setEnergy(this.energyStorage.getEnergyStored() + this.maxProgress);
                }
            } else {
                this.progress = 0;
                this.maxProgress = 0;
            }
        }

        outputEnergy();

    }

    @Override
    public void tick(@NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, FurnaceGeneratorBlockEntity pBlockEntity) {
        pBlockEntity.tick();
    }

}
