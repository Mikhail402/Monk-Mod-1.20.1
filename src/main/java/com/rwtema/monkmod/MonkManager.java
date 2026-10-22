package com.rwtema.monkmod;

import com.rwtema.monkmod.data.MonkData;
import com.rwtema.monkmod.data.MonkDataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class MonkManager {
    private MonkManager() {}

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(new ResourceLocation(MonkMod.MODID, "monk_data"), new MonkDataProvider());
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        MonkData original = get(event.getOriginal());
        MonkData clone = get(event.getEntity());
        clone.copyFrom(original);
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            MonkNetwork.sendToPlayer(serverPlayer, new MonkNetwork.SyncMonkDataMessage(clone.getLevel(), clone.getProgress()));
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        MonkData data = get(player);
        if (player instanceof ServerPlayer serverPlayer) {
            MonkNetwork.sendToPlayer(serverPlayer, new MonkNetwork.SyncMonkDataMessage(data.getLevel(), data.getProgress()));
        }
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity().level().isClientSide) {
            return;
        }

        if (event.getEntity() instanceof Player player) {
            MonkData data = get(player);
            if (data.isDirty()) {
                if (player instanceof ServerPlayer serverPlayer) {
                    MonkNetwork.sendToPlayer(serverPlayer, new MonkNetwork.SyncMonkDataMessage(data.getLevel(), data.getProgress()));
                }
                data.clearDirty();
            }
        }
    }

    public static MonkData get(Player player) {
        LazyOptional<MonkData> capability = player.getCapability(MonkDataProvider.MONK_DATA_CAPABILITY);
        return capability.orElseGet(MonkData::new);
    }
}
