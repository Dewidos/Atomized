package io.github.dewidos.atomized.event;

import io.github.dewidos.atomized.Atomized;
import io.github.dewidos.atomized.container.ModContainers;
import io.github.dewidos.atomized.screen.LeadChestScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Atomized.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents {
    private ClientModEvents() {}

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        MenuScreens.register(ModContainers.LEAD_CHEST.get(), LeadChestScreen::new);
//        MenuScreens.register(ModContainers.FURNACE_GENERATOR.get(), FurnaceGeneratorScreen::new);
    }
}
