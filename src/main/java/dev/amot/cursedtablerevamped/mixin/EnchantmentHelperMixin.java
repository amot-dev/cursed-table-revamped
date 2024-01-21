package dev.amot.cursedtablerevamped.mixin;

import dev.amot.cursedtablerevamped.CursedTableRevamped;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;

import static dev.amot.cursedtablerevamped.CursedTableRevampedGameRules.enabledEnchants;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
    @Inject(method = "getPossibleEntries", at = @At(value = "RETURN"), cancellable = true)
    private static void addGameruleEnchants(int power, ItemStack stack, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
        List<EnchantmentLevelEntry> list = cir.getReturnValue();
        // Go through all possible enchantments to add
        for (Map.Entry<Enchantment,Boolean> enabledEnchant : enabledEnchants.entrySet()) {
            // If gamerule is enabled (or rather, the internal variables set from updateFromInternalGameRules)
            if (enabledEnchant.getValue()) {
                // Add enchantment (and all its levels) to possible entries
                Enchantment enchantment = enabledEnchant.getKey();
                for (int i = enchantment.getMaxLevel(); i > enchantment.getMinLevel() - 1; --i) {
                    if (power < enchantment.getMinPower(i) || power > enchantment.getMaxPower(i)) continue;
                    list.add(new EnchantmentLevelEntry(enchantment, i));
                }
            }
        }
        // TODO debug wtf is going on with missing enchants
        for (EnchantmentLevelEntry enchantmentLevelEntry : list) {
            CursedTableRevamped.LOGGER.info(enchantmentLevelEntry.enchantment.toString() + " " + enchantmentLevelEntry.level);
        }
        cir.setReturnValue(list);
    }
}