package net.hadences;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.hadences.commands.DebugCommand;
import net.hadences.ik.IKEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mimir implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("mimir");
	public static final String MOD_ID = "mimir";

	public static final IKEntityManager ikEntityManager = new IKEntityManager();

	@Override
	public void onInitialize() {
		ServerTickEvents.END_SERVER_TICK.register((server) -> {
			ikEntityManager.tickAll();
		});

		//register command
		CommandRegistrationCallback.EVENT.register(DebugCommand::register);

	}
}
