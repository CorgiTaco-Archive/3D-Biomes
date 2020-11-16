package corgitaco.biomes3d.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class CaveSurfaceBuilderConfig implements IFeatureConfig {

    public static final Codec<CaveSurfaceBuilderConfig> CODEC = RecordCodecBuilder.create((codecRecorder) -> {
        return codecRecorder.group(BlockStateProvider.CODEC.fieldOf("top_block").forGetter((config) -> {
            return config.topBlockProvider;
        }), BlockStateProvider.CODEC.fieldOf("under_block").forGetter((config) -> {
            return config.topBlockProvider;
        })).apply(codecRecorder, CaveSurfaceBuilderConfig::new);
    });

    private final BlockStateProvider topBlockProvider;
    private final BlockStateProvider underBlockProvider;


    public CaveSurfaceBuilderConfig(BlockStateProvider topBlockProvider, BlockStateProvider underBlockProvider) {
        this.topBlockProvider = topBlockProvider;
        this.underBlockProvider = underBlockProvider;
    }

    public BlockStateProvider getTopBlockProvider() {
        return topBlockProvider;
    }

    public BlockStateProvider getUnderBlockProvider() {
        return underBlockProvider;
    }

}