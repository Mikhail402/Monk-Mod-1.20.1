package com.rwtema.monkmod.abilities;

import com.rwtema.monkmod.MonkManager;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MonkAbilityJump extends MonkAbility {
    private final double jumpFactor;

    public MonkAbilityJump(double jumpFactor) {
        super("jump");
        this.jumpFactor = jumpFactor;
    }

    @SubscribeEvent
    public void onJump(LivingEvent.LivingJumpEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }
        if (!MonkManager.getAbilityLevel(player, this)) {
            return;
        }

        var motion = player.getDeltaMovement();
        player.setDeltaMovement(motion.x(), motion.y() * jumpFactor, motion.z());
    }

    @SubscribeEvent
    public void onFall(LivingFallEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }
        if (!MonkManager.getAbilityLevel(player, this)) {
            return;
        }

        event.setDistance((float) Math.max(0.0D, event.getDistance() - 3.0D));
    }
}
