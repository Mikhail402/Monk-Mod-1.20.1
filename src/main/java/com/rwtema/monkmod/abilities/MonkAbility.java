package com.rwtema.monkmod.abilities;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public abstract class MonkAbility {
    public static final EquipmentSlot[] HELD_SLOTS = { EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND };
    public static final EquipmentSlot[] ARMOR_SLOTS = { EquipmentSlot.FEET, EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS };

    public final String name;

    protected MonkAbility(String name) {
        this.name = name;
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static boolean isUnarmored(Player player) {
        for (EquipmentSlot slot : ARMOR_SLOTS) {
            ItemStack stack = player.getItemBySlot(slot);
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public String getKey() {
        return name;
    }

    public void tickServer(Player player) {
    }

    public boolean canApply(Player player) {
        return true;
    }
}
