package com.rwtema.monkmod.levels;

import com.rwtema.monkmod.MonkMod;
import com.rwtema.monkmod.abilities.MonkAbility;

import java.util.*;

public final class MonkLevelManager {
    private static final Map<Integer, Set<MonkAbility>> ABILITIES = new HashMap<>();

    private MonkLevelManager() {}

    public static void register(int level, MonkAbility ability) {
        ABILITIES.computeIfAbsent(level, key -> new HashSet<>()).add(ability);
    }

    public static Set<MonkAbility> getAbilities(int level) {
        int clamped = Math.max(0, Math.min(level, MonkMod.MAX_LEVEL));
        return ABILITIES.getOrDefault(clamped, Collections.emptySet());
    }
}
