package com.ds.projectdawn.init;

import com.ds.projectdawn.ProjectDawn;
import com.ds.projectdawn.effects.DrainedEffect;
import com.ds.projectdawn.effects.WoundingEffect;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionInit {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, ProjectDawn.MOD_ID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, ProjectDawn.MOD_ID);

    public static void init()
    {
        EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        POTIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }


    public static final RegistryObject<WoundingEffect> WOUNDING_EFFECT = PotionInit.EFFECTS.register("wounding_effect", WoundingEffect::new);
    public static final RegistryObject<DrainedEffect> DRAINED_EFFECT = PotionInit.EFFECTS.register("drained_effect", DrainedEffect::new);

    //Examples
   // public static final RegistryObject<Potion> WOUNDING_POTION = PotionList.POTIONS.register("wounding_potion",
     //       () -> new Potion(new EffectInstance(WOUNDING_EFFECT.get(), 1200, 0)));
   // public static final RegistryObject<Potion> LONG_WOUNDING_POTION = PotionList.POTIONS.register("long_wounding_potion",
     //       () -> new Potion(new EffectInstance(WOUNDING_EFFECT.get(), 2400, 0)));
   // public static final RegistryObject<Potion> STRONG_BLEEDING_POTION = PotionList.POTIONS.register("strong_wounding_potion",
     //       () -> new Potion(new EffectInstance(WOUNDING_EFFECT.get(), 600, 1)));


    public static void addPotionRecipes()
    {
        //Examples
        //BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(Potions.AWKWARD, ItemList.ARCANE_CRYSTAL_SHARD.get(), WOUNDING_POTION.get()));
        //BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(WOUNDING_POTION.get(), Items.REDSTONE, LONG_WOUNDING_POTION.get()));
        //BrewingRecipeRegistry.addRecipe(new BetterBrewingRecipe(WOUNDING_POTION.get(), Items.GLOWSTONE_DUST, STRONG_BLEEDING_POTION.get()));
    }


    private static class BetterBrewingRecipe implements IBrewingRecipe {
        private final Potion bottleInput;
        private final Item itemInput;
        private final ItemStack output;

        public BetterBrewingRecipe(Potion bottleInput, Item itemInput, Potion outputIn) {
            this.bottleInput = bottleInput;
            this.itemInput = itemInput;
            this.output = PotionUtils.setPotion(new ItemStack(Items.POTION), outputIn);


        }

        @Override
        public boolean isInput(ItemStack input) {
            return PotionUtils.getPotion(input).equals(this.bottleInput);
        }

        @Override
        public boolean isIngredient(ItemStack ingredient) {
            return ingredient.getItem().equals(this.itemInput);
        }

        @Override
        public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
            if (isInput(input) && isIngredient(ingredient)) {
                return this.output.copy();
            } else {
                return ItemStack.EMPTY;
            }
        }
    }


}
