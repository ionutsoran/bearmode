package com.isoran.bearmode.events;

import com.isoran.bearmode.item.ModItems;
import com.isoran.bearmode.util.Config;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ExperienceBottleEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
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
}
