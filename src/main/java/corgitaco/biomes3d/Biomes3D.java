package corgitaco.biomes3d;

import corgitaco.biomes3d.api.CaveBiomeManager;
import corgitaco.biomes3d.core.world.B3DBiomes;
import corgitaco.biomes3d.core.world.B3DFeatures;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("biomes3d")
public class Biomes3D {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "biomes3d";

    public Biomes3D() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        CaveBiomeManager.addCaveBiome(Biome.Category.JUNGLE, WorldGenRegistries.BIOME.getValueForKey(Biomes.JUNGLE));
        CaveBiomeManager.addCaveBiome(Biome.Category.ICY, WorldGenRegistries.BIOME.getValueForKey(Biomes.ICE_SPIKES));
        CaveBiomeManager.addCaveBiome(Biome.Category.DESERT, WorldGenRegistries.BIOME.getValueForKey(Biomes.DESERT));
        CaveBiomeManager.addCaveBiome(Biome.Category.OCEAN, WorldGenRegistries.BIOME.getValueForKey(Biomes.OCEAN));
        CaveBiomeManager.addCaveBiome(Biome.Category.NONE, WorldGenRegistries.BIOME.getValueForKey(Biomes.PLAINS));
    }


    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class BYGWorldGenRegistries {

        @SubscribeEvent
        public static void registerBiomes(RegistryEvent.Register<Biome> event) {
            LOGGER.debug("3D Biomes: Registering biomes...");
            B3DBiomes.init();
            B3DBiomes.biomes.forEach(biome -> event.getRegistry().register(biome));
            LOGGER.info("3D Biomes: Biomes registered!");
        }

        @SubscribeEvent
        public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
            LOGGER.debug("3D Biomes: Registering features...");
            B3DBiomes.init();
            B3DFeatures.features.forEach(feature -> event.getRegistry().register(feature));
            LOGGER.info("3D Biomes: Features registered!");
        }
    }
}
