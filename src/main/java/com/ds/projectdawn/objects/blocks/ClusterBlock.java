package com.ds.projectdawn.objects.blocks;

import com.ds.projectdawn.init.BlockInit;
import com.ds.projectdawn.init.ParticleInit;
import net.minecraft.block.*;
import net.minecraft.block.material.PushReaction;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.IParticleData;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class ClusterBlock extends DirectionalBlock implements IWaterLoggable
{
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected static final VoxelShape CLUSTER_UP = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 5.0D, 14.0D);
    protected static final VoxelShape CLUSTER_DOWN = Block.box(2.0D, 11.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    protected static final VoxelShape CLUSTER_SOUTH = Block.box(2.0D, 2.0D, 0.0D, 14.0D, 14.0D, 5.0D);
    protected static final VoxelShape CLUSTER_NORTH = Block.box(2.0D, 2.0D, 11.0D, 14.0D, 14.0D, 16.0D);
    protected static final VoxelShape CLUSTER_EAST = Block.box(0.0D, 2.0D, 2.0D, 5.0D, 14.0D, 14.0D);
    protected static final VoxelShape CLUSTER_WEST = Block.box(11.0D, 2.0D, 2.0D, 16.0D, 14.0D, 14.0D);

    private final int min;
    private final int max;

    public ClusterBlock(int minIn, int maxIn, AbstractBlock.Properties builder) {
        super(builder);
        this.min = minIn;
        this.max = maxIn;
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP).setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        Random rand = new Random();
        return MathHelper.nextInt(rand, this.min, this.max);
    }
    @SuppressWarnings("deprecation")
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }
    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.setValue(FACING, mirrorIn.mirror(state.getValue(FACING)));
    }
    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch((Direction)state.getValue(FACING)) {
            case NORTH:
                return CLUSTER_NORTH;
            case SOUTH:
                return CLUSTER_SOUTH;
            case EAST:
                return CLUSTER_EAST;
            case WEST:
                return CLUSTER_WEST;
            case DOWN:
                return CLUSTER_DOWN;
            case UP:
            default:
                return CLUSTER_UP;
        }
    }
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction direction = context.getClickedFace();
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().offset(direction.getNormal()));
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        boolean flag = fluidstate.getType() == Fluids.WATER;

        super.getStateForPlacement(context).setValue(WATERLOGGED, Boolean.valueOf(flag));
        return blockstate.is(this) && blockstate.getValue(FACING) == direction ? this.defaultBlockState().setValue(FACING, direction.getOpposite()) : this.defaultBlockState().setValue(FACING, direction);
    }
    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 7;
    }
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        if(state.getBlock() == Blocks.AIR)
            return false;
        else
            return true;
    }
    protected boolean mayPlaceOn(BlockState p_200014_1_, IBlockReader p_200014_2_, BlockPos p_200014_3_) {
        return !p_200014_1_.getCollisionShape(p_200014_2_, p_200014_3_).getFaceShape(Direction.UP).isEmpty() || p_200014_1_.isFaceSturdy(p_200014_2_, p_200014_3_, Direction.UP);
    }
    @SuppressWarnings("deprecation")
    @Override
    public boolean canSurvive(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_) {
        BlockPos blockpos = p_196260_3_.below();
        return this.mayPlaceOn(p_196260_2_.getBlockState(blockpos), p_196260_2_, blockpos);
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
    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

}