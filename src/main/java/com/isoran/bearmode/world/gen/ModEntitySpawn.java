package com.isoran.bearmode.world.gen;

import com.isoran.bearmode.Bearmode;
import com.isoran.bearmode.entity.ModEntityTypes;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = Bearmode.MOD_ID)
public class ModEntitySpawn {

    @SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent event)
    {
        RegistryKey<Biome> key = RegistryKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);

        if (!types.contains(BiomeDictionary.Type.NETHER) && !types.contains(BiomeDictionary.Type.END)
        && !types.contains(BiomeDictionary.Type.OCEAN) &&!types.contains(BiomeDictionary.Type.RIVER)
        && !types.contains(BiomeDictionary.Type.BEACH))

        {
            List<MobSpawnInfo.Spawners> base = event.getSpawns().getSpawner(EntityClassification.CREATURE);
            base.add(new MobSpawnInfo.Spawners(ModEntityTypes.BUFFALO.get(),
                    30, 2, 5));
        }
    }
}
