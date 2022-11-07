package com.seestarz.fast_projectile_fix;

import com.seestarz.fast_projectile_fix.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
}