package com.rwtema.monkmod;

import com.rwtema.monkmod.item.MonkItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(MonkMod.MODID)
public class MonkMod {
    public static final String MODID = "monk";
    public static final String NAME = "Monk Mod";
    public static final String VERSION = "0.1.0";

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> MONK_LEVEL_ITEM =
            ITEMS.register("monk_level", MonkItem::new);

    public MonkMod() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modBus);

        MinecraftForge.EVENT_BUS.register(MonkManager.class);
        MonkNetwork.register();
    }
}
