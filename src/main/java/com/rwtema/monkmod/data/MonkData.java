package com.rwtema.monkmod.data;

import net.minecraft.nbt.CompoundTag;

public class MonkData {
    private int level;
    private int progress;
    private boolean dirty;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = Math.max(0, level);
        this.dirty = true;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = Math.max(0, progress);
        this.dirty = true;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void clearDirty() {
        this.dirty = false;
    }

    public void copyFrom(MonkData other) {
        this.level = other.level;
        this.progress = other.progress;
        this.dirty = true;
    }

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("level", level);
        tag.putInt("progress", progress);
        return tag;
    }

    public void load(CompoundTag tag) {
        this.level = tag.getInt("level");
        this.progress = tag.getInt("progress");
        this.dirty = true;
    }
}
