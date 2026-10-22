package com.rwtema.monkmod.abilities;

import com.rwtema.monkmod.MonkManager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MonkAbilityGlassTouch extends MonkAbility {
    public MonkAbilityGlassTouch() {
        super("glass_touch");
    }

    @SubscribeEvent
    public void breakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        if (!MonkManager.getAbilityLevel(player, this)) return;

        BlockState state = player.level().getBlockState(event.getPos());
        if (state.is(Blocks.GLASS) || state.is(Blocks.TINTED_GLASS)) {
            event.setNewSpeed(event.getNewSpeed() * 2.0F);
        }
    }
}
