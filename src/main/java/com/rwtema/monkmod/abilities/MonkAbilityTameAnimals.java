package com.rwtema.monkmod.abilities;

import com.rwtema.monkmod.MonkManager;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MonkAbilityTameAnimals extends MonkAbility {
    public MonkAbilityTameAnimals() {
        super("tame_animals");
    }

    @SubscribeEvent
    public void onInteract(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getTarget() instanceof TamableAnimal animal)) return;
        Player player = event.getEntity();
        if (!MonkManager.getAbilityLevel(player, this)) return;
        if (!animal.isTame()) {
            animal.tame(player);
        }
    }
}
