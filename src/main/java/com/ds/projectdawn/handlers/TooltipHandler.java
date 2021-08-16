package com.ds.projectdawn.handlers;

import com.ds.projectdawn.config.ToolTipConfiguration;
import com.ds.projectdawn.init.*;
import com.ds.projectdawn.objects.accessories.CurativeAccessoryItem;
import com.ds.projectdawn.objects.weapons.axeblades.AxebladeItem;
import com.ds.projectdawn.objects.weapons.battleaxes.BattleAxeItem;
import com.ds.projectdawn.objects.weapons.battleaxes.EffectBattleAxeItem;
import com.ds.projectdawn.objects.weapons.hammers.EffectHammerItem;
import com.ds.projectdawn.objects.weapons.hammers.HammerItem;
import com.ds.projectdawn.objects.weapons.staffs.AbstractStaffItem;
import com.ds.projectdawn.objects.weapons.swords.AbstractProjectileSwordItem;
import com.ds.projectdawn.objects.weapons.swords.EffectSwordItem;
import com.ds.projectdawn.objects.weapons.swords.LifeStealingSwordItem;
import com.ds.projectdawn.objects.weapons.tomes.AbstractTomeItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(value = {Dist.CLIENT}, modid = "projectdawn")
public class TooltipHandler
{
    public TooltipHandler() {}

    @SubscribeEvent
    public static void displayToolTip(ItemTooltipEvent event) {

        final ToolTipConfiguration config = new ToolTipConfiguration();
        List<ITextComponent> tooltip = new ArrayList();
        ItemStack stack = event.getItemStack();

        if (config.shouldShowAllToolTips()) {

            if (stack.getItem() instanceof AbstractStaffItem) {
                AbstractStaffItem staffItem = (AbstractStaffItem) stack.getItem();
                if (config.shouldShowStaffToolTips()) {
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.weapon.magic_damage", new Object[]{(int) staffItem.getMagicDamage() + 1})).withStyle(TextFormatting.DARK_GREEN));
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.weapon.experience_cost", new Object[]{staffItem.getXpAmount()})).withStyle(TextFormatting.DARK_GREEN));
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.weapon.projectile_velocity", new Object[]{staffItem.getVelocity()})).withStyle(TextFormatting.DARK_GREEN));
                }
            }
            if (stack.getItem() instanceof AbstractTomeItem) {
                AbstractTomeItem tomeItem = (AbstractTomeItem) stack.getItem();
                if (config.shouldShowTomeToolTips()) {
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.mainhand_offhand")).withStyle(TextFormatting.GRAY));
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.weapon.cooldown", new Object[]{tomeItem.getCooldown()})).withStyle(TextFormatting.DARK_GREEN));
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.weapon.magic_damage", new Object[]{(int) tomeItem.getMagicDamage() + 1})).withStyle(TextFormatting.DARK_GREEN));
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.weapon.experience_cost", new Object[]{tomeItem.getXpAmount()})).withStyle(TextFormatting.DARK_GREEN));
                    if (tomeItem.getVelocity() != 0)
                        tooltip.add((new TranslationTextComponent("tooltip.projectdawn.weapon.projectile_velocity", new Object[]{tomeItem.getVelocity()})).withStyle(TextFormatting.DARK_GREEN));
                }
            }
            if (stack.getItem() instanceof AxebladeItem) {
                AxebladeItem axebladeItem = (AxebladeItem) stack.getItem();
                if (config.shouldShowAxeBladeToolTips())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.weapon.strength", new Object[]{axebladeItem.getStrength()})).withStyle(TextFormatting.DARK_GREEN));
            }
            if (stack.getItem() instanceof BattleAxeItem) {
                BattleAxeItem battleAxeItem = (BattleAxeItem) stack.getItem();
                if (config.shouldShowBattleAxeToolTips())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.weapon.strength", new Object[]{battleAxeItem.getStrength()})).withStyle(TextFormatting.DARK_GREEN));
            }
            if (stack.getItem() instanceof HammerItem) {
                HammerItem hammerItem = (HammerItem) stack.getItem();
                if (config.shouldShowHammerTips())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.weapon.strength", new Object[]{hammerItem.getStrength()})).withStyle(TextFormatting.DARK_GREEN));
            }
            if (stack.getItem() instanceof AbstractProjectileSwordItem) {
                AbstractProjectileSwordItem projectileSwordItem = (AbstractProjectileSwordItem) stack.getItem();
                if (config.shouldShowProjectileSwordToolTips()) {
                    if (projectileSwordItem.getCostsXp()) {
                        tooltip.add((new TranslationTextComponent("tooltip.projectdawn.weapon.magic_damage", new Object[]{(int) projectileSwordItem.getProjectileDamage() + 1})).withStyle(TextFormatting.DARK_GREEN));
                        tooltip.add((new TranslationTextComponent("tooltip.projectdawn.weapon.experience_cost", new Object[]{projectileSwordItem.getXpAmount()})).withStyle(TextFormatting.DARK_GREEN));
                    }
                    else
                        tooltip.add((new TranslationTextComponent("tooltip.projectdawn.projectile_sword.damage", new Object[]{(int) projectileSwordItem.getProjectileDamage() + 1})).withStyle(TextFormatting.DARK_GREEN));
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.weapon.projectile_velocity", new Object[]{projectileSwordItem.getVelocity()})).withStyle(TextFormatting.DARK_GREEN));
                }
            }
            if (stack.getItem() instanceof TieredItem) {
                TieredItem tier = (TieredItem) stack.getItem();
                if (config.shouldShowRarityTip()) {
                    String rarityInput = tier.getRarity(tier.getDefaultInstance()).toString();
                    String rarityOutput = rarityInput.charAt(0) + rarityInput.substring(1).toLowerCase();
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.rarity", rarityOutput)).withStyle(tier.getRarity(tier.getDefaultInstance()).color));
                }
            }
            if (stack.getItem() instanceof CurativeAccessoryItem) {
                CurativeAccessoryItem curativeAccessoryItem = (CurativeAccessoryItem) stack.getItem();
                if (config.shouldShowCurativeAccessoryTip()) {
                    String effectInput = curativeAccessoryItem.getEffect().getRegistryName().toString();
                    String effectOutput = effectInput.substring(10);
                    if(curativeAccessoryItem.getMischievous())
                        tooltip.add((new TranslationTextComponent("tooltip.projectdawn.curative_accessory.mischievous", effectOutput)).withStyle(TextFormatting.GRAY));
                    else
                        tooltip.add((new TranslationTextComponent("tooltip.projectdawn.curative_accessory", effectOutput)).withStyle(TextFormatting.GRAY));
                }
            }
            if (stack.getItem() instanceof EffectSwordItem) {
                EffectSwordItem effectSwordItem = (EffectSwordItem) stack.getItem();
                if (config.shouldShowEffectSwordTip()) {
                    if (effectSwordItem.getEffect() != null) {
                        String effectInput = effectSwordItem.getEffect().getRegistryName().toString();
                        String effectOutput = effectInput.substring(10);
                        tooltip.add((new TranslationTextComponent("tooltip.projectdawn.effect_weapon.effect", effectOutput)).withStyle(TextFormatting.GRAY));
                    }
                    else
                        tooltip.add((new TranslationTextComponent("tooltip.projectdawn.effect_weapon.effect_null")).withStyle(TextFormatting.GRAY));
                }
            }
            if (stack.getItem() instanceof EffectBattleAxeItem) {
                EffectBattleAxeItem effectBattleAxeItem = (EffectBattleAxeItem) stack.getItem();
                if (config.shouldShowEffectBattleAxeTip()) {
                    if (effectBattleAxeItem.getEffect() != null) {
                        String effectInput = effectBattleAxeItem.getEffect().getRegistryName().toString();
                        String effectOutput = effectInput.substring(10);
                        tooltip.add((new TranslationTextComponent("tooltip.projectdawn.effect_weapon.effect", effectOutput)).withStyle(TextFormatting.GRAY));
                    }
                    else
                        tooltip.add((new TranslationTextComponent("tooltip.projectdawn.effect_weapon.effect_null")).withStyle(TextFormatting.GRAY));
                }
            }
            if (stack.getItem() instanceof LifeStealingSwordItem) {
                LifeStealingSwordItem lifeStealingSwordItem = (LifeStealingSwordItem) stack.getItem();
                if (config.shouldShowLifeStealingSwordTip()) {
                    float percentage = (lifeStealingSwordItem.getChance() / 100) * 100;
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.life_stealing_sword.percentage", (int)percentage)).withStyle(TextFormatting.GRAY));
                }
            }
            if (stack.getItem() instanceof EffectHammerItem) {
                EffectHammerItem effectHammerItem = (EffectHammerItem) stack.getItem();
                if (config.shouldShowEffectHammerTips()) {
                    if (effectHammerItem.getEffect() != null) {
                        String effectInput = effectHammerItem.getEffect().getRegistryName().toString();
                        String effectOutput = effectInput.substring(10);
                        tooltip.add((new TranslationTextComponent("tooltip.projectdawn.effect_weapon.effect", effectOutput)).withStyle(TextFormatting.GRAY));
                    }
                    else
                        tooltip.add((new TranslationTextComponent("tooltip.projectdawn.effect_weapon.effect_null")).withStyle(TextFormatting.GRAY));
                }
            }

            if (config.shouldShowModFlavorText()) {
                //Melee
                if (stack.getItem().asItem() == ItemInit.AXEBLADE.get())
                    tooltip.add((new TranslationTextComponent(String.format("tooltip.%s.axeblade", "projectdawn"), new Object[]{TextFormatting.GREEN.toString() + "tooltip.projectdawn.axeblade.bonus_damage"})).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.ARCANE_SWORD.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.arcane_sword")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.PIGLIN_DOOMBLADE.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.piglin_doomblade")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.VINDICATOR_AXE.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.vindicator_axe")).withStyle(TextFormatting.GRAY));

                if (stack.getItem().asItem() == ItemInit.PIGLIN_SWORD.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.piglin_toolset.oink_oink")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.PIGLIN_AXE.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.piglin_toolset.oink_oink")).withStyle(TextFormatting.GRAY));

                if (stack.getItem().asItem() == ItemInit.FIREBRAND_SWORD.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.firebrand_toolset")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.FIREBRAND_BATTLEAXE.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.firebrand_toolset")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.FIREBRAND_BOW.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.firebrand_toolset")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.FIREBRAND_PICKAXE.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.firebrand_toolset")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.FIREBRAND_SHOVEL.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.firebrand_toolset")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.FIREBRAND_AXE.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.firebrand_toolset")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.FIREBRAND_HOE.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.firebrand_toolset")).withStyle(TextFormatting.GRAY));

                if (stack.getItem().asItem() == ItemInit.FROSTBRAND_SWORD.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.frostbrand_toolset")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.FROSTBRAND_BATTLEAXE.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.frostbrand_toolset")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.FROSTBRAND_BOW.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.frostbrand_toolset")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.FROSTBRAND_PICKAXE.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.frostbrand_toolset")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.FROSTBRAND_SHOVEL.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.frostbrand_toolset")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.FROSTBRAND_AXE.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.frostbrand_toolset")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.FROSTBRAND_HOE.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.frostbrand_toolset")).withStyle(TextFormatting.GRAY));

                if (stack.getItem().asItem() == ItemInit.JAGGEDTOOTH_SWORD.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.jagged_tooth")).withStyle(TextFormatting.GRAY));

                if (stack.getItem().asItem() == ItemInit.ENDCATACLYSM_SWORD.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.endcataclysm_sword")).withStyle(TextFormatting.GRAY));

                if (stack.getItem().asItem() == ItemInit.GRAVITY_HAMMER.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.gravity_hammer")).withStyle(TextFormatting.GRAY));

                //Ranged
                if (stack.getItem().asItem() == ItemInit.POWPOW_BOMB.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.spore_bomb")).withStyle(TextFormatting.GRAY));

                //Magic
                if (stack.getItem().asItem() == ItemInit.MAGIC_HARP.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.magic_harp")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.EMERALD_STAFF.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.emerald_staff")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.RUBY_STAFF.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.ruby_staff")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.DIAMOND_STAFF.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.diamond_staff")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.RADIANT_PILLAR.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.radiant_pillar")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.NETHER_FORK.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.nether_fork")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.FROSTSPARK_STAFF.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.frostpark_staff")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.SOULFIRE_STAFF.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.soulfire_staff")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.SHADOWCRESCENT_STAFF.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.shadowcrescent_staff")).withStyle(TextFormatting.GRAY));

                if (stack.getItem().asItem() == ItemInit.INFERNO_TOME.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.inferno_tome")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.EVOKER_TOME.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.evoker_tome")).withStyle(TextFormatting.GRAY));

                //Tools

                //Accessories
                if (stack.getItem().asItem() == ItemInit.CHARM_REGENERATION.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.charm_regeneration")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.CHARM_ARCANE.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.charm_arcane")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.CHARM_MYTH.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.charm_myth")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.CHARM_MAGMA.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.charm_magma")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.CHARM_WITHERING.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.charm_withering")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.EMBER_RUNE.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.ember_rune")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.MAGIC_GAUNTLETS.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.magic_gauntlets")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.FIRE_GAUNTLETS.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.fire_gauntlets")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.RAVAGER_HORN.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.ravager_horn")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.SECRET_ANTIDOTE.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.secret_antidote")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.UNSTABLE_CRYSTAL.get()) {
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.unstable_crystal")).withStyle(TextFormatting.GRAY));
                }

                //Unobtainable/MISC
                if (stack.getItem().asItem() == ItemInit.NETHERITE_SHIELD.get() || stack.getItem().asItem() == ItemInit.CHARM_GOD.get() || stack.getItem().asItem() == ItemInit.DREADNOUGHT_AXEBLADE.get())
                    tooltip.add((new TranslationTextComponent("tooltip.projectdawn.unobtainable")).withStyle(TextFormatting.GRAY));
                if (stack.getItem().asItem() == ItemInit.ENDERITE_CRYSTAL.get())
                    tooltip.add((new TranslationTextComponent("WILL CRASH GAME IF RIGHT CLICKED!!!")).withStyle(TextFormatting.RED));

                //if(stack.getItem().asItem() == MeleeWeaponInit.DREADNOUGHT_AXEBLADE.get())
                //  tooltip.add((new TranslationTextComponent("Small chance to cause withering")).mergeStyle(TextFormatting.GRAY));
                //if(stack.getItem().asItem() == MeleeWeaponInit.DEMONSLAYER_SWORD.get())
                //  tooltip.add((new TranslationTextComponent("Hitting mobs with this weapon has a chance to grant health")).mergeStyle(TextFormatting.GRAY));
            }

            event.getToolTip().addAll(getInsertOffset(event.getFlags().isAdvanced(), event.getToolTip().size(), stack), tooltip);
        }
    }
    private static int getInsertOffset(boolean advanced, int tooltipSize, ItemStack stack) {
        int offset = 0;
        if (advanced) {
            ++offset;
            if (stack.hasCustomHoverName()) {
                ++offset;
            }
            if (stack.hasTag()) {
                ++offset;
            }
        }
        return Math.max(0, tooltipSize - offset);
    }

}
