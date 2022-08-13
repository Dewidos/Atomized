package io.github.dewidos.atomized.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.dewidos.atomized.Atomized;
import io.github.dewidos.atomized.container.FurnaceGeneratorContainer;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class FurnaceGeneratorScreen extends AbstractContainerScreen<FurnaceGeneratorContainer> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Atomized.MOD_ID,
            "textures/gui/furnace_generator.png");

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
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        super.render(stack, mouseX, mouseY, partialTicks);

        final int energyStored = this.menu.data.get(2);
        final int maxEnergy = this.menu.data.get(3);
        final int scaledHeight = (int) mapNumber(energyStored, 0, maxEnergy, 0, 62);
        bindTexture();
        blit(stack, this.leftPos + 118, this.topPos + 75 - scaledHeight, 176, 62 - scaledHeight, 30, scaledHeight);

        final int progress = this.menu.data.get(0);
        final int maxProgress = this.menu.data.get(1);
        final int scaledProgress = (int) mapNumber(progress, 0, maxProgress, 0, 22);
        bindTexture();
        blit(stack, this.leftPos + 76, this.topPos + 36, 206, 0, scaledProgress, 15);

        this.font.draw(stack, this.title, this.leftPos + 7, this.topPos + 5, 0x404040);
        this.font.draw(stack, this.playerInventoryTitle, this.leftPos + 8, this.topPos + 75, 0x404040);
        drawCenteredString(stack, this.font, energyStored + "", this.leftPos + 133, this.topPos + 4, 0xFFFFFF);
    }

    @Override
    protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
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
