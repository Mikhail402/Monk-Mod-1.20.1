package com.rwtema.monkmod.abilities;

import com.rwtema.monkmod.MonkManager;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MonkAbilityFullWaterBreathing extends MonkAbility {
    public MonkAbilityFullWaterBreathing() {
        super("full_water_breathing");
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
