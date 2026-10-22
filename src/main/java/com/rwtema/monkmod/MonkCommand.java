package com.rwtema.monkmod;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MonkCommand {
    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {
        var dispatcher = event.getDispatcher();

        dispatcher.register(Commands.literal("monk")
                .then(Commands.literal("level")
                        .then(Commands.argument("player", EntityArgument.player())
                                .then(Commands.argument("value", IntegerArgumentType.integer(1, 20))
                                        .executes(ctx -> setLevel(ctx, EntityArgument.getPlayer(ctx, "player"), IntegerArgumentType.getInteger(ctx, "value"))))))
                .then(Commands.literal("progress")
                        .then(Commands.argument("player", EntityArgument.player())
                                .then(Commands.argument("value", IntegerArgumentType.integer(0, 1000))
                                        .executes(ctx -> addProgress(ctx, EntityArgument.getPlayer(ctx, "player"), IntegerArgumentType.getInteger(ctx, "value"))))))
                .executes(ctx -> {
                    ServerPlayer player = ctx.getSource().getPlayer();
                    if (player == null) {
                        ctx.getSource().sendFailure(Component.literal("This command can only be run by a player."));
                        return 1;
                    }
                    MonkData data = MonkManager.get(player);
                    ctx.getSource().sendSuccess(() -> Component.literal("Monk level: " + data.getLevel() + " | progress: " + data.getProgress()), false);
                    return 1;
                }));
    }

    private static int setLevel(CommandContext<CommandSourceStack> ctx, ServerPlayer player, int level) {
        MonkManager.setLevel(player, level);
        ctx.getSource().sendSuccess(() -> Component.literal("Set monk level for " + player.getScoreboardName() + " to " + level), false);
        return 1;
    }

    private static int addProgress(CommandContext<CommandSourceStack> ctx, ServerPlayer player, int amount) {
        MonkManager.grantProgress(player, amount);
        MonkData data = MonkManager.get(player);
        ctx.getSource().sendSuccess(() -> Component.literal("Added " + amount + " monk progress to " + player.getScoreboardName() + ". Current level: " + data.getLevel() + ", progress: " + data.getProgress()), false);
        return 1;
    }
}
