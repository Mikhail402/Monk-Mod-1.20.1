package com.rwtema.monkmod.abilities;

import com.rwtema.monkmod.MonkManager;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MonkAbilityWalkOnWater extends MonkAbility {
    public MonkAbilityWalkOnWater() {
        super("walk_on_water");
    }

    @SubscribeEvent
    public void onTick(LivingEvent.LivingTickEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!MonkManager.getAbilityLevel(player, this)) return;
        if (player.isInWater() && !player.isSwimming()) {
            player.setDeltaMovement(player.getDeltaMovement().x * 1.05D, Math.max(player.getDeltaMovement().y, 0.08D), player.getDeltaMovement().z * 1.05D);
        }
    }
}
