package dev.amot.cursedtablerevamped.mixin;

import dev.amot.cursedtablerevamped.CursedTableRevamped;
import dev.amot.cursedtablerevamped.CursedTableRevampedGameRules;
import net.minecraft.block.BlockState;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantingTableBlock.class)
public abstract class EnchantingTableBlockMixin {

    @Inject(method = "onUse", at = @At(value = "RETURN"))
    private void updateFromInternalGameRules(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        if (world instanceof ServerWorld serverWorld) {
            // Updating internal variables based on game rules
            CursedTableRevampedGameRules.enabledEnchants.put(Enchantments.BINDING_CURSE, serverWorld.getGameRules().getBoolean(CursedTableRevampedGameRules.ENABLE_ENCHANT_BINDING_CURSE));
            CursedTableRevampedGameRules.enabledEnchants.put(Enchantments.FROST_WALKER, serverWorld.getGameRules().getBoolean(CursedTableRevampedGameRules.ENABLE_ENCHANT_FROST_WALKER));
            CursedTableRevampedGameRules.enabledEnchants.put(Enchantments.MENDING, serverWorld.getGameRules().getBoolean(CursedTableRevampedGameRules.ENABLE_ENCHANT_MENDING));
            CursedTableRevampedGameRules.enabledEnchants.put(Enchantments.SOUL_SPEED, serverWorld.getGameRules().getBoolean(CursedTableRevampedGameRules.ENABLE_ENCHANT_SOUL_SPEED));
            CursedTableRevampedGameRules.enabledEnchants.put(Enchantments.SWIFT_SNEAK, serverWorld.getGameRules().getBoolean(CursedTableRevampedGameRules.ENABLE_ENCHANT_SWIFT_SNEAK));
            CursedTableRevampedGameRules.enabledEnchants.put(Enchantments.VANISHING_CURSE, serverWorld.getGameRules().getBoolean(CursedTableRevampedGameRules.ENABLE_ENCHANT_VANISHING_CURSE));
        }
    }

}