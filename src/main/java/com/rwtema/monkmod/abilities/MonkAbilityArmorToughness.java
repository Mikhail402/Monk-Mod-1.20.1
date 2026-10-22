package com.rwtema.monkmod.abilities;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class MonkAbilityArmorToughness extends MonkAbilityAttribute {
    public MonkAbilityArmorToughness(double toughnessBonus) {
        super("armor_toughness", Attributes.ARMOR_TOUGHNESS, toughnessBonus, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public boolean canApply(Player player) {
        return isUnarmored(player);
    }
}
