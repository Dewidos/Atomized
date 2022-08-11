package io.github.dewidos.atomized.effect;

import io.github.dewidos.atomized.Atomized;
import io.github.dewidos.atomized.effect.custom.RadiationEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOD_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Atomized.MOD_ID);

    public static final RegistryObject<MobEffect> RADIATION = MOD_EFFECTS.register("radiation", () -> new RadiationEffect(MobEffectCategory.HARMFUL, 124823));
    public static void register(IEventBus eventBus) {
        MOD_EFFECTS.register(eventBus);
    }
}
