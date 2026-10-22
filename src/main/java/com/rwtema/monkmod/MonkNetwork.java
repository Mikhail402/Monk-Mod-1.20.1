package com.rwtema.monkmod;

import com.rwtema.monkmod.data.MonkData;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Supplier;

public final class MonkNetwork {
    private static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MonkMod.MODID, "network"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private MonkNetwork() {}

    public static void register() {
        int index = 0;
        CHANNEL.registerMessage(index++, SyncMonkDataMessage.class, SyncMonkDataMessage::encode, SyncMonkDataMessage::decode, SyncMonkDataMessage::handle);
    }

    public static void sendToPlayer(ServerPlayer player, SyncMonkDataMessage message) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public record SyncMonkDataMessage(int level, int progress) {
        public static void encode(SyncMonkDataMessage message, FriendlyByteBuf buffer) {
            buffer.writeVarInt(message.level);
            buffer.writeVarInt(message.progress);
        }

        public static SyncMonkDataMessage decode(FriendlyByteBuf buffer) {
            return new SyncMonkDataMessage(buffer.readVarInt(), buffer.readVarInt());
        }

        public static void handle(SyncMonkDataMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
            NetworkEvent.Context context = contextSupplier.get();
            context.enqueueWork(() -> {
                Minecraft mc = Minecraft.getInstance();
                if (mc.player != null) {
                    MonkData data = MonkManager.get(mc.player);
                    data.setLevel(message.level);
                    data.setProgress(message.progress);
                    data.clearDirty();
                }
            });
            context.setPacketHandled(true);
        }
    }
}
