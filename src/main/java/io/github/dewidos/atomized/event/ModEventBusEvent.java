package io.github.dewidos.atomized.event;

import io.github.dewidos.atomized.Atomized;
import io.github.dewidos.atomized.particle.ModParticles;
import io.github.dewidos.atomized.particle.custom.RadiatingParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Atomized.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvent {

    @SubscribeEvent
    public static void registerParticleFactories(final ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.RADIATING_PARTICLES.get(), RadiatingParticles.Provider::new);
    }
}
