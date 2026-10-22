package com.rwtema.monkmod.abilities;

import com.rwtema.monkmod.MonkManager;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MonkAbilityWitheringStare extends MonkAbility {
    public MonkAbilityWitheringStare() {
        super("withering_stare");
    }

    @SubscribeEvent
    public void onTick(LivingEvent.LivingTickEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!MonkManager.getAbilityLevel(player, this)) return;

        AABB box = new AABB(player.blockPosition()).inflate(6.0D);
        for (LivingEntity target : player.level().getEntitiesOfClass(LivingEntity.class, box, e -> e != player && !(e instanceof Player))) {
            if (player.hasLineOfSight(target)) {
                target.addEffect(new MobEffectInstance(MobEffects.WITHER, 40, 0, false, false));
            }
        }
    }
}
