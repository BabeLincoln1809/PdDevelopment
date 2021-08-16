package com.ds.projectdawn.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ToolTipConfiguration{
    private final ForgeConfigSpec spec;
    private final ForgeConfigSpec.BooleanValue showAllToolTips;
    private final ForgeConfigSpec.BooleanValue showModFlavorText;
    private final ForgeConfigSpec.BooleanValue showVanillaFlavorText;
    private final ForgeConfigSpec.BooleanValue showStaffTips;
    private final ForgeConfigSpec.BooleanValue showTomeTips;
    private final ForgeConfigSpec.BooleanValue showAxeBladeTips;
    private final ForgeConfigSpec.BooleanValue showBattleAxeTips;
    private final ForgeConfigSpec.BooleanValue showProjectileSwordTips;
    private final ForgeConfigSpec.BooleanValue showRarityTip;
    private final ForgeConfigSpec.BooleanValue showCurativeAccessoryTip;
    private final ForgeConfigSpec.BooleanValue showEffectSwordTip;
    private final ForgeConfigSpec.BooleanValue showEffectBattleAxeTip;
    private final ForgeConfigSpec.BooleanValue showLifeStealingSwordTip;
    private final ForgeConfigSpec.BooleanValue showHammerTips;
    private final ForgeConfigSpec.BooleanValue showEffectHammerTips;


    public ToolTipConfiguration() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("ToolTip settings");
        builder.push("tooltip");
        builder.comment("Should all tool tips be shown?");
        this.showAllToolTips = builder.define("show-all-tooltips", true);
        builder.comment("Should mod flavor text be shown?");
        this.showModFlavorText = builder.define("show-mod-flavor-text", true);
        builder.comment("Should vanilla flavor text be shown?");
        this.showVanillaFlavorText = builder.define("show-vanilla-flavor-text", true);
        builder.comment("Should staff tips be shown?");
        this.showStaffTips = builder.define("show-staff-tooltips", true);
        builder.comment("Should tome tips be shown?");
        this.showTomeTips = builder.define("show-tome-tooltips", true);
        builder.comment("Should axeblade tips be shown?");
        this.showAxeBladeTips = builder.define("show-axeblade-tooltips", true);
        builder.comment("Should battleaxe tips be shown?");
        this.showBattleAxeTips = builder.define("show-battleaxe-tooltips", true);
        builder.comment("Should projectile sword tips be shown?");
        this.showProjectileSwordTips = builder.define("show-projectile-sword-tooltips", true);
        builder.comment("Should rarity tip be shown?");
        this.showRarityTip = builder.define("show-rarity-tooltip", true);
        builder.comment("Should curative accessory tip be shown?");
        this.showCurativeAccessoryTip = builder.define("show-curative-accessory-tooltip", true);
        builder.comment("Should effect sword tip be shown?");
        this.showEffectSwordTip = builder.define("show-effect-sword-tooltip", true);
        builder.comment("Should effect battleaxe tip be shown?");
        this.showEffectBattleAxeTip = builder.define("show-effect-battleaxe-tooltip", true);
        builder.comment("Should life stealing sword tip be shown?");
        this.showLifeStealingSwordTip = builder.define("show-life-stealing-sword-tooltip", true);
        builder.comment("Should hammer tips be shown?");
        this.showHammerTips = builder.define("show-hammer-tooltips", true);
        builder.comment("Should effect hammer tips be shown?");
        this.showEffectHammerTips = builder.define("show-effect-hammer-tooltips", true);
        builder.pop();
        this.spec = builder.build();
    }

    public ForgeConfigSpec getSpec() {
        return this.spec;
    }

    public boolean shouldShowAllToolTips() { return (Boolean)this.showAllToolTips.get(); }
    public boolean shouldShowModFlavorText() { return (Boolean)this.showModFlavorText.get(); }
    public boolean shouldShowVanillaFlavorText() { return (Boolean)this.showVanillaFlavorText.get(); }
    public boolean shouldShowStaffToolTips() { return (Boolean)this.showStaffTips.get(); }
    public boolean shouldShowTomeToolTips() { return (Boolean)this.showTomeTips.get(); }
    public boolean shouldShowAxeBladeToolTips() { return (Boolean)this.showAxeBladeTips.get(); }
    public boolean shouldShowBattleAxeToolTips() { return (Boolean)this.showBattleAxeTips.get(); }
    public boolean shouldShowProjectileSwordToolTips() { return (Boolean)this.showProjectileSwordTips.get(); }
    public boolean shouldShowRarityTip() { return (Boolean)this.showRarityTip.get(); }
    public boolean shouldShowCurativeAccessoryTip() { return (Boolean)this.showCurativeAccessoryTip.get(); }
    public boolean shouldShowEffectSwordTip() { return (Boolean)this.showEffectSwordTip.get(); }
    public boolean shouldShowEffectBattleAxeTip() { return (Boolean)this.showEffectBattleAxeTip.get(); }
    public boolean shouldShowLifeStealingSwordTip() { return (Boolean)this.showLifeStealingSwordTip.get(); }
    public boolean shouldShowHammerTips() { return (Boolean)this.showHammerTips.get(); }
    public boolean shouldShowEffectHammerTips() { return (Boolean)this.showEffectHammerTips.get(); }



}
