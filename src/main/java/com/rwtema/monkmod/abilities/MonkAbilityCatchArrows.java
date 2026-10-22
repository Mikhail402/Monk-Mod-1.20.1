package com.rwtema.monkmod.abilities;

import com.rwtema.monkmod.MonkManager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MonkAbilityCatchArrows extends MonkAbility {
    public MonkAbilityCatchArrows() {
        super("catch_arrows");
    }

    @SubscribeEvent
    public void onAttack(LivingAttackEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!MonkManager.getAbilityLevel(player, this)) return;

        if (event.getSource().getDirectEntity() instanceof AbstractArrow arrow) {
            arrow.setDeltaMovement(0, 0, 0);
            arrow.setPos(player.getX(), player.getY(), player.getZ());
            arrow.discard();
            event.setCanceled(true);
        }
    }
}
