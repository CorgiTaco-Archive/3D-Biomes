package corgitaco.biomes3d.api;

import corgitaco.biomes3d.world.layers.CaveBiomeLayers;
import net.minecraft.world.biome.Biome;

public class CaveBiomeManager {

    public static void addCaveBiome(Biome.Category category, Biome biome) {
        if (category == Biome.Category.JUNGLE)
            CaveBiomeLayers.jungleBiomes.add(biome);
        else if (category == Biome.Category.ICY)
            CaveBiomeLayers.icyBiomes.add(biome);
        else if (category == Biome.Category.DESERT || category == Biome.Category.MESA)
            CaveBiomeLayers.desertBiomes.add(biome);
        else if (category == Biome.Category.OCEAN)
            CaveBiomeLayers.oceanBiomes.add(biome);
        else
            CaveBiomeLayers.caveBiomes.add(biome);
    }
}
