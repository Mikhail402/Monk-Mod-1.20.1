package com.rwtema.monkmod.abilities;

import com.rwtema.monkmod.MonkManager;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MonkAbilityStrength extends MonkAbility {
    public MonkAbilityStrength() {
        super("strength");
    }

    @SubscribeEvent
    public void onTick(LivingEvent.LivingTickEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!MonkManager.getAbilityLevel(player, this)) return;
        if (!player.isSprinting()) return;
        player.addEffect(new net.minecraft.world.effect.MobEffectInstance(MobEffects.DAMAGE_BOOST, 20, 0, false, false));
    }
}
