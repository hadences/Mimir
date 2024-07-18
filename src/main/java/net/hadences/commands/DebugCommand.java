package net.hadences.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.hadences.ik.IKEntity;
import net.hadences.ik.IKPart;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

public class DebugCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("debug_mimir")
                .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
                .executes(context -> {
                    ServerCommandSource source = context.getSource();
                    ServerPlayerEntity player = source.getPlayer();

                    assert player != null;
                    MinecraftServer server = player.getServer();

                    IKEntity entity = new IKEntity(0.1, player.getServerWorld(), player.getPos());
                    entity.addPart(new IKPart(1));
                    entity.addPart(new IKPart(1));
                    entity.addPart(new IKPart(1));


                    return 1;
                }));
    }
}
