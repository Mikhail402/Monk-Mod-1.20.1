package com.rwtema.monkmod;

import com.rwtema.monkmod.abilities.MonkAbilityArmor;
import com.rwtema.monkmod.abilities.MonkAbilityArmorToughness;
import com.rwtema.monkmod.abilities.MonkAbilityFly;
import com.rwtema.monkmod.abilities.MonkAbilityHealth;
import com.rwtema.monkmod.abilities.MonkAbilityJump;
import com.rwtema.monkmod.abilities.MonkAbilityProtection;
import com.rwtema.monkmod.abilities.MonkAbilitySpeed;
import com.rwtema.monkmod.abilities.MonkAbilityStrength;
import com.rwtema.monkmod.abilities.MonkAbilityWaterBreathing;
import com.rwtema.monkmod.item.MonkItem;
import com.rwtema.monkmod.levels.MonkLevelManager;
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
    public static final int MAX_LEVEL = 20;

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> MONK_LEVEL_ITEM =
            ITEMS.register("monk_level", MonkItem::new);

    public static final MonkAbilitySpeed SPEED = new MonkAbilitySpeed(0.12D);
    public static final MonkAbilityJump JUMP = new MonkAbilityJump(1.9D);
    public static final MonkAbilityHealth HEALTH = new MonkAbilityHealth(2.0D);
    public static final MonkAbilityArmor ARMOR = new MonkAbilityArmor(1.0D, 0.5D);
    public static final MonkAbilityArmorToughness ARMOR_TOUGHNESS = new MonkAbilityArmorToughness(1.0D);
    public static final MonkAbilityStrength STRENGTH = new MonkAbilityStrength();
    public static final MonkAbilityWaterBreathing WATER_BREATHING = new MonkAbilityWaterBreathing();
    public static final MonkAbilityProtection PROTECTION = new MonkAbilityProtection();
    public static final MonkAbilityFly FLY = new MonkAbilityFly();

    public MonkMod() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modBus);

        MonkLevelManager.initializeDefaultLevels();
        MonkLevelManager.register(1, SPEED);
        MonkLevelManager.register(1, JUMP);
        MonkLevelManager.register(2, HEALTH);
        MonkLevelManager.register(3, ARMOR);
        MonkLevelManager.register(4, ARMOR_TOUGHNESS);
        MonkLevelManager.register(5, STRENGTH);
        MonkLevelManager.register(6, WATER_BREATHING);
        MonkLevelManager.register(8, PROTECTION);
        MonkLevelManager.register(10, FLY);

        MinecraftForge.EVENT_BUS.register(new MonkCommand());
        MinecraftForge.EVENT_BUS.register(MonkManager.class);
        MonkNetwork.register();
    }
}
