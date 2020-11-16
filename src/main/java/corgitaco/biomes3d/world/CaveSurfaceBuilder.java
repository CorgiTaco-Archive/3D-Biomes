package corgitaco.biomes3d.world;

import com.mojang.serialization.Codec;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class CaveSurfaceBuilder extends Feature<CaveSurfaceBuilderConfig> {


    public CaveSurfaceBuilder(Codec<CaveSurfaceBuilderConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, CaveSurfaceBuilderConfig config) {
        BlockPos.Mutable mutable = new BlockPos.Mutable().setPos(pos);
        if (!world.getBlockState(mutable.down()).isIn(BlockTags.BASE_STONE_OVERWORLD))
            return false;

        for (int height = 0; height < 3; height++) {
            if (world.getBlockState(mutable).isIn(BlockTags.BASE_STONE_OVERWORLD)) {
                if (height == 0)
                    world.setBlockState(mutable, config.getTopBlockProvider().getBlockState(rand, mutable), 2);
                else
                    world.setBlockState(mutable, config.getUnderBlockProvider().getBlockState(rand, mutable), 2);
            }
            mutable.move(Direction.DOWN);
        }

        return true;
    }
}
