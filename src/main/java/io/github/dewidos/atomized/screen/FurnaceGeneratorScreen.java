package io.github.dewidos.atomized.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.dewidos.atomized.Atomized;
import io.github.dewidos.atomized.block.entity.FurnaceGeneratorBlockEntity;
import io.github.dewidos.atomized.container.FurnaceGeneratorContainer;
import io.github.dewidos.atomized.screen.renderer.EnergyInfoArea;
import io.github.dewidos.atomized.util.MouseUtil;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.Optional;

public class FurnaceGeneratorScreen extends AbstractContainerScreen<FurnaceGeneratorContainer> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Atomized.MOD_ID,
            "textures/gui/furnace_generator.png");

    private EnergyInfoArea energyInfoArea;

    public FurnaceGeneratorScreen(FurnaceGeneratorContainer container, Inventory playerInv, Component title) {
        super(container, playerInv, title);
        this.leftPos = 0;
        this.topPos = 0;
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        renderBackground(pPoseStack);
        bindTexture();
        blit(pPoseStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        energyInfoArea.draw(pPoseStack);
    }

    private void assignEnergyInfoArea() {
        energyInfoArea = new EnergyInfoArea(((width - imageWidth) / 2) +  156,
                ((height - imageHeight) / 2) + 13, FurnaceGeneratorBlockEntity.energyStorage, 15, 46);
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);

        final int energyStored = this.menu.data.get(2);
        final int maxEnergy = this.menu.data.get(3);
        final int scaledHeight = (int) mapNumber(energyStored, 0, maxEnergy, 0, 47);
        bindTexture();
        blit(stack, this.leftPos + 116, this.topPos + 55 - scaledHeight, 176, 47 - scaledHeight, 16, scaledHeight);

        final int progress = this.menu.data.get(0);
        final int maxProgress = this.menu.data.get(1);
        final int scaledProgress = (int) mapNumber(progress, 0, maxProgress, 0, 22);
        bindTexture();
        blit(stack, this.leftPos + 69, this.topPos + 37, 206, 0, scaledProgress, 15);

        this.font.draw(stack, this.title, this.leftPos + 7, this.topPos + 5, 0x404040);
        this.font.draw(stack, this.playerInventoryTitle, this.leftPos + 8, this.topPos + 75, 0x404040);
    }

    @Override
    public void init() {
        super.init();
        assignEnergyInfoArea();
    }

    private void renderEnergyArea(PoseStack pPoseStack, int pMouseX, int pMouseY, int x, int y) {
        if(isMouseAboveArea(pMouseX, pMouseY, x, y, 116, 8, 15, 46)) {
            renderTooltip(pPoseStack, energyInfoArea.getTooltips(),
                    Optional.empty(), pMouseX - x, pMouseY - y);
        }
    }



    @Override
    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        renderEnergyArea(pPoseStack, pMouseX, pMouseY, x, y);
    }

    private boolean isMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int offsetX, int offsetY, int width, int height) {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + offsetX, y + offsetY, width, height);
    }

    public static void bindTexture() {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
    }

    public static double mapNumber(double value, double rangeMin, double rangeMax, double resultMin, double resultMax) {
        return (value - rangeMin) / (rangeMax - rangeMin) * (resultMax - resultMin) + resultMin;
    }
}
