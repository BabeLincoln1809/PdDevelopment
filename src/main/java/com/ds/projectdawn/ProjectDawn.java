package com.ds.projectdawn;

import com.ds.projectdawn.capabilites.CoolDownCapability;
import com.ds.projectdawn.config.ToolTipConfiguration;
import com.ds.projectdawn.events.*;
import com.ds.projectdawn.events.accessoryevents.*;
import com.ds.projectdawn.handlers.TooltipHandler;
import com.ds.projectdawn.loot.LootRegistry;
import com.ds.projectdawn.init.*;
import com.ds.projectdawn.message.LeftSwingMessage;
import com.ds.projectdawn.objects.entities.projectiles.*;
import com.ds.projectdawn.world.gen.FeatureGeneration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;


@Mod("projectdawn")
@Mod.EventBusSubscriber(modid = ProjectDawn.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ProjectDawn
{
    private final ToolTipConfiguration toolTipConfig = new ToolTipConfiguration();

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "projectdawn";

    public static final SimpleChannel INSTANCE;
    private static final String PROTOCOL_VERSION = Integer.toString(1);
    private static int packetsRegistered = 0;

    public ProjectDawn() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        ItemInit.ITEMS.register(modEventBus);
        ParticleInit.PARTICLES.register(modEventBus);
        EntityTypeInit.init();
        BlockInit.init();
        PotionInit.init();
        TileEntityInit.init();
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, FeatureGeneration::generateOverworldFeatures);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, FeatureGeneration::generateRubyOre);


        MinecraftForge.EVENT_BUS.register(this);
    }

    public static <MSG> void sendMSGToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        ItemInit.putItemsInMap();

        CoolDownCapability.register();

        INSTANCE.registerMessage(packetsRegistered++, LeftSwingMessage.class, LeftSwingMessage::write, LeftSwingMessage::read, com.ds.projectdawn.message.LeftSwingMessage.Handler::handle);
        MinecraftForge.EVENT_BUS.register(new TooltipHandler());
        MinecraftForge.EVENT_BUS.register(new MobSpawnEvent());
        MinecraftForge.EVENT_BUS.register(new LootRegistry());
        MinecraftForge.EVENT_BUS.register(new EmberRuneEvent());
        MinecraftForge.EVENT_BUS.register(new UnstableCrystalEvent());
        MinecraftForge.EVENT_BUS.register(new RavagerHornEvent());
        MinecraftForge.EVENT_BUS.register(new MagicGauntletEvent());
        MinecraftForge.EVENT_BUS.register(new FireGauntletEvent());
        MinecraftForge.EVENT_BUS.register(new MagmaCharmEvent());
        MinecraftForge.EVENT_BUS.register(new NetherRoseEvent());
        PotionInit.addPotionRecipes();

    }
    private void doClientStuff(final FMLClientSetupEvent event)
    {
        registerEntityModels(event.getMinecraftSupplier());
        registerPropertyOverrides();
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, this.toolTipConfig.getSpec());
    }
    @OnlyIn(Dist.CLIENT)
    private void registerPropertyOverrides()
    {
        //this probs isn't the most efficient way of doing it, but it'll work for now
        ItemModelsProperties.register(ItemInit.BOLTCASTER.get(), new ResourceLocation("loaded"), (stack, world, player) -> {
            if(player != null)
                return player.isUsingItem() && player.getUseItem() == stack ? 1.0F : player.getProjectile(stack).getCount();
            else
                return 0.0F;
        });
        ItemModelsProperties.register(ItemInit.SPELLBOUND_BOLTCASTER.get(), new ResourceLocation("loaded"), (stack, world, player) -> {
            if(player != null)
                return player.isUsingItem() && player.getUseItem() == stack ? 1.0F : player.getProjectile(stack).getCount();
            else
                return 0.0F;
        });
        ItemModelsProperties.register(ItemInit.WINDPIERCER.get(), new ResourceLocation("loaded"), (stack, world, player) -> {
            if(player != null)
                return player.isUsingItem() && player.getUseItem() == stack ? 1.0F : player.getProjectile(stack).getCount();
            else
                return 0.0F;
        });
        ItemModelsProperties.register(ItemInit.NETHERITE_SHIELD.get(), new ResourceLocation("blocking"), (stack, world, player) -> {
            return player != null && player.isUsingItem() && player.getUseItem() == stack ? 1.0F : 0.0F;
        });
        ItemModelsProperties.register(ItemInit.ARCANE_SWORD.get(), new ResourceLocation("full"), (stack, world, player) -> {
            if(player != null) {
                PlayerEntity playerPlayer = (PlayerEntity)player;
                return playerPlayer.isCreative() || player.isUsingItem() && player.getUseItem() == stack ? 1.0F : playerPlayer.totalExperience;
            }
            else
                return 0.0F;
        });
        ItemModelsProperties.register(ItemInit.FIREBRAND_BOW.get(), new ResourceLocation("pull"), (stack, world, player) -> {
            if (player == null) {
                return 0.0F;
            } else {
                return player.getUseItem() != stack ? 0.0F : (float)(stack.getUseDuration() - player.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        ItemModelsProperties.register(ItemInit.FIREBRAND_BOW.get(), new ResourceLocation("pulling"), (stack, world, player) -> {
            return player != null && player.isUsingItem() && player.getUseItem() == stack ? 1.0F : 0.0F;
        });
        ItemModelsProperties.register(ItemInit.FROSTBRAND_BOW.get(), new ResourceLocation("pull"), (stack, world, player) -> {
            if (player == null) {
                return 0.0F;
            } else {
                return player.getUseItem() != stack ? 0.0F : (float)(stack.getUseDuration() - player.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        ItemModelsProperties.register(ItemInit.FROSTBRAND_BOW.get(), new ResourceLocation("pulling"), (stack, world, player) -> {
            return player != null && player.isUsingItem() && player.getUseItem() == stack ? 1.0F : 0.0F;
        });
    }

    @OnlyIn(Dist.CLIENT)
    private void registerEntityModels(Supplier<Minecraft> minecraft)
    {
        ItemRenderer renderer = minecraft.get().getItemRenderer();
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.BLAZE_BOMB.get(), (rendererManager) ->
                new SpriteRenderer<>(rendererManager, renderer));
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.POWPOW_BOMB.get(), (rendererManager) ->
                new SpriteRenderer<>(rendererManager, renderer));

        RenderTypeLookup.setRenderLayer(BlockInit.ARCANE_CLUSTER.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BlockInit.RADIANT_CLUSTER.get(), RenderType.cutout());
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.ARCANE_BOLT.get(), ArcaneBoltRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.AMETHYST_BOLT.get(), AmethystBoltRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.EMERALD_BOLT.get(), EmeraldBoltRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.RUBY_BOLT.get(), RubyBoltRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.DIAMOND_BOLT.get(), DiamondBoltRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.RADIANT_BOLT.get(), RadiantBoltRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.FIRE_BALL.get(), FireBallRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.FROSTSPARK.get(), FrostSparkRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.INFERNO_BALL.get(), InfernoBallRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.SOULCHARGE.get(), SoulChargeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.VOIDCHARGE.get(), VoidChargeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.ARCANE_SLASH.get(), ArcaneSlashRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.MAGIC_NOTE.get(), MagicNoteRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.END_SLASH.get(), EndSlashRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.TEST.get(), ZombieRenderer::new);

    }

    public static final ItemGroup PROJECTDAWN_WEAPONS_TAB = new ItemGroup("projectdawn_weapons_tab")
    {
        @Override
        public ItemStack makeIcon(){
            return new ItemStack(ItemInit.AXEBLADE.get());
        }
    };

    public static final ItemGroup PROJECTDAWN_TOOLS_TAB = new ItemGroup("projectdawn_tools_tab")
    {
        @Override
        public ItemStack makeIcon(){
            return new ItemStack(ItemInit.FROSTBRAND_PICKAXE.get());
        }
    };

    public static final ItemGroup PROJECTDAWN_BLOCKS_TAB = new ItemGroup("projectdawn_blocks_tab")
    {
        @Override
        public ItemStack makeIcon(){
            return new ItemStack(BlockInit.ARCANE_CLUSTER.get());
        }
    };

    public static final ItemGroup PROJECTDAWN_ITEMS_TAB = new ItemGroup("projectdawn_items_tab")
    {
        @Override
        public ItemStack makeIcon(){ return new ItemStack(ItemInit.FIREBRAND_INGOT.get()); }
    };

    static
    {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation("mymodid", "main"),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals
        );
    }

}
