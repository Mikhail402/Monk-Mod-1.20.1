package com.rwtema.monkmod.abilities;

import com.rwtema.monkmod.MonkManager;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MonkAbilityBlindness extends MonkAbility {
    public MonkAbilityBlindness() {
        super("blindness");
    }

    @SubscribeEvent
    public void onTick(LivingEvent.LivingTickEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!MonkManager.getAbilityLevel(player, this)) return;

        if (player.isUnderWater() || player.isInPowderSnow) {
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 20, 0, false, false));
        }
    }
}
