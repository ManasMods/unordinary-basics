package com.github.manasmods.unordinary_basics.item.custom;

import com.github.manasmods.unordinary_basics.item.Unordinary_BasicsItems;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Deprecated(forRemoval = true)
public class LeaperItem extends BowItem {

    private static final int MAX_CHARGE_TIME = 200; // In game ticks, 20 tps

    public LeaperItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int timeUsed) {
        if (livingEntity instanceof Player player) {
            int i = this.getUseDuration(stack) - timeUsed;
            if (i < 10) return;
            float power = getPowerForTime(i);
            if (!level.isClientSide) {
                if (!player.getAbilities().instabuild) {
                    stack.hurtAndBreak((int) Math.floor(power), player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));
                }
            }
            player.getCooldowns().addCooldown(Unordinary_BasicsItems.LEAPER, 80);
            player.setDeltaMovement(player.getDeltaMovement().add(getVector(player.getXRot(), player.getYRot(), power)));
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE, SoundSource.PLAYERS, 1.0F, 0.4F);
        }
    }

    public static Vec3 getVector(float pitch, float yaw, float multiplier) {
        double pitchRadians = Math.toRadians(pitch);
        double yawRadians = Math.toRadians(yaw);
        double yForce = 0 - Math.sin(pitchRadians) * multiplier;
        double diagonalForce = Math.cos(pitchRadians) * multiplier;
        double xForce = 0 - Math.sin(yawRadians) * diagonalForce;
        double zForce = Math.cos(yawRadians) * diagonalForce;
        return new Vec3(xForce, yForce, zForce);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    public static float getPowerForTime(int time) {
        return (float) (Math.min(time / (float) MAX_CHARGE_TIME + 0.2, 1) * 7.5F);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.unordinary_basics.leaper"));
    }

}
