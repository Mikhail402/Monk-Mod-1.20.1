package com.rwtema.monkmod.abilities;

import com.rwtema.monkmod.MonkManager;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MonkAbilityFeatherFalling extends MonkAbility {
    public MonkAbilityFeatherFalling() {
        super("feather_falling");
    }

    @SubscribeEvent
    public void onTick(LivingEvent.LivingTickEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!MonkManager.getAbilityLevel(player, this)) return;

        if (player.fallDistance > 4.0F) {
            player.fallDistance *= 0.4F;
        }
    }
}
