package dev.amot.cursedtablerevamped.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static dev.amot.cursedtablerevamped.CursedTableRevampedGameRules.enabledEnchants;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
    @Inject(method = "getPossibleEntries", at = @At(value = "RETURN"), cancellable = true)
    private static void addGameruleEnchants(int level, ItemStack stack, Stream<RegistryEntry<Enchantment>> possibleEnchantments, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {
        List<EnchantmentLevelEntry> list = cir.getReturnValue();
        // Go through all possible enchantments to add
        for (Map.Entry<RegistryEntry<Enchantment>, Boolean> enabledEnchant : enabledEnchants.entrySet()) {
            // Skip if not enabled (set from gamerule in updateFromInternalGameRules in EnchantingTableBlockMixin, on table interaction)
            if (!enabledEnchant.getValue()) continue;
            Enchantment enchantment = enabledEnchant.getKey().value();

            // Skip if enchantment not available for current item
            if (!(enchantment.isPrimaryItem(stack) || stack.isOf(Items.BOOK))) continue;

            // Add enchantment (and all its levels) to possible entries
            for(int i = enchantment.getMaxLevel(); i > enchantment.getMinLevel() - 1; --i) {
                if (level >= enchantment.getMinPower(i) && level <= enchantment.getMaxPower(i)) {
                    list.add(new EnchantmentLevelEntry(enabledEnchant.getKey(), i));
                    break;
                }
            }
        }
        cir.setReturnValue(list);
    }
}