package com.isoran.bearmode.events;

import com.isoran.bearmode.block.ModFluids;
import com.isoran.bearmode.item.ModItems;
import com.isoran.bearmode.util.Config;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ExperienceBottleEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Collection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ModEvents {

    @SubscribeEvent
    public void onCopperedSheep(AttackEntityEvent event)
    {
        if(event.getPlayer().getItemInHand(Hand.MAIN_HAND).getItem() == ModItems.COPPERED_APPLE.get())
        {
            if(event.getTarget().isAlive())
            {
                LivingEntity target = (LivingEntity) event.getTarget();
                if(target instanceof SheepEntity)
                {
                    PlayerEntity player = event.getPlayer();

                    // "delete " one of the help Items
                    player.getItemInHand(Hand.MAIN_HAND).shrink(1);

                    target.addEffect(new EffectInstance(Effects.GLOWING, Config.COPPER_GLOW_DURATION.get()));

                    if(!player.isLocalPlayer())
                    {
                        String msg = TextFormatting.YELLOW + "Sheep is now glowing";
                        player.sendMessage(new StringTextComponent(msg), player.getUUID());
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onCopperedSheepDrops(LivingDropsEvent event) {
        LivingEntity entity = event.getEntityLiving();

        if (entity instanceof SheepEntity)
        {
            World world = entity.getCommandSenderWorld();
            Collection<ItemEntity> drops = event.getDrops();

            LogManager.getLogger().debug(entity.getActiveEffects());

            if(entity.hasEffect(Effects.GLOWING))
            {
                drops.add(new ItemEntity(world, entity.position().x,entity.position().y, entity.position().z,
                        new ItemStack(ModItems.COPPER_INGOT.get(), 2)));
            }
        }
    }

    @SubscribeEvent
    public void onCopperedShovelPickUpSnow(BlockEvent.BreakEvent event)
    {
        PlayerEntity player = event.getPlayer();

        if (player.getItemInHand(Hand.MAIN_HAND).getItem() == ModItems.COPPER_SHOVEL.get() &&
                event.getState().is(Blocks.SNOW))
        {
            event.getWorld().addFreshEntity(new ExperienceBottleEntity(
                    player.getCommandSenderWorld(),
                    player.position().x,
                    player.position().y + 10,
                    player.position().z));
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onRenderFog(EntityViewRenderEvent.FogDensity event) {
        if (event.getInfo().getFluidInCamera().getFluidState() == ModFluids.OIL_FLUID.get().getSource(false)
                || event.getInfo().getFluidInCamera().getFluidState() ==
                ModFluids.OIL_FLUID.get().getFlowing(8,true))
        {
            RenderSystem.fogMode(GlStateManager.FogMode.EXP);
            event.setDensity(1.5f);
            event.setCanceled(true);
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onRenderFogColor(EntityViewRenderEvent.FogColors event) {
        if (event.getInfo().getFluidInCamera().getFluidState() == ModFluids.OIL_FLUID.get().getSource(false)
                || event.getInfo().getFluidInCamera().getFluidState() ==
                ModFluids.OIL_FLUID.get().getFlowing(8,true))
        {
            event.setRed(0.0f);
            event.setGreen(0.0f);
            event.setBlue(0.0f);
//            String msg = TextFormatting.GREEN + " 1 " + event.getInfo().getFluidInCamera().getFluidState();
//            Minecraft.getInstance().player.sendMessage(new StringTextComponent(msg), Minecraft.getInstance().player.getUUID());
//            String msg1 = TextFormatting.YELLOW + " 2 " + ModFluids.OIL_FLUID.get().getFlowing(8,true);
//            Minecraft.getInstance().player.sendMessage(new StringTextComponent(msg1), Minecraft.getInstance().player.getUUID());
        }
    }
}
