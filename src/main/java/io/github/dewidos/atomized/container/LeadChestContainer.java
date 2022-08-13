package io.github.dewidos.atomized.container;

import io.github.dewidos.atomized.block.ModBlocks;
import io.github.dewidos.atomized.block.entity.LeadChestBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class LeadChestContainer extends AbstractContainerMenu {
    private final ContainerLevelAccess containerAccess;

    // Client constructor
    public LeadChestContainer(int id, Inventory playerInv) {
        this(id, playerInv, new ItemStackHandler(27), BlockPos.ZERO);
    }

    // Server constructor
    public LeadChestContainer(int id, Inventory playerInv, IItemHandler slots, BlockPos blockPos) {
        super(ModContainers.LEAD_CHEST.get(), id);
        containerAccess = ContainerLevelAccess.create(playerInv.player.level, blockPos);

        final int slotSizePlus2 = 18, startX = 8, startY = 87, hotbarY = 144, inventoryY = 17;

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new SlotItemHandler(slots, row * 9 + column, startX + column * slotSizePlus2, inventoryY + row * slotSizePlus2));
            }
        }

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                addSlot(new Slot(playerInv, 9 + row * 9 + column, startX + column * slotSizePlus2, startY + row * slotSizePlus2));
            }
        }

        for (int column = 0; column < 9; column++) {
            addSlot(new Slot(playerInv, column, startX + column * slotSizePlus2, hotbarY));
        }
    }

    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        return stillValid(containerAccess, pPlayer, ModBlocks.LEAD_CHEST.get());
    }

    public static MenuConstructor getServerContainer(LeadChestBlockEntity chest, BlockPos pos) {
        return (id, playerInv, player) -> new LeadChestContainer(id, playerInv, chest.inventory, pos);
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player pPlayer, int pIndex) {
        var retStack = ItemStack.EMPTY;
        final Slot slot = getSlot(pIndex);
        if (slot.hasItem()) {
            final ItemStack item = slot.getItem();
            retStack = item.copy();
            if (pIndex < 27) {
                if (!moveItemStackTo(item, 27, this.slots.size(), true))
                    return ItemStack.EMPTY;
            } else if (!moveItemStackTo(item, 0, 27, false))
                return ItemStack.EMPTY;

            if (item.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return retStack;
    }
}
