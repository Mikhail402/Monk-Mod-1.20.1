package com.rwtema.monkmod.abilities;

import com.rwtema.monkmod.MonkManager;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MonkAbilityFly extends MonkAbility {
    public MonkAbilityFly() {
        super("fly");
    }

    @SubscribeEvent
    public void onTick(LivingEvent.LivingTickEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!MonkManager.getAbilityLevel(player, this)) return;

        player.getAbilities().mayfly = true;
        player.getAbilities().flying = player.getAbilities().flying && player.isSprinting();
        player.onUpdateAbilities();
    }
}
