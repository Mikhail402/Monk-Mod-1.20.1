package com.rwtema.monkmod.item;

import com.rwtema.monkmod.MonkManager;
import com.rwtema.monkmod.MonkNetwork;
import com.rwtema.monkmod.data.MonkData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MonkItem extends Item {
    public MonkItem() {
        super(new Item.Properties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (level.isClientSide) {
            return InteractionResultHolder.succeed(stack);
        }

        if (player instanceof ServerPlayer serverPlayer) {
            MonkData data = MonkManager.get(player);
            data.setLevel(data.getLevel() + 1);
            data.setProgress(0);
            MonkNetwork.sendToPlayer(serverPlayer,
                    new MonkNetwork.SyncMonkDataMessage(data.getLevel(), data.getProgress()));
        }

        return InteractionResultHolder.succeed(stack);
    }
}
