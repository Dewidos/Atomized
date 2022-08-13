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

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTab.ATOMIZED_TAB)));
    public static final RegistryObject<Item> SULFUR = ITEMS.register("sulfur", () -> new Item(new Item.Properties().tab(ModCreativeTab.ATOMIZED_TAB)));
    public static final RegistryObject<Item> RUBBER = ITEMS.register("rubber", () -> new Item(new Item.Properties().tab(ModCreativeTab.ATOMIZED_TAB)));
    public static final RegistryObject<Item> IRON_ROD = ITEMS.register("iron_rod", () -> new Item(new Item.Properties().tab(ModCreativeTab.ATOMIZED_TAB)));
    public static final RegistryObject<Item> SULFUR_DUST = ITEMS.register("sulfur_dust", () -> new Item(new Item.Properties().tab(ModCreativeTab.ATOMIZED_TAB)));
    public static final RegistryObject<Item> RAW_URANIUM = ITEMS.register("raw_uranium", () -> new Item(new Item.Properties().tab(ModCreativeTab.ATOMIZED_TAB)));
    public static final RegistryObject<Item> LEAD_INGOT = ITEMS.register("lead_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTab.ATOMIZED_TAB)));
    public static final RegistryObject<Item> URANIUM_INGOT = ITEMS.register("uranium_ingot", () -> new UraniumIngotItem(new Item.Properties().tab(ModCreativeTab.ATOMIZED_TAB)));
    public static final RegistryObject<Item> HAZMAT_HELMET = ITEMS.register("hazmat_helmet", () -> new ArmorItem(ModArmorMaterials.HAZMAT, EquipmentSlot.HEAD, new Item.Properties().tab(ModCreativeTab.ATOMIZED_EQUIPMENT_TAB)));
    public static final RegistryObject<Item> HAZMAT_CHEST = ITEMS.register("hazmat_chest", () -> new ArmorItem(ModArmorMaterials.HAZMAT, EquipmentSlot.CHEST, new Item.Properties().tab(ModCreativeTab.ATOMIZED_EQUIPMENT_TAB)));
    public static final RegistryObject<Item> HAZMAT_LEG = ITEMS.register("hazmat_leg", () -> new ArmorItem(ModArmorMaterials.HAZMAT, EquipmentSlot.LEGS, new Item.Properties().tab(ModCreativeTab.ATOMIZED_EQUIPMENT_TAB)));
    public static final RegistryObject<Item> HAZMAT_BOOTS = ITEMS.register("hazmat_boots", () -> new ArmorItem(ModArmorMaterials.HAZMAT, EquipmentSlot.FEET, new Item.Properties().tab(ModCreativeTab.ATOMIZED_EQUIPMENT_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
