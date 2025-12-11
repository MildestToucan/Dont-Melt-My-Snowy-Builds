package io.github.mildesttoucan.nomeltsnowybuilds;

import io.github.mildesttoucan.nomeltsnowybuilds.config.DMMSBConfig;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;

public class DontMeltMySnowyBuilds implements ModInitializer {
	public static final String MOD_ID = "nomeltsnowybuilds";

	public static final DMMSBConfig CONFIG = DMMSBConfig.createToml(
			Paths.get("config"),
			"",
			"nomeltsnowybuilds",
			DMMSBConfig.class
	);


	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Applying magical anti-melt to snow and maybe ice!");
	}
}