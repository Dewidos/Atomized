package io.github.dewidos.atomized.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ModCreativeTab {

    public static final CreativeModeTab ATOMIZED_TAB = new CreativeModeTab("Atomized") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModItems.STEEL_INGOT.get());
        }
    };

    public static final CreativeModeTab ATOMIZED_EQUIPMENT_TAB = new CreativeModeTab("AtomizedEquipment") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModItems.HAZMAT_HELMET.get());
        }
    };

}
