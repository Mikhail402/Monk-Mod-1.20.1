package com.rwtema.monkmod.abilities;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public abstract class MonkAbilityAttribute extends MonkAbility {
    private final UUID uuid;
    private final Attribute attribute;
    private final double multiplier;
    private final AttributeModifier.Operation operation;

    protected MonkAbilityAttribute(String name, Attribute attribute, double multiplier, AttributeModifier.Operation operation) {
        super(name);
        this.uuid = UUID.nameUUIDFromBytes(name.getBytes());
        this.attribute = attribute;
        this.multiplier = multiplier;
        this.operation = operation;
    }

    @Override
    public void tickServer(Player player) {
        AttributeInstance instance = player.getAttribute(attribute);
        if (instance == null) {
            return;
        }

        AttributeModifier modifier = instance.getModifier(uuid);
        if (canApply(player)) {
            double amount = getAmount(player);
            if (modifier == null || modifier.getAmount() != amount) {
                if (modifier != null) {
                    instance.removeModifier(modifier);
                }
                instance.addPermanentModifier(new AttributeModifier(uuid, name, amount, operation));
            }
        } else if (modifier != null) {
            instance.removeModifier(modifier);
        }
    }

    public double getAmount(Player player) {
        return multiplier;
    }
}
