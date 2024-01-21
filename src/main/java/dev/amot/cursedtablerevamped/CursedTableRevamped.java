package dev.amot.cursedtablerevamped;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CursedTableRevamped implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Cursed Table Revamped");

    @Override
    public void onInitialize() {
        CursedTableRevampedGameRules.init();
        LOGGER.info("Mod loaded!");
    }
}