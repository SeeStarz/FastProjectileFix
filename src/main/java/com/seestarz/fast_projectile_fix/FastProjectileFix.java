package com.seestarz.fast_projectile_fix;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FastProjectileFix.MOD_ID)
public class FastProjectileFix
{
    public static final String MOD_ID = "fast_projectile_fix";
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public FastProjectileFix() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
}