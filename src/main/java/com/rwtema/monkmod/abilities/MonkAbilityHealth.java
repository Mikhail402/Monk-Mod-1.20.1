package com.rwtema.monkmod.abilities;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class MonkAbilityHealth extends MonkAbilityAttribute {
    public MonkAbilityHealth(double healthBonus) {
        super("health", Attributes.MAX_HEALTH, healthBonus, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public boolean canApply(Player player) {
        return true;
    }
}
