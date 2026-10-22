package com.rwtema.monkmod.render;

import com.rwtema.monkmod.MonkManager;
import com.rwtema.monkmod.data.MonkData;
import com.rwtema.monkmod.levels.MonkLevelManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = "monk")
public final class MonkHudOverlay {
    private MonkHudOverlay() {}

    @SubscribeEvent
    public static void renderGui(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) {
            return;
        }

        MonkData data = MonkManager.get(mc.player);
        int level = data.getLevel();
        int progress = data.getProgress();
        int nextLevelThreshold = MonkLevelManager.PROGRESS_PER_LEVEL;
        int remaining = Math.max(0, nextLevelThreshold - progress);

        GuiGraphics graphics = event.getGuiGraphics();
        Font font = mc.font;
        int x = 12;
        int y = 12;

        graphics.drawString(font, "Monk Lv. " + level, x, y, 0xFFFFCC00);

        int barWidth = 120;
        int barHeight = 8;
        int used = (int)((progress / (float) nextLevelThreshold) * barWidth);
        graphics.fill(x, y + 12, x + barWidth, y + 12 + barHeight, 0x66000000);
        graphics.fill(x, y + 12, x + Math.min(used, barWidth), y + 12 + barHeight, 0xFF00D1FF);

        graphics.drawString(font, Component.literal("Next: " + remaining + " / " + nextLevelThreshold).getString(), x, y + 24, 0xFFFFFFFF);
    }
}
