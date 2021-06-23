package com.isoran.bearmode.screens;

import com.isoran.bearmode.Bearmode;
import com.isoran.bearmode.container.PipeContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class PipeScreen extends ContainerScreen<PipeContainer> {

    private final ResourceLocation GUI = new ResourceLocation(Bearmode.MOD_ID, "textures/gui/pipe_gui.png");

    public PipeScreen(PipeContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks)
    {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(MatrixStack matrixStack, int x, int y) {
        drawString(matrixStack,
                Minecraft.getInstance().font,
                "Energy:" + menu.getEnergyLevel(),
                28,
                10,
                0xffffff);
        //super.renderLabels(p_230451_1_, p_230451_2_, p_230451_3_);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float particleTicks, int x, int y)
    {
        RenderSystem.color4f(1.0f,1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(GUI);
        int i =this.getGuiLeft();
        int j = this.getGuiTop();
        this.blit(matrixStack, i, j, 0, 0, this.getXSize(), this.getYSize());

        this.blit(matrixStack,
                i+13,
                j +9,
                176,
                0,
                11,
                64- menu.getEnergyLevel());
    }
}
