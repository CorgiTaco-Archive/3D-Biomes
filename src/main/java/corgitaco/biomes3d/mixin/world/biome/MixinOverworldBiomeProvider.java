package corgitaco.biomes3d.mixin.world.biome;

import corgitaco.biomes3d.world.layers.CaveBiomeLayers;
import corgitaco.biomes3d.world.layers.DatapackLayer;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.OverworldBiomeProvider;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OverworldBiomeProvider.class)
public class MixinOverworldBiomeProvider {

    private DatapackLayer oceanLayer;
    private DatapackLayer jungleLayer;
    private DatapackLayer icyLayer;
    private DatapackLayer desertLayer;
    private DatapackLayer caveLayer;

    @Inject(at = @At("RETURN"), method = "<init>(JZZLnet/minecraft/util/registry/Registry;)V")
    private void initializeCaveBiomeLayers(long seed, boolean legacyBiomes, boolean largeBiomes, Registry<Biome> lookupRegistry, CallbackInfo ci) {
        oceanLayer = CaveBiomeLayers.stackOceanLayers(lookupRegistry, seed);
        jungleLayer = CaveBiomeLayers.stackJungleCaveLayers(lookupRegistry, seed);
        icyLayer = CaveBiomeLayers.stackIcyCaveLayers(lookupRegistry, seed);
        desertLayer = CaveBiomeLayers.stackDesertCaveLayers(lookupRegistry, seed);
        caveLayer = CaveBiomeLayers.stackCaveLayers(lookupRegistry, seed);
    }

    @Shadow @Final private Registry<Biome> lookupRegistry;

    @Inject(at = @At("RETURN"), method = "getNoiseBiome(III)Lnet/minecraft/world/biome/Biome;", cancellable = true)
    private void inject3DBiomeProvider(int x, int y, int z, CallbackInfoReturnable<Biome> cir) {
        Biome result = cir.getReturnValue();

        if (y >= 12)
            cir.setReturnValue(result);
        else {
            if (result.getCategory() == Biome.Category.JUNGLE)
                cir.setReturnValue(jungleLayer.sample(lookupRegistry, x, z));
            else if (result.getCategory() == Biome.Category.ICY)
                cir.setReturnValue(icyLayer.sample(lookupRegistry, x, z));
            else if (result.getCategory() == Biome.Category.DESERT || result.getCategory() == Biome.Category.MESA)
                cir.setReturnValue(desertLayer.sample(lookupRegistry, x, z));
            else if (result.getCategory() == Biome.Category.OCEAN)
                cir.setReturnValue(oceanLayer.sample(lookupRegistry, x, z));
            else
                cir.setReturnValue(caveLayer.sample(lookupRegistry, x, z));
        }
    }
}