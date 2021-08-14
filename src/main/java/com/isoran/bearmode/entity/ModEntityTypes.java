package com.isoran.bearmode.entity;

import com.isoran.bearmode.Bearmode;
import com.isoran.bearmode.util.Registration;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class ModEntityTypes
{
    public static final RegistryObject<EntityType<BuffaloEntity>> BUFFALO =
            Registration.ENTITIES.register("buffalo",
                    () -> EntityType.Builder
                            .of(BuffaloEntity::new, EntityClassification.CREATURE)
                            .sized(1.5f, 1.5f)
                            .build(new ResourceLocation(Bearmode.MOD_ID + "buffalo").toString()));

    public static final RegistryObject<EntityType<JuicedVillagerEntity>> JUICED_VILLAGER =
            Registration.ENTITIES.register("juiced_villager",
                    () -> EntityType.Builder
                            .of(JuicedVillagerEntity::new, EntityClassification.CREATURE)
                            .sized(0.9f, 2.5f)
                            .build(new ResourceLocation(Bearmode.MOD_ID + "juiced_villager").toString()));

    public static void register() {}
}
