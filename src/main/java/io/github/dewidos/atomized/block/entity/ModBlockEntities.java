package io.github.dewidos.atomized.block.entity;

import io.github.dewidos.atomized.Atomized;
import io.github.dewidos.atomized.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Atomized.MOD_ID);

    public static final RegistryObject<BlockEntityType<FurnaceGeneratorBlockEntity>> FURNACE_GENERATOR = BLOCK_ENTITIES.register("furnace_generator_block_entity", () -> BlockEntityType.Builder.of(FurnaceGeneratorBlockEntity::new, ModBlocks.FURNACE_GENERATOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<LeadChestBlockEntity>> LEAD_CHEST = BLOCK_ENTITIES.register("lead_chest", () -> BlockEntityType.Builder.of(LeadChestBlockEntity::new, ModBlocks.LEAD_CHEST.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
