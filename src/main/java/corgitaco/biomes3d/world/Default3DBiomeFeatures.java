package corgitaco.biomes3d.world;

import corgitaco.biomes3d.core.world.B3DConfiguredFeatures;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;

public class Default3DBiomeFeatures {

    public static void withSurfaceBuilder(BiomeGenerationSettings.Builder builder) {
        builder.withFeature(GenerationStage.Decoration.LAKES, B3DConfiguredFeatures.OVERGROWN_CAVE_SURFACE_BUILDER);
    }

}
