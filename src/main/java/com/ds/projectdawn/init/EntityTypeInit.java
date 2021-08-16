package com.ds.projectdawn.init;

import com.ds.projectdawn.ProjectDawn;
import com.ds.projectdawn.objects.entities.projectiles.*;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityTypeInit
{
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, ProjectDawn.MOD_ID);

    public static final RegistryObject<EntityType<ArcaneBoltEntity>> ARCANE_BOLT = ENTITY_TYPES.register("arcane_bolt", () ->
            EntityType.Builder.<ArcaneBoltEntity>of(ArcaneBoltEntity::new, EntityClassification.MISC).sized(0.25f, 0.25f).build("arcane_bolt"));
    public static final RegistryObject<EntityType<AmethystBoltEntity>> AMETHYST_BOLT = ENTITY_TYPES.register("amethyst_bolt", () ->
            EntityType.Builder.<AmethystBoltEntity>of(AmethystBoltEntity::new, EntityClassification.MISC).sized(0.25f, 0.25f).build("amethyst_bolt"));
    public static final RegistryObject<EntityType<EmeraldBoltEntity>> EMERALD_BOLT = ENTITY_TYPES.register("emerald_bolt", () ->
            EntityType.Builder.<EmeraldBoltEntity>of(EmeraldBoltEntity::new, EntityClassification.MISC).sized(0.25f, 0.25f).build("emerald_bolt"));
    public static final RegistryObject<EntityType<RubyBoltEntity>> RUBY_BOLT = ENTITY_TYPES.register("ruby_bolt", () ->
            EntityType.Builder.<RubyBoltEntity>of(RubyBoltEntity::new, EntityClassification.MISC).sized(0.25f, 0.25f).build("ruby_bolt"));
    public static final RegistryObject<EntityType<DiamondBoltEntity>> DIAMOND_BOLT = ENTITY_TYPES.register("diamond_bolt", () ->
            EntityType.Builder.<DiamondBoltEntity>of(DiamondBoltEntity::new, EntityClassification.MISC).sized(0.25f, 0.25f).build("diamond_bolt"));
    public static final RegistryObject<EntityType<RadiantBoltEntity>> RADIANT_BOLT = ENTITY_TYPES.register("radiant_bolt", () ->
            EntityType.Builder.<RadiantBoltEntity>of(RadiantBoltEntity::new, EntityClassification.MISC).sized(0.25f, 0.25f).build("radiant_bolt"));

    public static final RegistryObject<EntityType<ArcaneSlashEntity>> ARCANE_SLASH = ENTITY_TYPES.register("arcane_slash", () ->
            EntityType.Builder.<ArcaneSlashEntity>of(ArcaneSlashEntity::new, EntityClassification.MISC).sized(1.0f, 0.25f).build("arcane_slash"));

    public static final RegistryObject<EntityType<MagicNoteEntity>> MAGIC_NOTE = ENTITY_TYPES.register("magic_note", () ->
            EntityType.Builder.<MagicNoteEntity>of(MagicNoteEntity::new, EntityClassification.MISC).sized(0.25f, 0.25f).build("magic_note"));

    public static final RegistryObject<EntityType<EndSlashEntity>> END_SLASH = ENTITY_TYPES.register("end_slash", () ->
            EntityType.Builder.<EndSlashEntity>of(EndSlashEntity::new, EntityClassification.MISC).sized(1.0f, 0.25f).build("end_slash"));

    public static final RegistryObject<EntityType<FrostSparkEntity>> FROSTSPARK = ENTITY_TYPES.register("frostspark", () ->
            EntityType.Builder.<FrostSparkEntity>of(FrostSparkEntity::new, EntityClassification.MISC).sized(0.25f, 0.25f).build("frostspark"));

    public static final RegistryObject<EntityType<FireBallEntity>> FIRE_BALL = ENTITY_TYPES.register("fire_ball", () ->
            EntityType.Builder.<FireBallEntity>of(FireBallEntity::new, EntityClassification.MISC).sized(0.25f, 0.25f).build("fire_ball"));

    public static final RegistryObject<EntityType<InfernoBallEntity>> INFERNO_BALL = ENTITY_TYPES.register("inferno_ball", () ->
            EntityType.Builder.<InfernoBallEntity>of(InfernoBallEntity::new, EntityClassification.MISC).sized(0.25f, 0.25f).build("inferno_ball"));

    public static final RegistryObject<EntityType<SoulChargeEntity>> SOULCHARGE = ENTITY_TYPES.register("soulcharge", () ->
            EntityType.Builder.<SoulChargeEntity>of(SoulChargeEntity::new, EntityClassification.MISC).sized(0.25f, 0.25f).build("soulcharge"));

    public static final RegistryObject<EntityType<VoidChargeEntity>> VOIDCHARGE = ENTITY_TYPES.register("voidcharge", () ->
            EntityType.Builder.<VoidChargeEntity>of(VoidChargeEntity::new, EntityClassification.MISC).sized(0.25f, 0.25f).build("voidcharge"));

    public static final RegistryObject<EntityType<BlazeBombEntity>> BLAZE_BOMB = ENTITY_TYPES.register("blaze_bomb", () ->
            EntityType.Builder.<BlazeBombEntity>of(BlazeBombEntity::new, EntityClassification.MISC).sized(0.25f, 0.25f).build("blaze_bomb"));

    public static final RegistryObject<EntityType<PowPowBombEntity>> POWPOW_BOMB = ENTITY_TYPES.register("powpow_bomb", () ->
            EntityType.Builder.<PowPowBombEntity>of(PowPowBombEntity::new, EntityClassification.MISC).sized(0.25f, 0.25f).build("powpow_bomb"));

    public static final RegistryObject<EntityType<ZombieEntity>> TEST = ENTITY_TYPES.register("test",
            () -> EntityType.Builder.<ZombieEntity>of(ZombieEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).clientTrackingRange(8).build(new ResourceLocation(ProjectDawn.MOD_ID, "test").toString()));

    public static void init()
    {
        ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }


}

