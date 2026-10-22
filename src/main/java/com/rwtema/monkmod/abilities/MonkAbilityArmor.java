package com.rwtema.monkmod.abilities;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class MonkAbilityArmor extends MonkAbilityAttribute {
    public MonkAbilityArmor(double armorBonus, double toughnessBonus) {
        super("armor", Attributes.ARMOR, armorBonus, AttributeModifier.Operation.ADDITION);
        this.toughness = toughnessBonus;
    }

    private final double toughness;

    @Override
    public boolean canApply(Player player) {
        return isUnarmored(player);
    }

    @Override
    public double getAmount(Player player) {
        return multiplier;
    }

    public double getToughness(Player player) {
        return toughness;
    }

    private final double multiplier;
}
