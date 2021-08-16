package com.ds.projectdawn.init;

import com.ds.projectdawn.ProjectDawn;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityInit
{
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ProjectDawn.MOD_ID);

    public static void init() { TILE_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus()); }

   //public static final RegistryObject<TileEntityType<SharpeningStationTileEntity>> SHARPENING_STATION = TILE_ENTITIES.register("sharpening_station", () ->
     //       TileEntityType.Builder.create(SharpeningStationTileEntity::new, BlockList.SHARPENING_STATION.get()).build(null));


}
