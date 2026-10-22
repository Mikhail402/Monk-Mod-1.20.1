package com.rwtema.monkmod.abilities;

import com.rwtema.monkmod.MonkManager;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MonkAbilityProtection extends MonkAbility {
    public MonkAbilityProtection() {
        super("protection");
    }

    @SubscribeEvent
    public void onDamage(LivingAttackEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!MonkManager.getAbilityLevel(player, this)) return;

        float amount = event.getAmount();
        float reduced = Math.max(0.0F, amount - 2.0F);
        event.setCanceled(false);
        if (reduced < amount) {
            player.hurt(player.damageSources().generic(), reduced);
        }
    }
}
