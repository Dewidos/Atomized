package io.github.dewidos.atomized.network;

import io.github.dewidos.atomized.Atomized;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(Atomized.MOD_ID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public static void init() {
        int index = 0;

        INSTANCE.messageBuilder(ServerboundLeadChestUpdatePacket.class, index++, NetworkDirection.PLAY_TO_SERVER).encoder(ServerboundLeadChestUpdatePacket::encode).decoder(ServerboundLeadChestUpdatePacket::new).consumer(ServerboundLeadChestUpdatePacket::handle).add();
    }
}
