package com.rwtema.monkmod.abilities;

import com.rwtema.monkmod.MonkManager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MonkAbilityWaterBreathing extends MonkAbility {
    public MonkAbilityWaterBreathing() {
        super("water_breathing");
    }

    @SubscribeEvent
    public void onTick(LivingEvent.LivingTickEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!MonkManager.getAbilityLevel(player, this)) return;
        if (player.isUnderWater()) {
            player.setAirSupply(player.getMaxAirSupply());
        }
    }
}
