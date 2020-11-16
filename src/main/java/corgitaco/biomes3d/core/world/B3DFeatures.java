package corgitaco.biomes3d.core.world;

import corgitaco.biomes3d.Biomes3D;
import corgitaco.biomes3d.world.CaveSurfaceBuilder;
import corgitaco.biomes3d.world.CaveSurfaceBuilderConfig;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;

import java.util.HashSet;
import java.util.Set;

public class B3DFeatures {

    public static Set<Feature<?>> features = new HashSet<>();



    public static Feature<CaveSurfaceBuilderConfig> SURFACE_BUILDER = createFeature("surface_builder", new CaveSurfaceBuilder(CaveSurfaceBuilderConfig.CODEC));
    public static void init() {}


    private static <C extends IFeatureConfig, F extends Feature<C>> F createFeature(String id, F feature) {
        ResourceLocation bygID = new ResourceLocation(Biomes3D.MOD_ID, id);
        if (Registry.FEATURE.keySet().contains(bygID))
            throw new IllegalStateException("Feature ID: \"" + bygID.toString() + "\" already exists in the Features registry!");

//        Registry.register(Registry.FEATURE, bygID, feature);
        feature.setRegistryName(bygID); //Forge
        features.add(feature);
        return feature;
    }
}
