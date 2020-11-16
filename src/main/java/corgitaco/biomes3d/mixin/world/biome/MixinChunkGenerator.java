package corgitaco.biomes3d.mixin.world.biome;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.ReportedException;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.structure.StructureManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkGenerator.class)
public class MixinChunkGenerator {

    @Shadow
    @Final
    protected BiomeProvider biomeProvider;

    @Inject(at = @At("HEAD"), method = "func_230351_a_(Lnet/minecraft/world/gen/WorldGenRegion;Lnet/minecraft/world/gen/feature/structure/StructureManager;)V", cancellable = true)
    private void injectLayeredBiomeDecorations(WorldGenRegion region, StructureManager manager, CallbackInfo ci) {
        int chunkX = region.getMainChunkX();
        int chunkZ = region.getMainChunkZ();
        int x = chunkX * 16;
        int z = chunkZ * 16;
        BlockPos blockpos = new BlockPos(x, 0, z);
        SharedSeedRandom sharedseedrandom = new SharedSeedRandom();
        long seed = sharedseedrandom.setDecorationSeed(region.getSeed(), x, z);

        //The original check.
        Biome caveBiome = this.biomeProvider.getNoiseBiome((chunkX << 2) + 2, 2, (chunkZ << 2) + 2);

        //Get biomes from the surface.
        Biome surfaceBiome = this.biomeProvider.getNoiseBiome((chunkX << 2) + 2, 16, (chunkZ << 2) + 2);
        //If biomes are both the same, do not generate features twice
        if (caveBiome == surfaceBiome)
            return;

        try {
            surfaceBiome.generateFeatures(manager, (ChunkGenerator)(Object)this, region, seed, sharedseedrandom, blockpos);
        } catch (Exception exception) {
            CrashReport crashreport = CrashReport.makeCrashReport(exception, "Surface Biome decoration");
            crashreport.makeCategory("Generation").addDetail("CenterX", chunkX).addDetail("CenterZ", chunkZ).addDetail("Seed", seed).addDetail("Biome", surfaceBiome);
            throw new ReportedException(crashreport);
        }
    }


    //Get carvers from the surface biomes as opposed to the underground
    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/provider/BiomeProvider;getNoiseBiome(III)Lnet/minecraft/world/biome/Biome;"), method = "func_230350_a_")
    private Biome generationSettings(BiomeProvider biomeProvider, int x, int y, int z) {
        return this.biomeProvider.getNoiseBiome(x, 16, z);

    }
}
