package io.github.dewidos.atomized.screen.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

import java.util.List;
import java.util.function.Function;

public class EnergyInfoArea extends InfoArea {
    private int energyStoredIndex;
    private int maxEnergyIndex;
    private Function<Integer, Integer> dataGetter;

    public EnergyInfoArea(int xMin, int yMin, Function<Integer, Integer> dataGetter, int energyStoredIndex, int maxEnergyIndex, int width, int height)  {
        super(new Rect2i(xMin, yMin, width, height));
        this.dataGetter = dataGetter;
        this.energyStoredIndex = energyStoredIndex;
        this.maxEnergyIndex = maxEnergyIndex;
    }

    public List<Component> getTooltips() {
        return List.of(new TextComponent(this.dataGetter.apply(energyStoredIndex) +"/"+ this.dataGetter.apply(maxEnergyIndex) +" FE"));
    }

    @Override
    public void draw(PoseStack transform) {
    }
}
