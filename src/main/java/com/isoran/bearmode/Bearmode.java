package com.isoran.bearmode;

import com.isoran.bearmode.block.ModBlocks;
import com.isoran.bearmode.block.ModFluids;
import com.isoran.bearmode.container.ModContainers;
import com.isoran.bearmode.events.ModEvents;
import com.isoran.bearmode.item.ModItems;
import com.isoran.bearmode.particles.BearParticle;
import com.isoran.bearmode.particles.ModParticles;
import com.isoran.bearmode.setup.ClientProxy;
import com.isoran.bearmode.setup.IProxy;
import com.isoran.bearmode.setup.ServerProxy;
import com.isoran.bearmode.tileentity.ModTileEntities;
import com.isoran.bearmode.util.Config;
import com.isoran.bearmode.util.Registration;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.AshParticle;
import net.minecraft.client.particle.BreakingParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Bearmode.MOD_ID)
public class Bearmode
{
    public static final String MOD_ID = "bearmode";

    private static final Logger LOGGER = LogManager.getLogger();

    public static final ItemGroup COURSE_TAB = new ItemGroup("courseTab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.COPPER_WIRE.get());
        }
    };

    public static IProxy proxy;

    public Bearmode()
    {
        proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        registerModAdditions();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
      registerConfigs();

      proxy.init();

      loadConfigs();
    }

    private void registerConfigs()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);
    }

    private void loadConfigs()
    {
        Config.loadConfigFile(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("bearmode-client.toml").toString());
        Config.loadConfigFile(Config.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve("bearmode-server.toml").toString());

    }

    private void registerModAdditions()
    {
        //Inits the registration of our additions
        Registration.init();

        //register items block etc added by our mod
        //Minecraft.getInstance().particles.registerFactory(DeferredRegistration.POWER_CRYSTAL_PARTICLE.get(), new SHParticle.Factory(Color.ROYALBLUE));
        ModItems.register();
        ModBlocks.register();
        ModParticles.register();
        ModFluids.register();
        ModTileEntities.register();
        ModContainers.register();

        //register mod events
        MinecraftForge.EVENT_BUS.register(new ModEvents());


    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().options);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
