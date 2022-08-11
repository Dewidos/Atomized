package io.github.dewidos.atomized.effect.custom;

import io.github.dewidos.atomized.particle.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class RadiationEffect extends MobEffect {
    public RadiationEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {

        if (pLivingEntity instanceof Player) {

            long time = pLivingEntity.getLevel().getGameTime();

            if (time % 20 == 0) {
                Player p = (Player) pLivingEntity;
                p.hurt(DamageSource.MAGIC, 1f);
                spawnFoundParticles(p);
            }

        }

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    private void spawnFoundParticles(LivingEntity p) {
        double min = -0.3;
        double max = 0.3;
        if (p.getLevel().isClientSide()) {
            for (int i = 0; i < 50; i++) {
                Vec3 position = p.getPosition(0);
                p.getLevel().addParticle(ModParticles.RADIATING_PARTICLES.get(),
                        position.x(), position.y() + 1, position.z(),
                        (Math.random() * ((max - min) + 0.1)) + min, (Math.random() * ((max - min) + 0.1)) + min, (Math.random() * ((max - min) + 0.1)) + min);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
