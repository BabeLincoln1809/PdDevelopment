package com.ds.projectdawn.init;

import com.ds.projectdawn.ProjectDawn;
import com.ds.projectdawn.objects.accessories.AccessoryItem;
import com.ds.projectdawn.objects.accessories.CurativeAccessoryItem;
import com.ds.projectdawn.objects.accessories.HealingAccessoryItem;
import com.ds.projectdawn.objects.items.BombItem;
import com.ds.projectdawn.objects.items.InstrumentItem;
import com.ds.projectdawn.objects.weapons.*;
import com.ds.projectdawn.objects.weapons.axeblades.DreadnoughtAxebladeItem;
import com.ds.projectdawn.objects.weapons.axeblades.GreatAxebladeItem;
import com.ds.projectdawn.objects.weapons.axeblades.PiglinDoombladeItem;
import com.ds.projectdawn.objects.weapons.battleaxes.BattleAxeItem;
import com.ds.projectdawn.objects.weapons.battleaxes.EffectBattleAxeItem;
import com.ds.projectdawn.objects.weapons.hammers.EffectHammerItem;
import com.ds.projectdawn.objects.weapons.hammers.GravityHammerItem;
import com.ds.projectdawn.objects.weapons.hammers.HammerItem;
import com.ds.projectdawn.objects.weapons.hammers.SunBreakerHammerItem;
import com.ds.projectdawn.objects.weapons.staffs.*;
import com.ds.projectdawn.objects.weapons.swords.*;
import com.ds.projectdawn.objects.weapons.tomes.*;
import com.ds.projectdawn.tiers.MagicWeaponTier;
import com.ds.projectdawn.tiers.SingleWeaponTier;
import com.ds.projectdawn.tiers.ToolSetTier;
import net.minecraft.item.*;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public class ItemInit
{
    public static Map<Item, ResourceLocation> materialMap = new HashMap();
    public static Map<Item, ResourceLocation> instrumentMap = new HashMap();
    public static Map<Item, ResourceLocation> combatAccessoryMap = new HashMap();
    public static Map<Item, ResourceLocation> healthManaAccessoryMap = new HashMap();
    public static Map<Item, ResourceLocation> curativeAccessoryMap = new HashMap();
    public static Map<Item, ResourceLocation> meleeWeaponMap = new HashMap();
    public static Map<Item, ResourceLocation> magicWeaponMap = new HashMap();
    public static Map<Item, ResourceLocation> rangedWeaponMap = new HashMap();
    public static Map<Item, ResourceLocation> toolMap = new HashMap();
    public static final DeferredRegister<Item> ITEMS;
    public static final RegistryObject<Item> ARCANE_SHARD;
    public static final RegistryObject<Item> RADIANT_DUST;
    public static final RegistryObject<Item> RADIANT_CRYSTAL;
    public static final RegistryObject<Item> FIREBRAND_INGOT;
    public static final RegistryObject<Item> FROSTBRAND_INGOT;
    public static final RegistryObject<Item> ENDERITE_CRYSTAL;
    public static final RegistryObject<Item> RUBY;
    public static final RegistryObject<Item> WITHER_BONE;
    public static final RegistryObject<Item> WITHER_DUST;
    public static final RegistryObject<Item> IRON_ROD;
    public static final RegistryObject<Item> GOLDEN_ROD;
    public static final RegistryObject<Item> EMPTY_SPELLTOME;
    public static final RegistryObject<Item> BAMBOO_FLUTE;
    public static final RegistryObject<Item> HARP;
    public static final RegistryObject<Item> IRON_GAUNTLETS;
    public static final RegistryObject<Item> MYSTERIOUS_RUNE;
    public static final RegistryObject<Item> MAGIC_GAUNTLETS;
    public static final RegistryObject<Item> UNSTABLE_CRYSTAL;
    public static final RegistryObject<Item> EMBER_RUNE;
    public static final RegistryObject<Item> FIRE_GAUNTLETS;
    public static final RegistryObject<Item> RAVAGER_HORN;
    public static final RegistryObject<Item> CHARM_REGENERATION;
    public static final RegistryObject<Item> CHARM_ARCANE;
    public static final RegistryObject<Item> CHARM_MAGMA;
    public static final RegistryObject<Item> CHARM_MYTH;
    public static final RegistryObject<Item> SECRET_ANTIDOTE;
    public static final RegistryObject<Item> PHANTOM_EYES;
    public static final RegistryObject<Item> CHARM_WITHERING;
    public static final RegistryObject<Item> CHARM_GOD;
    public static final RegistryObject<Item> GOLDEN_BATTLEAXE;
    public static final RegistryObject<Item> PIGLIN_SWORD;
    public static final RegistryObject<Item> PIGLIN_DOOMBLADE;
    public static final RegistryObject<Item> WOODEN_BATTLEAXE;
    public static final RegistryObject<Item> SLIME_SLAMMER;
    public static final RegistryObject<Item> STONE_BATTLEAXE;
    public static final RegistryObject<Item> HAMMER;
    public static final RegistryObject<Item> IRON_BATTLEAXE;
    public static final RegistryObject<Item> AXEBLADE;
    public static final RegistryObject<Item> CURSED_BATTLEAXE;
    public static final RegistryObject<Item> DIAMOND_BATTLEAXE;
    public static final RegistryObject<Item> SUNBREAKER_HAMMER;
    public static final RegistryObject<Item> NETHERITE_BATTLEAXE;
    public static final RegistryObject<Item> NETHERITE_SHIELD;
    public static final RegistryObject<Item> FIREBRAND_BATTLEAXE;
    public static final RegistryObject<Item> FIREBRAND_SWORD;
    public static final RegistryObject<Item> FIREBRAND_PICKAXE;
    public static final RegistryObject<Item> FIREBRAND_SHOVEL;
    public static final RegistryObject<Item> FIREBRAND_AXE;
    public static final RegistryObject<Item> FIREBRAND_HOE;
    public static final RegistryObject<Item> FROSTBRAND_BATTLEAXE;
    public static final RegistryObject<Item> FROSTBRAND_SWORD;
    public static final RegistryObject<Item> FROSTBRAND_PICKAXE;
    public static final RegistryObject<Item> FROSTBRAND_SHOVEL;
    public static final RegistryObject<Item> FROSTBRAND_AXE;
    public static final RegistryObject<Item> FROSTBRAND_HOE;
    public static final RegistryObject<Item> ARCANE_SWORD;
    public static final RegistryObject<Item> JAGGEDTOOTH_SWORD;
    public static final RegistryObject<Item> ENDCATACLYSM_SWORD;
    public static final RegistryObject<Item> GRAVITY_HAMMER;
    public static final RegistryObject<Item> STORMLANDER_HAMMER;
    public static final RegistryObject<Item> DREADNOUGHT_AXEBLADE;
    public static final RegistryObject<Item> ARCANE_WAND;
    public static final RegistryObject<Item> AMETHYST_STAFF;
    public static final RegistryObject<Item> EMERALD_STAFF;
    public static final RegistryObject<Item> RUBY_STAFF;
    public static final RegistryObject<Item> DIAMOND_STAFF;
    public static final RegistryObject<Item> MAGIC_HARP;
    public static final RegistryObject<Item> RADIANT_PILLAR;
    public static final RegistryObject<Item> EYE_SORROW;
    public static final RegistryObject<Item> NETHER_FORK;
    public static final RegistryObject<Item> FROSTSPARK_STAFF;
    public static final RegistryObject<Item> CRYSTAL_TOME;
    public static final RegistryObject<Item> INFERNO_TOME;
    public static final RegistryObject<Item> EVOKER_TOME;
    public static final RegistryObject<Item> RADIANT_HARP;
    public static final RegistryObject<Item> SOULFIRE_STAFF;
    public static final RegistryObject<Item> SHADOWCRESCENT_STAFF;
    public static final RegistryObject<Item> FIREBRAND_BOW;
    public static final RegistryObject<Item> FROSTBRAND_BOW;
    public static final RegistryObject<Item> BOLTCASTER;
    public static final RegistryObject<Item> SPELLBOUND_BOLTCASTER;
    public static final RegistryObject<Item> WINDPIERCER;
    public static final RegistryObject<Item> BLAZE_BOMB;
    public static final RegistryObject<Item> POWPOW_BOMB;
    public static final RegistryObject<Item> VINDICATOR_AXE;
    public static final RegistryObject<Item> PIGLIN_AXE;



    public ItemInit() {
    }

    public static void putItemsInMap() {
        putMaterialsInMap();
        putInstrumentsInMap();
        putCombatAccessoriesInMap();
        putHealthManaAccessoriesInMap();
        putCurativeAccessoriesInMap();
        putMeleeWeaponsInMap();
        putMagicWeaponsInMap();
        putRangeWeaponsInMap();
        putToolsInMap();
    }

    private static void putMaterialsInMap() {
        materialMap.put((Item)ARCANE_SHARD.get(), ((Item)ARCANE_SHARD.get()).getRegistryName());
        materialMap.put((Item)RADIANT_CRYSTAL.get(), ((Item)RADIANT_CRYSTAL.get()).getRegistryName());
        materialMap.put((Item)RADIANT_DUST.get(), ((Item)RADIANT_DUST.get()).getRegistryName());
        materialMap.put((Item)FIREBRAND_INGOT.get(), ((Item)FIREBRAND_INGOT.get()).getRegistryName());
        materialMap.put((Item)FROSTBRAND_INGOT.get(), ((Item)FROSTBRAND_INGOT.get()).getRegistryName());
        materialMap.put((Item)ENDERITE_CRYSTAL.get(), ((Item)ENDERITE_CRYSTAL.get()).getRegistryName());
        materialMap.put((Item)RUBY.get(), ((Item)RUBY.get()).getRegistryName());
        materialMap.put((Item)WITHER_BONE.get(), ((Item)WITHER_BONE.get()).getRegistryName());
        materialMap.put((Item)WITHER_DUST.get(), ((Item)WITHER_DUST.get()).getRegistryName());
        materialMap.put((Item)IRON_ROD.get(), ((Item)IRON_ROD.get()).getRegistryName());
        materialMap.put((Item)GOLDEN_ROD.get(), ((Item)GOLDEN_ROD.get()).getRegistryName());
        materialMap.put((Item)EMPTY_SPELLTOME.get(), ((Item)EMPTY_SPELLTOME.get()).getRegistryName());
        materialMap.put((Item)IRON_GAUNTLETS.get(), ((Item)IRON_GAUNTLETS.get()).getRegistryName());
        materialMap.put((Item)MYSTERIOUS_RUNE.get(), ((Item)MYSTERIOUS_RUNE.get()).getRegistryName());
    }
    private static void putInstrumentsInMap() {
        instrumentMap.put((Item)BAMBOO_FLUTE.get(), ((Item)BAMBOO_FLUTE.get()).getRegistryName());
        instrumentMap.put((Item)HARP.get(), ((Item)HARP.get()).getRegistryName());
    }
    private static void putCombatAccessoriesInMap() {
        combatAccessoryMap.put((Item)MAGIC_GAUNTLETS.get(), ((Item)MAGIC_GAUNTLETS.get()).getRegistryName());
        combatAccessoryMap.put((Item)UNSTABLE_CRYSTAL.get(), ((Item)UNSTABLE_CRYSTAL.get()).getRegistryName());
        combatAccessoryMap.put((Item)EMBER_RUNE.get(), ((Item)EMBER_RUNE.get()).getRegistryName());
        combatAccessoryMap.put((Item)FIRE_GAUNTLETS.get(), ((Item)FIRE_GAUNTLETS.get()).getRegistryName());
        combatAccessoryMap.put((Item)RAVAGER_HORN.get(), ((Item)RAVAGER_HORN.get()).getRegistryName());
    }
    private static void putHealthManaAccessoriesInMap() {
        healthManaAccessoryMap.put((Item)CHARM_REGENERATION.get(), ((Item)CHARM_REGENERATION.get()).getRegistryName());
        healthManaAccessoryMap.put((Item)CHARM_ARCANE.get(), ((Item)CHARM_ARCANE.get()).getRegistryName());
        healthManaAccessoryMap.put((Item)CHARM_MAGMA.get(), ((Item)CHARM_MAGMA.get()).getRegistryName());
        healthManaAccessoryMap.put((Item)CHARM_MYTH.get(), ((Item)CHARM_MYTH.get()).getRegistryName());
    }
    private static void putCurativeAccessoriesInMap() {
        curativeAccessoryMap.put((Item)SECRET_ANTIDOTE.get(), ((Item)SECRET_ANTIDOTE.get()).getRegistryName());
        curativeAccessoryMap.put((Item)PHANTOM_EYES.get(), ((Item)PHANTOM_EYES.get()).getRegistryName());
        curativeAccessoryMap.put((Item)CHARM_WITHERING.get(), ((Item)CHARM_WITHERING.get()).getRegistryName());
        curativeAccessoryMap.put((Item)CHARM_GOD.get(), ((Item)CHARM_GOD.get()).getRegistryName());
    }
    private static void putMeleeWeaponsInMap() {
        meleeWeaponMap.put((Item)GOLDEN_BATTLEAXE.get(), ((Item)GOLDEN_BATTLEAXE.get()).getRegistryName());
        meleeWeaponMap.put((Item)PIGLIN_SWORD.get(), ((Item)PIGLIN_SWORD.get()).getRegistryName());
        meleeWeaponMap.put((Item)PIGLIN_DOOMBLADE.get(), ((Item)PIGLIN_DOOMBLADE.get()).getRegistryName());
        meleeWeaponMap.put((Item)WOODEN_BATTLEAXE.get(), ((Item)WOODEN_BATTLEAXE.get()).getRegistryName());
        meleeWeaponMap.put((Item)SLIME_SLAMMER.get(), ((Item)SLIME_SLAMMER.get()).getRegistryName());
        meleeWeaponMap.put((Item)STONE_BATTLEAXE.get(), ((Item)STONE_BATTLEAXE.get()).getRegistryName());
        meleeWeaponMap.put((Item)HAMMER.get(), ((Item)HAMMER.get()).getRegistryName());
        meleeWeaponMap.put((Item)IRON_BATTLEAXE.get(), ((Item)IRON_BATTLEAXE.get()).getRegistryName());
        meleeWeaponMap.put((Item)AXEBLADE.get(), ((Item)AXEBLADE.get()).getRegistryName());
        meleeWeaponMap.put((Item)CURSED_BATTLEAXE.get(), ((Item)CURSED_BATTLEAXE.get()).getRegistryName());
        meleeWeaponMap.put((Item)DIAMOND_BATTLEAXE.get(), ((Item)DIAMOND_BATTLEAXE.get()).getRegistryName());
        meleeWeaponMap.put((Item)SUNBREAKER_HAMMER.get(), ((Item)SUNBREAKER_HAMMER.get()).getRegistryName());
        meleeWeaponMap.put((Item)NETHERITE_BATTLEAXE.get(), ((Item)NETHERITE_BATTLEAXE.get()).getRegistryName());
        meleeWeaponMap.put((Item)NETHERITE_SHIELD.get(), ((Item)NETHERITE_SHIELD.get()).getRegistryName());
        meleeWeaponMap.put((Item)FIREBRAND_BATTLEAXE.get(), ((Item)FIREBRAND_BATTLEAXE.get()).getRegistryName());
        meleeWeaponMap.put((Item)FIREBRAND_SWORD.get(), ((Item)FIREBRAND_SWORD.get()).getRegistryName());
        meleeWeaponMap.put((Item)FROSTBRAND_BATTLEAXE.get(), ((Item)FROSTBRAND_BATTLEAXE.get()).getRegistryName());
        meleeWeaponMap.put((Item)FROSTBRAND_SWORD.get(), ((Item)FROSTBRAND_SWORD.get()).getRegistryName());
        meleeWeaponMap.put((Item)ARCANE_SWORD.get(), ((Item)ARCANE_SWORD.get()).getRegistryName());
        meleeWeaponMap.put((Item)JAGGEDTOOTH_SWORD.get(), ((Item)JAGGEDTOOTH_SWORD.get()).getRegistryName());
        meleeWeaponMap.put((Item)ENDCATACLYSM_SWORD.get(), ((Item)ENDCATACLYSM_SWORD.get()).getRegistryName());
        meleeWeaponMap.put((Item)GRAVITY_HAMMER.get(), ((Item)GRAVITY_HAMMER.get()).getRegistryName());
        meleeWeaponMap.put((Item)STORMLANDER_HAMMER.get(), ((Item)STORMLANDER_HAMMER.get()).getRegistryName());
        meleeWeaponMap.put((Item)DREADNOUGHT_AXEBLADE.get(), ((Item)DREADNOUGHT_AXEBLADE.get()).getRegistryName());
    }
    private static void putMagicWeaponsInMap() {
        magicWeaponMap.put((Item)ARCANE_WAND.get(), ((Item)ARCANE_WAND.get()).getRegistryName());
        magicWeaponMap.put((Item)AMETHYST_STAFF.get(), ((Item)AMETHYST_STAFF.get()).getRegistryName());
        magicWeaponMap.put((Item)EMERALD_STAFF.get(), ((Item)EMERALD_STAFF.get()).getRegistryName());
        magicWeaponMap.put((Item)RUBY_STAFF.get(), ((Item)RUBY_STAFF.get()).getRegistryName());
        magicWeaponMap.put((Item)DIAMOND_STAFF.get(), ((Item)DIAMOND_STAFF.get()).getRegistryName());
        magicWeaponMap.put((Item)MAGIC_HARP.get(), ((Item)MAGIC_HARP.get()).getRegistryName());
        magicWeaponMap.put((Item)RADIANT_PILLAR.get(), ((Item)RADIANT_PILLAR.get()).getRegistryName());
        magicWeaponMap.put((Item)NETHER_FORK.get(), ((Item)NETHER_FORK.get()).getRegistryName());
        magicWeaponMap.put((Item)FROSTSPARK_STAFF.get(), ((Item)FROSTSPARK_STAFF.get()).getRegistryName());
        magicWeaponMap.put((Item)EYE_SORROW.get(), ((Item)EYE_SORROW.get()).getRegistryName());
        magicWeaponMap.put((Item)CRYSTAL_TOME.get(), ((Item)CRYSTAL_TOME.get()).getRegistryName());
        magicWeaponMap.put((Item)INFERNO_TOME.get(), ((Item)INFERNO_TOME.get()).getRegistryName());
        magicWeaponMap.put((Item)EVOKER_TOME.get(), ((Item)EVOKER_TOME.get()).getRegistryName());
        magicWeaponMap.put((Item)RADIANT_HARP.get(), ((Item)RADIANT_HARP.get()).getRegistryName());
        magicWeaponMap.put((Item)SOULFIRE_STAFF.get(), ((Item)SOULFIRE_STAFF.get()).getRegistryName());
        magicWeaponMap.put((Item)SHADOWCRESCENT_STAFF.get(), ((Item)SHADOWCRESCENT_STAFF.get()).getRegistryName());
    }
    private static void putRangeWeaponsInMap() {
        rangedWeaponMap.put((Item)FIREBRAND_BOW.get(), ((Item)FIREBRAND_BOW.get()).getRegistryName());
        rangedWeaponMap.put((Item)FROSTBRAND_BOW.get(), ((Item)FROSTBRAND_BOW.get()).getRegistryName());
        rangedWeaponMap.put((Item)BOLTCASTER.get(), ((Item)BOLTCASTER.get()).getRegistryName());
        rangedWeaponMap.put((Item)SPELLBOUND_BOLTCASTER.get(), ((Item)SPELLBOUND_BOLTCASTER.get()).getRegistryName());
        rangedWeaponMap.put((Item)WINDPIERCER.get(), ((Item)WINDPIERCER.get()).getRegistryName());
        rangedWeaponMap.put((Item)BLAZE_BOMB.get(), ((Item)BLAZE_BOMB.get()).getRegistryName());
        rangedWeaponMap.put((Item)POWPOW_BOMB.get(), ((Item)POWPOW_BOMB.get()).getRegistryName());
    }
    private static void putToolsInMap() {
        toolMap.put((Item)FIREBRAND_PICKAXE.get(), ((Item)FIREBRAND_PICKAXE.get()).getRegistryName());
        toolMap.put((Item)FIREBRAND_SHOVEL.get(), ((Item)FIREBRAND_SHOVEL.get()).getRegistryName());
        toolMap.put((Item)FIREBRAND_AXE.get(), ((Item)FIREBRAND_AXE.get()).getRegistryName());
        toolMap.put((Item)FIREBRAND_HOE.get(), ((Item)FIREBRAND_HOE.get()).getRegistryName());
        toolMap.put((Item)FROSTBRAND_PICKAXE.get(), ((Item)FROSTBRAND_PICKAXE.get()).getRegistryName());
        toolMap.put((Item)FROSTBRAND_SHOVEL.get(), ((Item)FROSTBRAND_SHOVEL.get()).getRegistryName());
        toolMap.put((Item)FROSTBRAND_AXE.get(), ((Item)FROSTBRAND_AXE.get()).getRegistryName());
        toolMap.put((Item)FROSTBRAND_HOE.get(), ((Item)FROSTBRAND_HOE.get()).getRegistryName());
        toolMap.put((Item)VINDICATOR_AXE.get(), ((Item)VINDICATOR_AXE.get()).getRegistryName());
        toolMap.put((Item)PIGLIN_AXE.get(), ((Item)PIGLIN_AXE.get()).getRegistryName());
    }

    static
    {
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ProjectDawn.MOD_ID);
        ARCANE_SHARD = ITEMS.register("arcane_shard", () -> { return new Item(new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        RADIANT_CRYSTAL = ITEMS.register("radiant_crystal", () -> { return new Item(new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        RADIANT_DUST = ITEMS.register("radiant_dust", () -> { return new Item(new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        FIREBRAND_INGOT = ITEMS.register("firebrand_ingot", () -> { return new Item(new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB).fireResistant()); });
        FROSTBRAND_INGOT = ITEMS.register("frostbrand_ingot", () -> { return new Item(new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB).fireResistant()); });
        ENDERITE_CRYSTAL = ITEMS.register("enderite_crystal", () -> { return new Item(new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        RUBY = ITEMS.register("ruby", () -> { return new Item(new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        WITHER_BONE = ITEMS.register("wither_bone", () -> { return new Item(new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        WITHER_DUST = ITEMS.register("wither_dust", () -> { return new Item(new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        IRON_ROD = ITEMS.register("iron_rod", () -> { return new Item(new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        GOLDEN_ROD = ITEMS.register("golden_rod", () -> { return new Item(new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        EMPTY_SPELLTOME = ITEMS.register("empty_spelltome", () -> { return new Item(new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        BAMBOO_FLUTE = ITEMS.register("bamboo_flute", () -> { return new InstrumentItem(SoundEvents.NOTE_BLOCK_FLUTE, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        HARP = ITEMS.register("harp", () -> { return new InstrumentItem(SoundEvents.NOTE_BLOCK_HARP, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        IRON_GAUNTLETS = ITEMS.register("iron_gauntlets", () -> { return new Item(new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        MYSTERIOUS_RUNE = ITEMS.register("mysterious_rune", () -> { return new Item(new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        MAGIC_GAUNTLETS = ITEMS.register("magic_gauntlets", () -> { return new AccessoryItem(new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        UNSTABLE_CRYSTAL = ITEMS.register("unstable_crystal", () -> { return new AccessoryItem(new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        EMBER_RUNE = ITEMS.register("ember_rune", () -> { return new AccessoryItem(new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        FIRE_GAUNTLETS = ITEMS.register("fire_gauntlets", () -> { return new AccessoryItem(new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        RAVAGER_HORN = ITEMS.register("ravager_horn", () -> { return new AccessoryItem(new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        CHARM_REGENERATION = ITEMS.register("charm_regeneration", () -> { return new HealingAccessoryItem(150, 2, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        CHARM_ARCANE = ITEMS.register("charm_arcane", () -> { return new AccessoryItem(new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        CHARM_MAGMA = ITEMS.register("charm_magma", () -> { return new AccessoryItem(new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        CHARM_MYTH = ITEMS.register("charm_myth", () -> { return new AccessoryItem(new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB).stacksTo(1)); });
        SECRET_ANTIDOTE = ITEMS.register("secret_antidote", () -> { return new CurativeAccessoryItem(Effects.POISON, false,  new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        PHANTOM_EYES = ITEMS.register("phantom_eyes", () -> { return new CurativeAccessoryItem(Effects.BLINDNESS, false,  new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        CHARM_WITHERING = ITEMS.register("charm_withering", () -> { return new CurativeAccessoryItem(Effects.WITHER, true,  new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        CHARM_GOD = ITEMS.register("charm_god", () -> { return new AccessoryItem(new Item.Properties().tab(ProjectDawn.PROJECTDAWN_ITEMS_TAB)); });
        GOLDEN_BATTLEAXE = ITEMS.register("golden_battleaxe", () -> { return new BattleAxeItem(ItemTier.GOLD, 5, -3.2f, 0.60F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB)); });
        PIGLIN_SWORD = ITEMS.register("piglin_sword", () -> { return new SwordItem(ItemTier.GOLD, 3, -2.4F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB)); });
        PIGLIN_DOOMBLADE = ITEMS.register("piglin_doomblade", () -> { return new PiglinDoombladeItem(SingleWeaponTier.DOOMBLADE, 0, -3.0F, 0.65F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB)); });
        WOODEN_BATTLEAXE = ITEMS.register("wooden_battleaxe", () -> { return new BattleAxeItem(ItemTier.WOOD, 5, -3.2f, 0.65F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB)); });
        SLIME_SLAMMER = ITEMS.register("slime_slammer", () -> { return new HammerItem(SingleWeaponTier.SLAMMER, 0, -3.2f, 0.85F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB)); });
        STONE_BATTLEAXE = ITEMS.register("stone_battleaxe", () -> { return new BattleAxeItem(ItemTier.STONE, 5, -3.2f, 0.70F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB)); });
        HAMMER = ITEMS.register("hammer", () -> { return new HammerItem(ItemTier.STONE, 5, -3.2f, 0.45F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB)); });
        IRON_BATTLEAXE = ITEMS.register("iron_battleaxe", () -> { return new BattleAxeItem(ItemTier.IRON, 5, -3.2f, 0.75F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB)); });
        AXEBLADE = ITEMS.register("axeblade", () -> { return new GreatAxebladeItem(ItemTier.IRON, 3, -3.0f, 0.65F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB)); });
        CURSED_BATTLEAXE = ITEMS.register("cursed_battleaxe", () -> { return new BattleAxeItem(ItemTier.IRON, 6, -3.0f, 0.75F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB)); });
        DIAMOND_BATTLEAXE = ITEMS.register("diamond_battleaxe", () -> { return new BattleAxeItem(ItemTier.DIAMOND, 5, -3.2f, 0.80F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB)); });
        SUNBREAKER_HAMMER = ITEMS.register("sunbreaker_hammer", () -> { return new SunBreakerHammerItem(SingleWeaponTier.SUNBREAKER, 0, -3.2f, 0.60F, null, 6, 0, true, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB).fireResistant()); });
        NETHERITE_BATTLEAXE = ITEMS.register("netherite_battleaxe", () -> { return new BattleAxeItem(ItemTier.NETHERITE, 5, -3.2f, 0.85F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB).fireResistant()); });
        NETHERITE_SHIELD = ITEMS.register("netherite_shield", () -> { return new PDShieldItem(ItemTier.NETHERITE, new Item.Properties().fireResistant()); });
        FIREBRAND_BATTLEAXE = ITEMS.register("firebrand_battleaxe", () -> { return new EffectBattleAxeItem(ToolSetTier.FIREBRAND, 5, -3.2f, 0.90F, null, 8, 0, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB).fireResistant()); });
        FIREBRAND_SWORD = ITEMS.register("firebrand_sword", () -> { return new EffectSwordItem(ToolSetTier.FIREBRAND, 3, -2.4F, null, 6, 0, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB).fireResistant()); });
        FIREBRAND_PICKAXE = ITEMS.register("firebrand_pickaxe", () -> new PickaxeItem(ToolSetTier.FIREBRAND, 1, -1.6f, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_TOOLS_TAB).fireResistant()));
        FIREBRAND_SHOVEL = ITEMS.register("firebrand_shovel", () -> new ShovelItem(ToolSetTier.FIREBRAND, 1.5F, -3.0f, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_TOOLS_TAB).fireResistant()));
        FIREBRAND_AXE = ITEMS.register("firebrand_axe", () -> new AxeItem(ToolSetTier.FIREBRAND, 5, -3.0f, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_TOOLS_TAB).fireResistant()));
        FIREBRAND_HOE = ITEMS.register("firebrand_hoe", () -> new HoeItem(ToolSetTier.FIREBRAND, -5, 0.0f, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_TOOLS_TAB).fireResistant()));
        FROSTBRAND_BATTLEAXE = ITEMS.register("frostbrand_battleaxe", () -> { return new EffectBattleAxeItem(ToolSetTier.FROSTBRAND, 5, -3.2f, 0.90F, Effects.MOVEMENT_SLOWDOWN, 350, 0, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB).fireResistant()); });
        FROSTBRAND_SWORD = ITEMS.register("frostbrand_sword", () -> { return new EffectSwordItem(ToolSetTier.FROSTBRAND, 3, -2.4F, Effects.MOVEMENT_SLOWDOWN, 250, 0, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB).fireResistant()); });
        FROSTBRAND_PICKAXE = ITEMS.register("frostbrand_pickaxe", () -> new PickaxeItem(ToolSetTier.FROSTBRAND, 1, -1.6f, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_TOOLS_TAB).fireResistant()));
        FROSTBRAND_SHOVEL = ITEMS.register("frostbrand_shovel", () -> new ShovelItem(ToolSetTier.FROSTBRAND, 1.5F, -3.0f, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_TOOLS_TAB).fireResistant()));
        FROSTBRAND_AXE = ITEMS.register("frostbrand_axe", () -> new AxeItem(ToolSetTier.FROSTBRAND, 5, -3.0f, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_TOOLS_TAB).fireResistant()));
        FROSTBRAND_HOE = ITEMS.register("frostbrand_hoe", () -> new HoeItem(ToolSetTier.FROSTBRAND, -5, 0.0f, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_TOOLS_TAB).fireResistant()));
        ARCANE_SWORD = ITEMS.register("arcane_sword", () -> { return new ArcaneSwordItem(SingleWeaponTier.ARCANE, 0, -2.0F, 3.0F, 1.25F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB)); });
        JAGGEDTOOTH_SWORD = ITEMS.register("jaggedtooth_sword", () -> { return new LifeStealingSwordItem(SingleWeaponTier.JAGGEDTOOTH,0, -2.4F, 20, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB)); });
        ENDCATACLYSM_SWORD = ITEMS.register("endcataclysm_sword", () -> { return new EndCataclysmSwordItem(ToolSetTier.ENDERITE, 0, -2.0F, 5.0F, 2.0F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB).fireResistant().rarity(Rarity.UNCOMMON)); });
        GRAVITY_HAMMER = ITEMS.register("gravity_hammer", () -> { return new GravityHammerItem(ToolSetTier.ENDERITE, 1, -3.2f, 0.75F, Effects.LEVITATION, 100, 0, false, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB).rarity(Rarity.EPIC)); });
        STORMLANDER_HAMMER = ITEMS.register("stormlander_hammer", () -> { return new HammerItem(SingleWeaponTier.STORMLANDER, 0, -3.2f, 0.75F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB).rarity(Rarity.EPIC)); });
        DREADNOUGHT_AXEBLADE = ITEMS.register("dreadnought_axeblade", () -> { return new DreadnoughtAxebladeItem(SingleWeaponTier.DREADNOUGHT, 0, -3.0F, 0.65F, new Item.Properties()); });
        ARCANE_WAND = ITEMS.register("arcane_wand", () -> { return new ArcaneWandItem(MagicWeaponTier.ARCANE_WAND, 3, -2.6F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB)); });
        AMETHYST_STAFF = ITEMS.register("amethyst_staff", () -> { return new AmethystStaffItem(MagicWeaponTier.AMETHYSTSTAFF, 3, -2.4F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB)); });
        EMERALD_STAFF = ITEMS.register("emerald_staff", () -> { return new EmeraldStaffItem(MagicWeaponTier.EMERALDSTAFF, 3, -2.4F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB)); });
        RUBY_STAFF = ITEMS.register("ruby_staff", () -> { return new RubyStaffItem(MagicWeaponTier.RUBYSTAFF, 3, -2.4F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB)); });
        DIAMOND_STAFF = ITEMS.register("diamond_staff", () -> { return new DiamondStaffItem(MagicWeaponTier.DIAMONDSTAFF, 3, -2.4F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB)); });
        MAGIC_HARP = ITEMS.register("magic_harp", () -> { return new MagicHarpItem(MagicWeaponTier.MAGICHARP, 3, 30, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB)); });
        RADIANT_PILLAR = ITEMS.register("radiant_pillar", () -> { return new RadiantPillarItem(MagicWeaponTier.RADIANTPILLAR, 3, -2.4F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB)); });
        EYE_SORROW = ITEMS.register("eye_sorrow", () -> { return new NetherForkItem(MagicWeaponTier.FORK, 3, -3.0F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB).fireResistant()); });
        NETHER_FORK = ITEMS.register("nether_fork", () -> { return new NetherForkItem(MagicWeaponTier.FROSTSPARK, 3, -2.4F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB).fireResistant()); });
        FROSTSPARK_STAFF = ITEMS.register("frostspark_staff", () -> { return new FrostSparkStaffItem(MagicWeaponTier.FORK, 3, -2.4F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB).fireResistant()); });
        CRYSTAL_TOME = ITEMS.register("crystal_tome", () -> { return new CrystalTomeItem(MagicWeaponTier.INFERNOTOME,3,25, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB)); });
        INFERNO_TOME = ITEMS.register("inferno_tome", () -> { return new InfernoTomeItem(MagicWeaponTier.INFERNOTOME,3,25, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB).fireResistant()); });
        EVOKER_TOME = ITEMS.register("evoker_tome", () -> { return new EvokerTomeItem(MagicWeaponTier.EVOKERTOME, 3, 30, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB)); });
        RADIANT_HARP = ITEMS.register("radiant_harp", () -> { return new RadiantHarpItem(MagicWeaponTier.MAGICHARP, 3, 30, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB)); });
        SOULFIRE_STAFF = ITEMS.register("soulfire_staff", () -> { return new SoulFireStaffItem(MagicWeaponTier.SOULSTAFF, 3,-2.6F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB)); });
        SHADOWCRESCENT_STAFF = ITEMS.register("shadowcrescent_staff", () -> { return new ShadowCrescentStaffItem(MagicWeaponTier.SHADOWCRESCENT, 3, -2.4F, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB).fireResistant().rarity(Rarity.UNCOMMON)); });
        FIREBRAND_BOW = ITEMS.register("firebrand_bow", () -> { return new EffectBowItem(3, 5, 1, 100, 0, new Item.Properties().durability(2031).tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB).fireResistant()); });
        FROSTBRAND_BOW = ITEMS.register("frostbrand_bow", () -> { return new EffectBowItem(3, 15, 0, 0, 0, new Item.Properties().durability(2031).tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB)); });
        BOLTCASTER = ITEMS.register("boltcaster", () -> { return new BoltcasterItem(0.0D,7, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB).durability(163)); });
        SPELLBOUND_BOLTCASTER = ITEMS.register("spellbound_boltcaster", () -> { return new BoltcasterItem(1.0D,8, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB).durability(637)); });
        WINDPIERCER = ITEMS.register("windpiercer", () -> { return new BoltcasterItem(1.0D,10, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB).durability(1385)); });
        BLAZE_BOMB = ITEMS.register("blaze_bomb", () -> { return new BombItem(25, false, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB).stacksTo(16)); });
        POWPOW_BOMB = ITEMS.register("powpow_bomb", () -> { return new BombItem(75, true, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_WEAPONS_TAB).stacksTo(1).durability(75)); });
        VINDICATOR_AXE = ITEMS.register("vindicator_axe", () -> new AxeItem(ItemTier.IRON, 5, -3.0f, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_TOOLS_TAB)));
        PIGLIN_AXE = ITEMS.register("piglin_axe", () -> new AxeItem(ItemTier.GOLD, 5, -3.0f, new Item.Properties().tab(ProjectDawn.PROJECTDAWN_TOOLS_TAB)));

    }

}
