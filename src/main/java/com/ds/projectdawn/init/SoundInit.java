package com.ds.projectdawn.init;

import com.ds.projectdawn.ProjectDawn;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public class SoundInit
{
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ProjectDawn.MOD_ID);
    public static final SoundEvent BASIC_MAGIC_SHOOT = register("basic_magic_shoot");
    public static final SoundEvent BASIC_MAGIC_HIT = register("basic_magic_hit");
    public static final SoundEvent BASIC_EMPTY_SPELLCAST = register("basic_empty_spellcast");
    public static final SoundEvent ICE_SHOOT = register("ice_shoot");
    public static final SoundEvent ICE_HIT = register("ice_hit");
    public static final SoundEvent MAGICHARP_USE = register("magicharp_use");
    public static final SoundEvent RADIANTHARP_USE = register("radiantharp_use");
    public static final SoundEvent ARCANE_SLASH = register("arcane_slash");
    public static final SoundEvent ENDSLASH = register("end_slash");
    public static final SoundEvent SHADOWCRESCENT_SHOOT = register("shadowcrescent_shoot");
    public static final SoundEvent VOIDCHARGE_HIT = register("voidcharge_hit");
    public static final SoundEvent HAMMER_SLAM = register("hammer_slam");

    public SoundInit() { }

    private static SoundEvent register(String soundName) {
        ResourceLocation soundID = new ResourceLocation("projectdawn", soundName);
        return (SoundEvent)(new SoundEvent(soundID)).setRegistryName(soundID);
    }
}

