package corgitaco.biomes3d.mixin.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.biome.ColumnFuzzedBiomeMagnifier;
import net.minecraft.world.biome.FuzzedBiomeMagnifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ColumnFuzzedBiomeMagnifier.class)
public class ColumnFuzzedBiomeMagnifierMixin {

    //Pass in the Y Value.
    @Inject(method = "getBiome", at = @At("HEAD"), cancellable = true)
    public void getBiome(long seed, int x, int y, int z, BiomeManager.IBiomeReader biomeReader, CallbackInfoReturnable<Biome> cir) {
        cir.setReturnValue(FuzzedBiomeMagnifier.INSTANCE.getBiome(seed, x, y, z, biomeReader));
    }
}