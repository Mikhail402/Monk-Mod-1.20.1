package com.rwtema.monkmod.abilities;

import com.rwtema.monkmod.MonkManager;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MonkAbilityCreeperKiss extends MonkAbility {
    public MonkAbilityCreeperKiss() {
        super("creeper_kiss");
    }

    @SubscribeEvent
    public void onAttack(LivingAttackEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!MonkManager.getAbilityLevel(player, this)) return;

        if (event.getSource().getEntity() instanceof Creeper creeper) {
            creeper.kill();
            event.setCanceled(true);
        }
    }
}
