package com.github.hespercq.ie_hcq_ponder;

import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(IEPonder.MODID)
// Client only mod
@Mod.EventBusSubscriber(modid = IEPonder.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class IEPonder {
    public static final String MODID = "ie_hcq_ponder";

    public static final ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public IEPonder(FMLJavaModLoadingContext context) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void clientInit(final FMLClientSetupEvent event) {
        PonderIndex.addPlugin(new IEPonderPlugin());
    }
}
