package dev.amot.cursedtablerevamped.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.featuretoggle.FeatureSet;
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
    private static void addGameruleEnchants(FeatureSet enabledFeatures, int level, ItemStack stack, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
        List<EnchantmentLevelEntry> list = cir.getReturnValue();
        // Go through all possible enchantments to add
        for (Map.Entry<Enchantment,Boolean> enabledEnchant : enabledEnchants.entrySet()) {
            // If gamerule is enabled (or rather, the internal variables set from updateFromInternalGameRules)
            if (enabledEnchant.getValue()) {
                // Add enchantment (and all its levels) to possible entries
                Enchantment enchantment = enabledEnchant.getKey();
                for(int i = enchantment.getMaxLevel(); i > enchantment.getMinLevel() - 1; --i) {
                    if (level >= enchantment.getMinPower(i) && level <= enchantment.getMaxPower(i)) {
                        list.add(new EnchantmentLevelEntry(enchantment, i));
                        break;
                    }
                }
            }
        }
        cir.setReturnValue(list);
    }
}