package io.github.dewidos.atomized.block;

import io.github.dewidos.atomized.Atomized;
import io.github.dewidos.atomized.block.custom.UraniumBlock;
import io.github.dewidos.atomized.item.ModCreativeTab;
import io.github.dewidos.atomized.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Atomized.MOD_ID);

    public static final RegistryObject<Block> steel_block = registerBlock(
            "steel_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(8f).requiresCorrectToolForDrops()),
            ModCreativeTab.ATOMIZED_TAB
    );

    public static final RegistryObject<Block> machine_casing = registerBlock(
            "machine_casing",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops()),
            ModCreativeTab.ATOMIZED_TAB
    );
    public static final RegistryObject<Block> lead_ore = registerBlock(
            "lead_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()),
            ModCreativeTab.ATOMIZED_TAB
    );
    public static final RegistryObject<Block> deepslate_lead_ore = registerBlock(
            "deepslate_lead_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(8f).requiresCorrectToolForDrops()),
            ModCreativeTab.ATOMIZED_TAB
    );
    public static final RegistryObject<Block> lead_block = registerBlock(
            "lead_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()),
            ModCreativeTab.ATOMIZED_TAB
    );
    public static final RegistryObject<Block> sulfur_ore = registerBlock(
            "sulfur_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops()),
            ModCreativeTab.ATOMIZED_TAB
    );
    public static final RegistryObject<Block> lead_glass = registerBlock(
            "lead_glass",
            () -> new GlassBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(3f).sound(SoundType.GLASS).noOcclusion()),
            ModCreativeTab.ATOMIZED_TAB
    );
    public static final RegistryObject<Block> uranium_ore = registerBlock(
            "uranium_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.GLASS).strength(6f).requiresCorrectToolForDrops()),
            ModCreativeTab.ATOMIZED_TAB
    );

    public static final RegistryObject<Block> uranium_block = registerBlock(
            "uranium_block",
            () -> new UraniumBlock(BlockBehaviour.Properties.of(Material.STONE).strength(7f).requiresCorrectToolForDrops()),
            ModCreativeTab.ATOMIZED_TAB
    );


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}