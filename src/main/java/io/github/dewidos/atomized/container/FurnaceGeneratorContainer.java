package io.github.dewidos.atomized.container;

import io.github.dewidos.atomized.block.ModBlocks;
import io.github.dewidos.atomized.block.entity.FurnaceGeneratorBlockEntity;
import io.github.dewidos.atomized.container.syncdata.FurnaceGeneratorContainerData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class FurnaceGeneratorContainer extends AbstractContainerMenu {
    private final ContainerLevelAccess containerAccess;
    public final ContainerData data;

    // Client Constructor
    public FurnaceGeneratorContainer(int id, Inventory playerInv) {
        this(id, playerInv, new ItemStackHandler(1), BlockPos.ZERO, new SimpleContainerData(4));
    }

    // Server constructor
    public FurnaceGeneratorContainer(int id, Inventory playerInv, IItemHandler slots, BlockPos pos, ContainerData data) {
        super(ModContainers.FURNACE_GENERATOR.get(), id);
        this.containerAccess = ContainerLevelAccess.create(playerInv.player.level, pos);
        this.data = data;

        final int slotSizePlus2 = 18, startX = 8, startY = 86, hotbarY = 144;

        addSlot(new SlotItemHandler(slots, 0, 44, 36));

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(playerInv, 9 + row * 9 + column, startX + column * slotSizePlus2,
                        startY + row * slotSizePlus2));
            }
        }

        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(playerInv, column, startX + column * slotSizePlus2, hotbarY));
        }

        addDataSlots(data);
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        var retStack = ItemStack.EMPTY;
        final Slot slot = getSlot(index);
        if (slot.hasItem()) {
            final ItemStack item = slot.getItem();
            retStack = item.copy();
            if (index < 1) {
                if (!moveItemStackTo(item, 1, this.slots.size(), true))
                    return ItemStack.EMPTY;
            } else if (!moveItemStackTo(item, 0, 1, false))
                return ItemStack.EMPTY;

            if (item.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return retStack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(this.containerAccess, player, ModBlocks.FURNACE_GENERATOR.get());
    }

    public static MenuConstructor getServerContainer(FurnaceGeneratorBlockEntity be, BlockPos pos) {
        return (id, playerInv, player) -> new FurnaceGeneratorContainer(id, playerInv, be.inventory, pos,
                new FurnaceGeneratorContainerData(be, 4));
    }
}
