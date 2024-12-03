package dev.amot.cursedtablerevamped.mixin;

import dev.amot.cursedtablerevamped.CursedTableRevampedGameRules;
import net.minecraft.block.BlockState;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantingTableBlock.class)
public abstract class EnchantingTableBlockMixin {

    @Inject(method = "onUse", at = @At(value = "RETURN"))
    private void updateFromInternalGameRules(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        if (world instanceof ServerWorld serverWorld) {
            Registry<Enchantment> enchantmentRegistry = world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);
            GameRules gameRules = serverWorld.getGameRules();
            // Update/set internal variables based on game rules
            CursedTableRevampedGameRules.enabledEnchants.put(
                    enchantmentRegistry.getEntry(enchantmentRegistry.get(Enchantments.BINDING_CURSE)),
                    gameRules.getBoolean(CursedTableRevampedGameRules.ENABLE_ENCHANT_BINDING_CURSE))
            ;
            CursedTableRevampedGameRules.enabledEnchants.put(
                    enchantmentRegistry.getEntry(enchantmentRegistry.get(Enchantments.FROST_WALKER)),
                    gameRules.getBoolean(CursedTableRevampedGameRules.ENABLE_ENCHANT_FROST_WALKER)
            );
            CursedTableRevampedGameRules.enabledEnchants.put(
                    enchantmentRegistry.getEntry(enchantmentRegistry.get(Enchantments.MENDING)),
                    gameRules.getBoolean(CursedTableRevampedGameRules.ENABLE_ENCHANT_MENDING)
            );
            CursedTableRevampedGameRules.enabledEnchants.put(
                    enchantmentRegistry.getEntry(enchantmentRegistry.get(Enchantments.SOUL_SPEED)),
                    gameRules.getBoolean(CursedTableRevampedGameRules.ENABLE_ENCHANT_SOUL_SPEED)
            );
            CursedTableRevampedGameRules.enabledEnchants.put(
                    enchantmentRegistry.getEntry(enchantmentRegistry.get(Enchantments.SWIFT_SNEAK)),
                    gameRules.getBoolean(CursedTableRevampedGameRules.ENABLE_ENCHANT_SWIFT_SNEAK)
            );
            CursedTableRevampedGameRules.enabledEnchants.put(
                    enchantmentRegistry.getEntry(enchantmentRegistry.get(Enchantments.VANISHING_CURSE)),
                    gameRules.getBoolean(CursedTableRevampedGameRules.ENABLE_ENCHANT_VANISHING_CURSE)
            );
            CursedTableRevampedGameRules.enabledEnchants.put(
                    enchantmentRegistry.getEntry(enchantmentRegistry.get(Enchantments.WIND_BURST)),
                    gameRules.getBoolean(CursedTableRevampedGameRules.ENABLE_ENCHANT_WIND_BURST)
            );
        }
    }
}