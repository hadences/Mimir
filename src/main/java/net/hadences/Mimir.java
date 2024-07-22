package net.hadences;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.hadences.commands.DebugCommand;
import net.hadences.entity.IKEntity;
import net.hadences.entity.MimirModEntities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mimir implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("mimir");
	public static final String MOD_ID = "mimir";

	@Override
	public void onInitialize() {
		//register command
		CommandRegistrationCallback.EVENT.register(DebugCommand::register);

		FabricDefaultAttributeRegistry.register(MimirModEntities.TEST_ENTITY, IKEntity.createIKAttributes());
	}
}
