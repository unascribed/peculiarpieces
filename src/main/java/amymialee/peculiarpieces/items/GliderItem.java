package amymialee.peculiarpieces.items;

import amymialee.peculiarpieces.registry.PeculiarItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class GliderItem extends Item {
    public GliderItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        toggle(stack);
        return TypedActionResult.success(stack);
    }

    private void toggle(ItemStack stack) {
        NbtCompound compound = stack.getOrCreateNbt();
        compound.putBoolean("pp:gliding", !compound.getBoolean("pp:gliding"));
    }

    public static boolean hasGlider(LivingEntity player) {
        ItemStack main = player.getMainHandStack();
        if (main.getNbt() != null && main.getNbt().getBoolean("pp:gliding")) {
            return true;
        }
        ItemStack off = player.getOffHandStack();
        return off.getNbt() != null && off.getNbt().getBoolean("pp:gliding");
    }

    public static ItemStack getGlider(LivingEntity player) {
        ItemStack main = player.getMainHandStack();
        if (isActive(main)) {
            return main;
        }
        ItemStack off = player.getOffHandStack();
        if (isActive(off)) {
            return off;
        }
        return null;
    }

    public static boolean isActiveGlider(ItemStack stack) {
        return stack.getItem() == PeculiarItems.HANG_GLIDER && isActive(stack);
    }

    public static boolean isActive(ItemStack stack) {
        return stack.getNbt() != null && stack.getNbt().getBoolean("pp:gliding");
    }

    public static boolean isGliding(LivingEntity player) {
        if (!player.isOnGround() && !player.isSubmergedInWater() && !player.isSleeping()) {
            Vec3d velocity = player.getVelocity();
            return hasGlider(player) && velocity.getY() < 0;
        }
        return false;
    }
}