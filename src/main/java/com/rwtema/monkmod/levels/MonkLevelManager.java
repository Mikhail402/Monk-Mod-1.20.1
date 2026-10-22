package com.rwtema.monkmod.levels;

import com.rwtema.monkmod.MonkMod;
import com.rwtema.monkmod.abilities.MonkAbility;
import com.rwtema.monkmod.abilities.MonkAbilityArmor;
import com.rwtema.monkmod.abilities.MonkAbilityArmorToughness;
import com.rwtema.monkmod.abilities.MonkAbilityBlindness;
import com.rwtema.monkmod.abilities.MonkAbilityBlink;
import com.rwtema.monkmod.abilities.MonkAbilityCatchArrows;
import com.rwtema.monkmod.abilities.MonkAbilityCreeperKiss;
import com.rwtema.monkmod.abilities.MonkAbilityExplosionProof;
import com.rwtema.monkmod.abilities.MonkAbilityFeatherFalling;
import com.rwtema.monkmod.abilities.MonkAbilityFireResistance;
import com.rwtema.monkmod.abilities.MonkAbilityFly;
import com.rwtema.monkmod.abilities.MonkAbilityFullWaterBreathing;
import com.rwtema.monkmod.abilities.MonkAbilityGlassTouch;
import com.rwtema.monkmod.abilities.MonkAbilityHealth;
import com.rwtema.monkmod.abilities.MonkAbilityHunger;
import com.rwtema.monkmod.abilities.MonkAbilityJump;
import com.rwtema.monkmod.abilities.MonkAbilityProtection;
import com.rwtema.monkmod.abilities.MonkAbilitySpeed;
import com.rwtema.monkmod.abilities.MonkAbilityStepUp;
import com.rwtema.monkmod.abilities.MonkAbilityStrength;
import com.rwtema.monkmod.abilities.MonkAbilityTameAnimals;
import com.rwtema.monkmod.abilities.MonkAbilityWalkOnWater;
import com.rwtema.monkmod.abilities.MonkAbilityWaterBreathing;
import com.rwtema.monkmod.abilities.MonkAbilityWitheringStare;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class MonkLevelManager {
    public static final int PROGRESS_PER_LEVEL = 20;
    private static final Map<Integer, Set<MonkAbility>> ABILITIES = new HashMap<>();

    private MonkLevelManager() {}

    public static void register(int level, MonkAbility ability) {
        ABILITIES.computeIfAbsent(level, key -> new HashSet<>()).add(ability);
    }

    public static Set<MonkAbility> getAbilities(int level) {
        int clamped = Math.max(0, Math.min(level, MonkMod.MAX_LEVEL));
        return ABILITIES.getOrDefault(clamped, Collections.emptySet());
    }

    public static void initializeDefaultLevels() {
        register(1, new MonkAbilitySpeed(0.12D));
        register(1, new MonkAbilityJump(1.9D));
        register(2, new MonkAbilityHealth(2.0D));
        register(3, new MonkAbilityArmor(1.0D, 0.5D));
        register(4, new MonkAbilityArmorToughness(1.0D));
        register(5, new MonkAbilityStrength());
        register(6, new MonkAbilityWaterBreathing());
        register(7, new MonkAbilityFullWaterBreathing());
        register(8, new MonkAbilityProtection());
        register(9, new MonkAbilityFireResistance());
        register(10, new MonkAbilityFly());
        register(11, new MonkAbilityFeatherFalling());
        register(12, new MonkAbilityBlink());
        register(13, new MonkAbilityWalkOnWater());
        register(14, new MonkAbilityStepUp());
        register(15, new MonkAbilityGlassTouch());
        register(16, new MonkAbilityHunger());
        register(17, new MonkAbilityTameAnimals());
        register(18, new MonkAbilityCatchArrows());
        register(19, new MonkAbilityCreeperKiss());
        register(20, new MonkAbilityBlindness());
        register(20, new MonkAbilityWitheringStare());
        register(20, new MonkAbilityExplosionProof());
    }

    public static int getProgressForNextLevel(int currentLevel) {
        return PROGRESS_PER_LEVEL * Math.max(1, currentLevel);
    }

    public static int getProgressToNextLevel(int currentLevel, int progress) {
        return Math.max(0, getProgressForNextLevel(currentLevel) - progress);
    }

    public static void addProgressForPlayer(com.rwtema.monkmod.data.MonkData data, int amount) {
        int newProgress = data.getProgress() + amount;
        int level = data.getLevel();

        while (newProgress >= PROGRESS_PER_LEVEL && level < MonkMod.MAX_LEVEL) {
            newProgress -= PROGRESS_PER_LEVEL;
            level++;
        }

        data.setLevel(level);
        data.setProgress(newProgress);
    }
}
