package com.rwtema.monkmod.abilities;

import com.rwtema.monkmod.MonkManager;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class MonkAbilitySpeed extends MonkAbilityAttribute {
    public MonkAbilitySpeed(double bonus) {
        super("speed", Attributes.MOVEMENT_SPEED, bonus, AttributeModifier.Operation.MULTIPLY_BASE);
    }

    @Override
    public boolean canApply(Player player) {
        return isUnarmored(player);
    }

    @Override
    public double getAmount(Player player) {
        double base = super.getAmount(player);
        return player.isSprinting() ? base * 2.0D : base;
    }
}
