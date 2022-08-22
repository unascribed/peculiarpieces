package amymialee.peculiarpieces.mixin;

import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntityMixin {
    @Shadow public abstract boolean isLeashed();

    @Inject(method = "canBeLeashedBy", at = @At("HEAD"), cancellable = true)
    public void PeculiarPieces$CreativeLeashes(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (!this.isLeashed()) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    public void PeculiarPieces$InteractMobHead(CallbackInfoReturnable<ActionResult> cir) {}
}