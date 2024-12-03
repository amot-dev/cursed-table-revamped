package dev.amot.cursedtablerevamped;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.GameRules;

import java.util.HashMap;
import java.util.Map;

public class CursedTableRevampedGameRules {
    public static GameRules.Key<GameRules.BooleanRule> ENABLE_ENCHANT_BINDING_CURSE;
    public static GameRules.Key<GameRules.BooleanRule> ENABLE_ENCHANT_FROST_WALKER;
    public static GameRules.Key<GameRules.BooleanRule> ENABLE_ENCHANT_MENDING;
    public static GameRules.Key<GameRules.BooleanRule> ENABLE_ENCHANT_SOUL_SPEED;
    public static GameRules.Key<GameRules.BooleanRule> ENABLE_ENCHANT_SWIFT_SNEAK;
    public static GameRules.Key<GameRules.BooleanRule> ENABLE_ENCHANT_VANISHING_CURSE;
    public static GameRules.Key<GameRules.BooleanRule> ENABLE_ENCHANT_WIND_BURST;

    // Game rules for internal access
    public static Map<RegistryEntry<Enchantment>, Boolean> enabledEnchants = new HashMap<>();

    public static void init() {
        ENABLE_ENCHANT_BINDING_CURSE = GameRuleRegistry.register("enableEnchantBindingCurse", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));
        ENABLE_ENCHANT_FROST_WALKER = GameRuleRegistry.register("enableEnchantFrostWalker", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
        ENABLE_ENCHANT_MENDING = GameRuleRegistry.register("enableEnchantMending", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
        ENABLE_ENCHANT_SOUL_SPEED = GameRuleRegistry.register("enableEnchantSoulSpeed", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
        ENABLE_ENCHANT_SWIFT_SNEAK = GameRuleRegistry.register("enableEnchantSwiftSneak", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
        ENABLE_ENCHANT_VANISHING_CURSE = GameRuleRegistry.register("enableEnchantVanishingCurse", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));
        ENABLE_ENCHANT_WIND_BURST = GameRuleRegistry.register("enableEnchantWindBurst", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));
    }
}
