package com.rwtema.monkmod.abilities;

import com.rwtema.monkmod.MonkManager;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MonkAbilityHunger extends MonkAbility {
    public MonkAbilityHunger() {
        super("hunger");
    }

    @SubscribeEvent
    public void onTick(LivingEvent.LivingTickEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!MonkManager.getAbilityLevel(player, this)) return;

        FoodData food = player.getFoodData();
        if (food.getFoodLevel() < 20) {
            food.setFoodLevel(Math.min(20, food.getFoodLevel() + 1));
        }
    }
}
