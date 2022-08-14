package io.github.dewidos.atomized.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.dewidos.atomized.Atomized;
import io.github.dewidos.atomized.block.entity.LeadChestBlockEntity;
import io.github.dewidos.atomized.container.LeadChestContainer;
import io.github.dewidos.atomized.network.PacketHandler;
import io.github.dewidos.atomized.network.ServerboundLeadChestUpdatePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.client.gui.widget.ExtendedButton;
import org.jetbrains.annotations.NotNull;

public class LeadChestScreen extends AbstractContainerScreen<LeadChestContainer> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Atomized.MOD_ID, "textures/gui/lead_chest.png");

    public LeadChestScreen(LeadChestContainer pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        leftPos = 0;
        topPos = 0;
        imageWidth = 176;
        imageHeight = 168;
        // TODO: Make sure that these values are correct
    }

    @Override
    protected void renderBg(@NotNull PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        renderBackground(pPoseStack);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        blit(pPoseStack, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        this.font.draw(pPoseStack, this.title, this.leftPos + 8, this.topPos + 6, 0x404040);
        this.font.draw(pPoseStack, this.playerInventoryTitle, this.leftPos + 8, this.topPos + 75, 0x404040);
    }

    @Override
    protected void renderLabels(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY) {}

    @Override
    protected void init() {
        super.init();
        addRenderableWidget(new ExtendedButton(leftPos + 8, topPos + imageHeight + 5, 64, 16, new TextComponent("Eject"), btn -> PacketHandler.INSTANCE.sendToServer(new ServerboundLeadChestUpdatePacket(getMinecraft().player.eyeBlockPosition(), true))));
    }
}
