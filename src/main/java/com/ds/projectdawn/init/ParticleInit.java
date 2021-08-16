package com.ds.projectdawn.init;

import com.ds.projectdawn.ProjectDawn;
import com.ds.projectdawn.objects.particles.MagicGlintParticle;
import com.mojang.serialization.Codec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.CampfireParticle;
import net.minecraft.client.particle.SuspendedTownParticle;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(modid = ProjectDawn.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticleInit {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ProjectDawn.MOD_ID);

    public static final RegistryObject<BasicParticleType> ARCANE_GLINT = PARTICLES.register("arcane_glint", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> AMETHYST_GLINT = PARTICLES.register("amethyst_glint", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> EMERALD_GLINT = PARTICLES.register("emerald_glint", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> RUBY_GLINT = PARTICLES.register("ruby_glint", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> DIAMOND_GLINT = PARTICLES.register("diamond_glint", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> PURPUR_GLINT = PARTICLES.register("purpur_glint", () -> new BasicParticleType(false));
    public static final RegistryObject<BasicParticleType> BLIGHT_SMOKE = PARTICLES.register("blight_smoke", () -> new BasicParticleType(false));

    @SuppressWarnings("resource")
    @SubscribeEvent
    public static void registerParticleFactory(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(ParticleInit.ARCANE_GLINT.get(), MagicGlintParticle.ArcaneGlintFactory::new);
        Minecraft.getInstance().particleEngine.register(ParticleInit.AMETHYST_GLINT.get(), MagicGlintParticle.AmethystGlintFactory::new);
        Minecraft.getInstance().particleEngine.register(ParticleInit.EMERALD_GLINT.get(), MagicGlintParticle.EmeraldGlintFactory::new);
        Minecraft.getInstance().particleEngine.register(ParticleInit.RUBY_GLINT.get(), MagicGlintParticle.RubyGlintFactory::new);
        Minecraft.getInstance().particleEngine.register(ParticleInit.DIAMOND_GLINT.get(), MagicGlintParticle.DiamondGlintFactory::new);
        Minecraft.getInstance().particleEngine.register(ParticleInit.PURPUR_GLINT.get(), MagicGlintParticle.PurPurGlintFactory::new);
        Minecraft.getInstance().particleEngine.register(ParticleInit.BLIGHT_SMOKE.get(), CampfireParticle.CozySmokeFactory::new);
    }

}
