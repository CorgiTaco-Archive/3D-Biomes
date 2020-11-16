package corgitaco.biomes3d.world.layers;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.ZoomLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongFunction;

public class CaveBiomeLayers {
    public static final List<Biome> oceanBiomes = new ArrayList<>();
    public static final List<Biome> jungleBiomes = new ArrayList<>();
    public static final List<Biome> icyBiomes = new ArrayList<>();
    public static final List<Biome> desertBiomes = new ArrayList<>();
    public static final List<Biome> caveBiomes = new ArrayList<>();

    public static DatapackLayer stackJungleCaveLayers(Registry<Biome> biomeRegistry, long seed) {
        LongFunction<IExtendedNoiseRandom<LazyArea>> randomProvider = salt -> new LazyAreaLayerContext(1, seed, salt);

        IAreaFactory<LazyArea> netherLayer = new CaveMasterLayer(biomeRegistry, jungleBiomes).apply(randomProvider.apply(485868686L));

        for (int netherBiomeSize = 0; netherBiomeSize <= 1; netherBiomeSize++) {
            netherLayer = ZoomLayer.NORMAL.apply(randomProvider.apply(28585L + netherBiomeSize), netherLayer);
        }
        netherLayer = ZoomLayer.FUZZY.apply(randomProvider.apply(958687L), netherLayer);

        return new DatapackLayer(netherLayer);
    }

    public static DatapackLayer stackIcyCaveLayers(Registry<Biome> biomeRegistry, long seed) {
        LongFunction<IExtendedNoiseRandom<LazyArea>> randomProvider = salt -> new LazyAreaLayerContext(1, seed, salt);

        IAreaFactory<LazyArea> netherLayer = new CaveMasterLayer(biomeRegistry, icyBiomes).apply(randomProvider.apply(485868686L));

        for (int netherBiomeSize = 0; netherBiomeSize <= 1; netherBiomeSize++) {
            netherLayer = ZoomLayer.NORMAL.apply(randomProvider.apply(28585L + netherBiomeSize), netherLayer);
        }
        netherLayer = ZoomLayer.FUZZY.apply(randomProvider.apply(958687L), netherLayer);

        return new DatapackLayer(netherLayer);
    }

    public static DatapackLayer stackDesertCaveLayers(Registry<Biome> biomeRegistry, long seed) {
        LongFunction<IExtendedNoiseRandom<LazyArea>> randomProvider = salt -> new LazyAreaLayerContext(1, seed, salt);

        IAreaFactory<LazyArea> netherLayer = new CaveMasterLayer(biomeRegistry, desertBiomes).apply(randomProvider.apply(485868686L));

        for (int netherBiomeSize = 0; netherBiomeSize <= 1; netherBiomeSize++) {
            netherLayer = ZoomLayer.NORMAL.apply(randomProvider.apply(28585L + netherBiomeSize), netherLayer);
        }
        netherLayer = ZoomLayer.FUZZY.apply(randomProvider.apply(958687L), netherLayer);

        return new DatapackLayer(netherLayer);
    }

    public static DatapackLayer stackCaveLayers(Registry<Biome> biomeRegistry, long seed) {
        LongFunction<IExtendedNoiseRandom<LazyArea>> randomProvider = salt -> new LazyAreaLayerContext(1, seed, salt);

        IAreaFactory<LazyArea> netherLayer = new CaveMasterLayer(biomeRegistry, caveBiomes).apply(randomProvider.apply(485868686L));

        for (int netherBiomeSize = 0; netherBiomeSize <= 1; netherBiomeSize++) {
            netherLayer = ZoomLayer.NORMAL.apply(randomProvider.apply(28585L + netherBiomeSize), netherLayer);
        }
        netherLayer = ZoomLayer.FUZZY.apply(randomProvider.apply(958687L), netherLayer);

        return new DatapackLayer(netherLayer);
    }

    public static DatapackLayer stackOceanLayers(Registry<Biome> biomeRegistry, long seed) {
        LongFunction<IExtendedNoiseRandom<LazyArea>> randomProvider = salt -> new LazyAreaLayerContext(1, seed, salt);

        IAreaFactory<LazyArea> netherLayer = new CaveMasterLayer(biomeRegistry, oceanBiomes).apply(randomProvider.apply(485868686L));

        for (int netherBiomeSize = 0; netherBiomeSize <= 1; netherBiomeSize++) {
            netherLayer = ZoomLayer.NORMAL.apply(randomProvider.apply(28585L + netherBiomeSize), netherLayer);
        }
        netherLayer = ZoomLayer.FUZZY.apply(randomProvider.apply(958687L), netherLayer);

        return new DatapackLayer(netherLayer);
    }
}
