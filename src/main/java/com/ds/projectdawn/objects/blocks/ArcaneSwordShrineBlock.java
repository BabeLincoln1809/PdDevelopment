package com.ds.projectdawn.objects.blocks;


import com.ds.projectdawn.init.ParticleInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ArcaneSwordShrineBlock extends Block implements IWaterLoggable
{
    protected static final Map<Block, Map<Direction, VoxelShape>> SHAPES = new HashMap<Block, Map<Direction, VoxelShape>>();
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty HORIZONTAL_FACING = BlockStateProperties.HORIZONTAL_FACING;
    protected static final VoxelShape SMALL_ROCK_X = Block.box(2.0D, 0.0D, 3.0D, 8.0D, 1.0D, 9.0D);
    protected static final VoxelShape MEDIUM_ROCK_X = Block.box(8.0D, 0.0D, 8.0D, 14.0D, 2.0D, 14.0D);
    protected static final VoxelShape BASE_ROCK_ONE_X = Block.box(3.0D, 0.0D, 4.0D, 13.0D, 3.0D, 12.0D);
    protected static final VoxelShape BASE_ROCK_TWO_X = Block.box(5.0D, 1.0D, 5.0D, 12.0D, 5.0D, 11.0D);
    protected static final VoxelShape SWORD_ONE_X = Block.box(10.5D, 5.0D, 9.0D, 6.5D, 17.0D, 7.0D);
    protected static final VoxelShape SWORD_TWO_X = Block.box(10.5D, 17.0D, 9.0D, 6.5D, 24.0D, 7.0D);
    protected static final VoxelShape SMALL_ROCK_Z = Block.box(7.0D, 0.0D, 2.0D, 13.0D, 1.0D, 8.0D);
    protected static final VoxelShape MEDIUM_ROCK_Z = Block.box(2.0D, 0.0D, 8.0D, 8.0D, 2.0D, 14.0D);
    protected static final VoxelShape BASE_ROCK_ONE_Z = Block.box(4.0D, 0.0D, 3.0D, 12.0D, 3.0D, 13.0D);
    protected static final VoxelShape BASE_ROCK_TWO_Z = Block.box(5.0D, 1.0D, 5.0D, 11.0D, 5.0D, 12.0D);
    protected static final VoxelShape SWORD_ONE_Z = Block.box(9.0D, 5.0D, 10.5D, 7.0D, 17.0D, 6.5D);
    protected static final VoxelShape SWORD_TWO_Z = Block.box(9.0D, 17.0D, 10.5D, 7.0D, 24.0D, 6.5D);
    private static final VoxelShape X_BUILD = VoxelShapes.or(SMALL_ROCK_X, MEDIUM_ROCK_X, BASE_ROCK_ONE_X, BASE_ROCK_TWO_X, SWORD_ONE_X, SWORD_TWO_X);
    private static final VoxelShape Z_BUILD = VoxelShapes.or(SMALL_ROCK_Z, MEDIUM_ROCK_Z, BASE_ROCK_ONE_Z, BASE_ROCK_TWO_Z, SWORD_ONE_Z, SWORD_TWO_Z);

    public ArcaneSwordShrineBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.valueOf(false)));

    }
    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        Random rand = new Random();
        return MathHelper.nextInt(rand, 3, 7);
    }

    @Override
    public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
        return state.setValue(HORIZONTAL_FACING, direction.rotate(state.getValue(HORIZONTAL_FACING)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(HORIZONTAL_FACING)));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        boolean flag = fluidstate.getType() == Fluids.WATER;

        super.getStateForPlacement(context).setValue(WATERLOGGED, Boolean.valueOf(flag));
        return this.defaultBlockState().setValue(HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
    }
    @SuppressWarnings("deprecation")
    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (!stateIn.canSurvive(worldIn, currentPos)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            if (stateIn.getValue(WATERLOGGED)) {
                worldIn.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
            }

            return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
    }
    @SuppressWarnings("deprecation")
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }
    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING, WATERLOGGED);
    }
    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Direction direction = state.getValue(HORIZONTAL_FACING);
        return direction.getAxis() == Direction.Axis.X ? Z_BUILD : X_BUILD;
    }
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        Direction direction = stateIn.getValue(HORIZONTAL_FACING);
        double d0 = (double) pos.getX() + 0.55D - (double) (rand.nextFloat() * 0.2F);
        double d1 = (double) pos.getY() + 1.55D - (double) (rand.nextFloat() * 1.25F);
        double d2 = (double) pos.getZ() + 0.55D - (double) (rand.nextFloat() * 0.2F);
        double d3 = (double) (0.4F - (rand.nextFloat() + rand.nextFloat()) * 0.4F);
        if (rand.nextInt(5) == 0)
            worldIn.addParticle(ParticleInit.EMERALD_GLINT.get(), d0 + (double) direction.getStepX() * d3, d1 + (double) direction.getStepY() * d3, d2 + (double) direction.getStepZ() * d3, rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D, rand.nextGaussian() * 0.005D);
    }
    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) { return 3; }
    @SuppressWarnings("deprecation")
    @Override
    public PushReaction getPistonPushReaction(BlockState p_149656_1_) {
        return PushReaction.IGNORE;
    }
    @SuppressWarnings("deprecation")
    @Override
    public boolean isPathfindable(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
        return false;
    }
}
