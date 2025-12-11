package io.github.mildesttoucan.permasnow;

import io.github.mildesttoucan.permasnow.config.PermaSnowConfig;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;

public class PermaSnow implements ModInitializer {
	public static final String MOD_ID = "permasnow";

	public static final PermaSnowConfig CONFIG = PermaSnowConfig.createToml(
			Paths.get("config"),
			"",
			"perma_snow",
			PermaSnowConfig.class
	);


	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Preparing your snowy builds to not melt!");
	}
}