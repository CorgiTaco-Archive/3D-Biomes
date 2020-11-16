package corgitaco.biomes3d.core.world;

import corgitaco.biomes3d.Biomes3D;
import corgitaco.biomes3d.world.CaveSurfaceBuilderConfig;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.CaveEdgeConfig;
import net.minecraft.world.gen.placement.Placement;

public class B3DConfiguredFeatures {


    public static final ConfiguredFeature<?, ?> OVERGROWN_CAVE_SURFACE_BUILDER = createConfiguredFeature("overgrown_sb", B3DFeatures.SURFACE_BUILDER.withConfiguration(new CaveSurfaceBuilderConfig(new SimpleBlockStateProvider(Blocks.GRASS_BLOCK.getDefaultState()), new SimpleBlockStateProvider(Blocks.DIRT.getDefaultState()))).withPlacement(Placement.CARVING_MASK.configure(new CaveEdgeConfig(GenerationStage.Carving.AIR, 1.0F))));




    private static <FC extends IFeatureConfig, F extends Feature<FC>, CF extends ConfiguredFeature<FC, F>> CF createConfiguredFeature(String id, CF configuredFeature) {
        ResourceLocation bygID = new ResourceLocation(Biomes3D.MOD_ID, id);
        if (WorldGenRegistries.CONFIGURED_FEATURE.keySet().contains(bygID))
            throw new IllegalStateException("Configured Feature ID: \"" + bygID.toString() + "\" already exists in the Configured Features registry!");

        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, bygID, configuredFeature);
        return configuredFeature;
    }

}
