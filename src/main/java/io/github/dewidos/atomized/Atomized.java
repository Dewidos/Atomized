package io.github.dewidos.atomized;

import com.mojang.logging.LogUtils;
import io.github.dewidos.atomized.block.ModBlocks;
import io.github.dewidos.atomized.block.entity.ModBlockEntities;
import io.github.dewidos.atomized.container.ModContainers;
import io.github.dewidos.atomized.effect.ModEffects;
import io.github.dewidos.atomized.item.ModItems;
import io.github.dewidos.atomized.network.PacketHandler;
import io.github.dewidos.atomized.particle.ModParticles;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Atomized.MOD_ID)
public class Atomized {

    public static final String MOD_ID = "atomized";

    public static final Logger LOGGER = LogUtils.getLogger();

    public Atomized() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::setup);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModParticles.register(modEventBus);
        ModEffects.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModContainers.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(PacketHandler::init);
    }

//    private void enqueueIMC(final InterModEnqueueEvent event) {
//        // Some example code to dispatch IMC to another mod
//        InterModComms.sendTo("Atomized", "helloworld", () -> {
//            LOGGER.info("Hello world from the MDK");
//            return "Hello world";
//        });
//    }

//    private void processIMC(final InterModProcessEvent event) {
//        // Some example code to receive and process InterModComms from other mods
//        LOGGER.info("Got IMC {}", event.getIMCStream().
//                map(m -> m.messageSupplier().get()).
//                collect(Collectors.toList()));
//    }

}
