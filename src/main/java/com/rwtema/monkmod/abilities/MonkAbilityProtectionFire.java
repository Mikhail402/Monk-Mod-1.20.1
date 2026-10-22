package com.rwtema.monkmod.abilities;

import com.rwtema.monkmod.MonkManager;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MonkAbilityProtectionFire extends MonkAbility {
    public MonkAbilityProtectionFire() {
        super("protection_fire");
    }

    @SubscribeEvent
    public void onFall(LivingFallEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!MonkManager.getAbilityLevel(player, this)) return;
        event.setDistance((float) Math.max(0.0D, event.getDistance() - 2.0D));
    }
}
