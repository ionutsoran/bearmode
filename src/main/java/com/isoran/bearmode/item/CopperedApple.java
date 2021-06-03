package com.isoran.bearmode.item;

import com.isoran.bearmode.Bearmode;
import com.isoran.bearmode.util.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class CopperedApple extends Item {

    public CopperedApple(){

        super(new Properties().tab(Bearmode.COURSE_TAB)
                .food(new Food.Builder()
                        .nutrition(5)
                        .saturationMod(1.5f)
                        .effect(
                                () -> new EffectInstance(Effects.GLOWING, 300, 1),1)
                        .build()));
    }

    @Override
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag)
    {
        if(KeyboardHelper.isHoldingShift())
        {
            tooltip.add(new StringTextComponent("Turns sheep into Copper Ingots"));
        }
        else{
            tooltip.add(new StringTextComponent("Hold" + "\u00A7e" +
                    " SHIFT" + "\u00A77" + " for more information!"));
        }
    }
}
