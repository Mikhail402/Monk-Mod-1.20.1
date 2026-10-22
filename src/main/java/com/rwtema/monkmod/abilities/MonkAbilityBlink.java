package com.rwtema.monkmod.abilities;

import com.rwtema.monkmod.MonkManager;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MonkAbilityBlink extends MonkAbility {
    private static final double BLINK_DISTANCE = 6.0D;

    public MonkAbilityBlink() {
        super("blink");
    }

    @SubscribeEvent
    public void onTick(LivingEvent.LivingTickEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!MonkManager.getAbilityLevel(player, this)) return;
        if (!(player instanceof ServerPlayer serverPlayer)) return;
        if (!player.isShiftKeyDown()) return;

        Vec3 look = player.getLookAngle();
        double x = player.getX() + look.x * BLINK_DISTANCE;
        double y = player.getY() + 0.25D;
        double z = player.getZ() + look.z * BLINK_DISTANCE;

        serverPlayer.teleportTo(x, y, z);
        player.setDeltaMovement(0, 0, 0);
    }
}
