package com.ds.projectdawn.objects.particles;

import com.ds.projectdawn.init.ParticleInit;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Locale;

@OnlyIn(Dist.CLIENT)
public class MagicGlintParticle extends SimpleAnimatedParticle {
    private MagicGlintParticle(ClientWorld world, double x, double y, double z, double xSpeedIn, double ySpeedIn, double zSpeedIn, float red, float green, float blue, IAnimatedSprite spriteWithAge) {
        super(world, x, y, z, spriteWithAge, 0.0F);
        this.xd = xSpeedIn;
        this.yd = ySpeedIn;
        this.zd = zSpeedIn;
        this.quadSize *= 0.75F;
        this.rCol =  red * 255.0F;
        this.gCol = green * 255.0F;
        this.bCol = blue * 255.0F;
        this.alpha = 0.99F;
        this.lifetime = 24 + this.random.nextInt(12);
    }

    @Override
    public IParticleRenderType getRenderType() { return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT; }

    @OnlyIn(Dist.CLIENT)
    public static class ArcaneGlintFactory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteWithAge;

        public ArcaneGlintFactory(IAnimatedSprite spriteIn) {
            this.spriteWithAge = spriteIn;
        }

        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            MagicGlintParticle magicparticle = new MagicGlintParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, 97, 252, 10, this.spriteWithAge);
            magicparticle.setSpriteFromAge(this.spriteWithAge);
            return magicparticle;
        }

    }
    @OnlyIn(Dist.CLIENT)
    public static class AmethystGlintFactory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteWithAge;

        public AmethystGlintFactory(IAnimatedSprite spriteIn) {
            this.spriteWithAge = spriteIn;
        }

        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            MagicGlintParticle magicparticle = new MagicGlintParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, 179, 142, 243, this.spriteWithAge);
            magicparticle.setSpriteFromAge(this.spriteWithAge);
            return magicparticle;
        }

    }
    @OnlyIn(Dist.CLIENT)
    public static class EmeraldGlintFactory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteWithAge;

        public EmeraldGlintFactory(IAnimatedSprite spriteIn) {
            this.spriteWithAge = spriteIn;
        }

        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            MagicGlintParticle magicparticle = new MagicGlintParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, 23, 221, 98, this.spriteWithAge);
            magicparticle.setSpriteFromAge(this.spriteWithAge);
            return magicparticle;
        }

    }
    @OnlyIn(Dist.CLIENT)
    public static class RubyGlintFactory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteWithAge;

        public RubyGlintFactory(IAnimatedSprite spriteIn) {
            this.spriteWithAge = spriteIn;
        }

        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            MagicGlintParticle magicparticle = new MagicGlintParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, 227, 38, 52, this.spriteWithAge);
            magicparticle.setSpriteFromAge(this.spriteWithAge);
            return magicparticle;
        }

    }
    @OnlyIn(Dist.CLIENT)
    public static class DiamondGlintFactory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteWithAge;

        public DiamondGlintFactory(IAnimatedSprite spriteIn) {
            this.spriteWithAge = spriteIn;
        }

        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            MagicGlintParticle magicparticle = new MagicGlintParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, 74, 237, 217, this.spriteWithAge);
            magicparticle.setSpriteFromAge(this.spriteWithAge);
            return magicparticle;
        }

    }
    @OnlyIn(Dist.CLIENT)
    public static class PurPurGlintFactory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteWithAge;

        public PurPurGlintFactory(IAnimatedSprite spriteIn) {
            this.spriteWithAge = spriteIn;
        }

        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            MagicGlintParticle magicparticle = new MagicGlintParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, 255, 0, 255, this.spriteWithAge);
            magicparticle.setSpriteFromAge(this.spriteWithAge);
            return magicparticle;
        }

    }
    @OnlyIn(Dist.CLIENT)
    public static class HasColorFactory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteWithAge;

        public HasColorFactory(IAnimatedSprite spriteIn) {
            this.spriteWithAge = spriteIn;
        }

        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            MagicGlintParticle magicparticle = new MagicGlintParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, 255, 255, 255, this.spriteWithAge);
            magicparticle.setSpriteFromAge(this.spriteWithAge);
            return magicparticle;
        }

    }

}
