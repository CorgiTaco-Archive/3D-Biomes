package corgitaco.biomes3d.core.world;

import corgitaco.biomes3d.Biomes3D;
import corgitaco.biomes3d.world.Default3DBiomeFeatures;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

import java.util.HashSet;
import java.util.Set;

public class B3DBiomes {
    public static final Set<Biome> biomes = new HashSet<>();

    public static final Biome OVERGROWN_CAVE = createBiome("overgrown_cave", overgrownCaveBiome(0, 0, new ConfiguredSurfaceBuilder<>(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState(), Blocks.AIR.getDefaultState()))));

    public static void init() {}

    private static int getSkyColorWithTemperatureModifier(float temperature) {
        float tempDividedBy3 = temperature / 3.0F;
        tempDividedBy3 = MathHelper.clamp(tempDividedBy3, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.62222224F - tempDividedBy3 * 0.05F, 0.5F + tempDividedBy3 * 0.1F, 1.0F);
    }


    public static Biome overgrownCaveBiome(float depth, float scale, ConfiguredSurfaceBuilder<SurfaceBuilderConfig> surfaceBuilder) {
        MobSpawnInfo.Builder spawn = new MobSpawnInfo.Builder();
        DefaultBiomeFeatures.withPassiveMobs(spawn);
        spawn.withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.LLAMA, 5, 4, 6));
        DefaultBiomeFeatures.withBatsAndHostiles(spawn);
        BiomeGenerationSettings.Builder builder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(surfaceBuilder);
        Default3DBiomeFeatures.withSurfaceBuilder(builder);
        return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.EXTREME_HILLS).depth(depth).scale(scale).temperature(0.2F).downfall(0.3F).setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011).setFogColor(12638463).withSkyColor(getSkyColorWithTemperatureModifier(0.2F)).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build()).withMobSpawnSettings(spawn.copy()).withGenerationSettings(builder.build()).build();
    }

    public static Biome createBiome(String id, Biome biome) {
        ResourceLocation bygID = new ResourceLocation(Biomes3D.MOD_ID, id);
        if (WorldGenRegistries.BIOME.keySet().contains(bygID))
            throw new IllegalStateException("Biome ID: \"" + bygID.toString() + "\" already exists in the Biome registry!");

//        Registry.register(WorldGenRegistries.BIOME, bygID, biome);
        biome.setRegistryName(bygID); //Forge
        biomes.add(biome);
        return biome;
    }
}
