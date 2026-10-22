package com.rwtema.monkmod.abilities;

import com.rwtema.monkmod.MonkManager;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MonkAbilityMining extends MonkAbility {
    public MonkAbilityMining() {
        super("mining");
    }

    @SubscribeEvent
    public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        if (!MonkManager.getAbilityLevel(player, this)) return;
        event.setNewSpeed(event.getNewSpeed() * 1.5F);
    }
}
