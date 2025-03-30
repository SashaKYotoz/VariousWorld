
package net.sashakyotoz.variousworld.client.particle;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.multiplayer.ClientLevel;

@OnlyIn(Dist.CLIENT)
public class WanderingSpiritAbilityShootParticleParticle extends TextureSheetParticle {
	public static WanderingSpiritAbilityShootParticleParticleProvider provider(SpriteSet spriteSet) {
		return new WanderingSpiritAbilityShootParticleParticleProvider(spriteSet);
	}

	public static class WanderingSpiritAbilityShootParticleParticleProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public WanderingSpiritAbilityShootParticleParticleProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new WanderingSpiritAbilityShootParticleParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}

	private final SpriteSet spriteSet;

	protected WanderingSpiritAbilityShootParticleParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
		super(world, x, y, z);
		this.spriteSet = spriteSet;
		this.setSize(0.3f, 0.3f);
		this.quadSize *= 2f;
		this.lifetime = Math.max(1, 40 + (this.random.nextInt(30) - 10));
		this.xd = vx;
		this.yd = vy;
		this.zd = vz;
		this.setSpriteFromAge(spriteSet);
	}

	@Override
	public int getLightColor(float partialTick) {
		return 15728880;
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_LIT;
	}

	@Override
	public void tick() {
		super.tick();
		fadeOut();
		if (!this.removed)
			this.setSprite(this.spriteSet.get((this.age / 4) % 16 + 1, 16));
	}
	private void fadeOut() {
		this.alpha = (-(1 / (float) lifetime) * age + 1);
		this.bCol = (-(1 / (float) lifetime) * age + 1);
	}
}
