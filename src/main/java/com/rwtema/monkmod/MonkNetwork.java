package com.rwtema.monkmod.data;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class MonkDataProvider implements ICapabilitySerializable<CompoundTag> {
    public static final Capability<MonkData> MONK_DATA_CAPABILITY =
            CapabilityManager.get(new CapabilityToken<>() {});

    private final MonkData instance = new MonkData();

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
        return capability == MONK_DATA_CAPABILITY ? LazyOptional.of(() -> instance).cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return instance.save();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        instance.load(nbt);
    }
}
