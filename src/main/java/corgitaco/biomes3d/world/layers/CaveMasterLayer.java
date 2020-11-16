package corgitaco.biomes3d.world.layers;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IAreaTransformer0;

import java.util.List;

public class CaveMasterLayer implements IAreaTransformer0 {
    private final Registry<Biome> biomeRegistry;
    private final List<Biome> biomeList;

    public CaveMasterLayer(Registry<Biome> biomeRegistry, List<Biome> biomeList) {
        this.biomeRegistry = biomeRegistry;
        this.biomeList = biomeList;
    }

    @Override
    public int apply(INoiseRandom rand, int x, int y) {
        return getRandomCaveBiomes(this.biomeRegistry, rand);
    }

    public int getRandomCaveBiomes(Registry<Biome> biomeRegistry, INoiseRandom rand) {
        return WorldGenRegistries.BIOME.getId(biomeList.get(rand.random(biomeList.size())));
    }
}