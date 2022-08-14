package io.github.dewidos.atomized.network;

import io.github.dewidos.atomized.Atomized;
import io.github.dewidos.atomized.block.entity.LeadChestBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class ServerboundLeadChestUpdatePacket {
    public final boolean ejectItems;
    public final BlockPos chestPos;

    public ServerboundLeadChestUpdatePacket(BlockPos blockPos, boolean ejectItems) {
        this.chestPos = blockPos;
        this.ejectItems = ejectItems;
    }

    public ServerboundLeadChestUpdatePacket(FriendlyByteBuf buffer) {
        this(buffer.readBlockPos(), buffer.readBoolean());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeBlockPos(chestPos);
        buffer.writeBoolean(ejectItems);
    }

    public boolean handle(Supplier<NetworkEvent.Context> context) {
        final var success = new AtomicBoolean(false);
        context.get().enqueueWork(() -> {
            BlockEntity blockEntity = context.get().getSender().level.getBlockEntity(chestPos);

            if (blockEntity instanceof LeadChestBlockEntity leadChest) {
                if (ejectItems) {
                    final int slotCount = leadChest.inventory.getSlots();

                    for (int i = 0; i < slotCount; i++) {
                        leadChest.inventory.setStackInSlot(i, ItemStack.EMPTY);

                        Atomized.LOGGER.info("Thing happened");
                    }
                }

                success.set(true);
            }
        });

        context.get().setPacketHandled(true);

        return success.get();
    }

}
