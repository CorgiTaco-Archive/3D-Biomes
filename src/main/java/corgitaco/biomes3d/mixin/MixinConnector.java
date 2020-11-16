package corgitaco.biomes3d.mixin;

import corgitaco.biomes3d.Biomes3D;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.connect.IMixinConnector;

public class MixinConnector implements IMixinConnector {

    @Override
    public void connect() {
        Biomes3D.LOGGER.debug("3D Biomes: Connecting Mixin...");
        Mixins.addConfiguration("3dbiomes.mixins.json");
        Biomes3D.LOGGER.info("3D Biomes: Mixin Connected!");
    }
}