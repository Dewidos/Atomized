package io.github.dewidos.atomized.item;

import io.github.dewidos.atomized.Atomized;
import io.github.dewidos.atomized.item.custom.ModArmorMaterials;
import io.github.dewidos.atomized.item.custom.UraniumIngotItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Atomized.MOD_ID);

    public static final RegistryObject<Item> steel_ingot = ITEMS.register("steel_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTab.ATOMIZED_TAB)));
    public static final RegistryObject<Item> sulfur = ITEMS.register("sulfur", () -> new Item(new Item.Properties().tab(ModCreativeTab.ATOMIZED_TAB)));
    public static final RegistryObject<Item> rubber = ITEMS.register("rubber", () -> new Item(new Item.Properties().tab(ModCreativeTab.ATOMIZED_TAB)));
    public static final RegistryObject<Item> iron_rod = ITEMS.register("iron_rod", () -> new Item(new Item.Properties().tab(ModCreativeTab.ATOMIZED_TAB)));
    public static final RegistryObject<Item> sulfur_dust = ITEMS.register("sulfur_dust", () -> new Item(new Item.Properties().tab(ModCreativeTab.ATOMIZED_TAB)));
    public static final RegistryObject<Item> raw_uranium = ITEMS.register("raw_uranium", () -> new Item(new Item.Properties().tab(ModCreativeTab.ATOMIZED_TAB)));
    public static final RegistryObject<Item> lead_ingot = ITEMS.register("lead_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTab.ATOMIZED_TAB)));
    public static final RegistryObject<Item> uranium_ingot = ITEMS.register("uranium_ingot", () -> new UraniumIngotItem(new Item.Properties().tab(ModCreativeTab.ATOMIZED_TAB)));
    public static final RegistryObject<Item> hazmat_helmet = ITEMS.register("hazmat_helmet", () -> new ArmorItem(ModArmorMaterials.HAZMAT, EquipmentSlot.HEAD, new Item.Properties().tab(ModCreativeTab.ATOMIZED_EQUIPMENT_TAB)));
    public static final RegistryObject<Item> hazmat_chest = ITEMS.register("hazmat_chest", () -> new ArmorItem(ModArmorMaterials.HAZMAT, EquipmentSlot.CHEST, new Item.Properties().tab(ModCreativeTab.ATOMIZED_EQUIPMENT_TAB)));
    public static final RegistryObject<Item> hazmat_leg = ITEMS.register("hazmat_leg", () -> new ArmorItem(ModArmorMaterials.HAZMAT, EquipmentSlot.LEGS, new Item.Properties().tab(ModCreativeTab.ATOMIZED_EQUIPMENT_TAB)));
    public static final RegistryObject<Item> hazmat_boots = ITEMS.register("hazmat_boots", () -> new ArmorItem(ModArmorMaterials.HAZMAT, EquipmentSlot.FEET, new Item.Properties().tab(ModCreativeTab.ATOMIZED_EQUIPMENT_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
