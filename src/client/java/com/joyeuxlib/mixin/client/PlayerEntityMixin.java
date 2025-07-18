package com.joyeuxlib.mixin.client;

import com.joyeuxlib.core.util.JoyeuxLibCustomHitSoundItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Shadow public abstract float getAttackCooldownProgress(float baseTime);


    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getAttackCooldownProgress(F)F"))
    private void ratatouille$spawnCustomHitParticlesAndPlayCustomHitSound(Entity target, CallbackInfo ci) {
        if (this.getAttackCooldownProgress(0.5F) > 0.90F) {

            if (this.getMainHandStack().getItem() instanceof JoyeuxLibCustomHitSoundItem customHitSoundItem) {
                customHitSoundItem.playHitSound((PlayerEntity) (Object) this);
            }
        }
    }

}
