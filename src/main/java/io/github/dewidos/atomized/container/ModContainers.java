package io.github.dewidos.atomized.container;

import io.github.dewidos.atomized.Atomized;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModContainers {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS,
            Atomized.MOD_ID);

    public static final RegistryObject<MenuType<FurnaceGeneratorContainer>> FURNACE_GENERATOR = CONTAINERS
            .register("furnace_generator", () -> new MenuType<>(FurnaceGeneratorContainer::new));

    public static final RegistryObject<MenuType<LeadChestContainer>> LEAD_CHEST = CONTAINERS.register("lead_chest", () -> new MenuType<>(LeadChestContainer::new));

    public static void register(IEventBus eventBus) {
        CONTAINERS.register(eventBus);
    }
}
