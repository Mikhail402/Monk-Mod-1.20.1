package com.rwtema.monkmod;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.rwtema.monkmod.data.MonkData;
import com.rwtema.monkmod.data.MonkDataProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
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

    public static void grantProgress(Player player, int value) {
        MonkData data = get(player);
        data.setLevel(Math.max(1, Math.min(20, data.getLevel())));
        data.setProgress(Math.max(0, data.getProgress() + value));
    }

    public static void setLevel(Player player, int value) {
        MonkData data = get(player);
        data.setLevel(Math.max(1, Math.min(20, value)));
    }

    public static int getLevel(Player player) {
        return get(player).getLevel();
    }
}
