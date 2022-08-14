package io.github.dewidos.atomized.screen.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.dewidos.atomized.block.entity.util.CustomEnergyStorage;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

import java.util.List;

public class EnergyInfoArea extends InfoArea {
    private final CustomEnergyStorage energy;

    public EnergyInfoArea(int xMin, int yMin, CustomEnergyStorage energy, int width, int height)  {
        super(new Rect2i(xMin, yMin, width, height));
        this.energy = energy;
    }

    public List<Component> getTooltips() {
        return List.of(new TextComponent(this.energy.getEnergyStored()+"/"+this.energy.getMaxEnergyStored()+" FE"));
    }

    @Override
    public void draw(PoseStack transform) {
    }
}
