package com.joyeuxlib.core.blocks;

import com.joyeuxlib.core.JoyeuxLibBlocks;
import com.joyeuxlib.core.JoyeuxLibSounds;
import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;


public class BoxBlock extends HorizontalFacingBlock {
    protected static final VoxelShape SHAPE = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 8.0, 12.0);

    public BoxBlock(Settings settings) {
        super(settings);
    }

    public ActionResult onUse(BlockState state, @NotNull World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {

            var mid = Vec3d.ofCenter(pos);
            var pitch = 0.8f + world.random.nextFloat() * 0.4f;
            var note = world.getBlockState(pos.down());
            if (note.contains(Properties.NOTE)) {
                pitch = (float) Math.pow(2.0, (double) (note.get(Properties.NOTE) - 12) / 12.0);
            }

            world.playSound(null, mid.getX(), mid.getY(), mid.getZ(), BoxBlock.getSound(state), SoundCategory.BLOCKS, 1.0f, 1.0f);

        }
        return ActionResult.SUCCESS;
    }

    public static SoundEvent getSound(BlockState state) {
        SoundEvent ret = SoundEvents.BLOCK_WOOL_HIT;;
        if (state.getBlock() == JoyeuxLibBlocks.JOY_BLOCK) {
            ret = JoyeuxLibSounds.BOOWOMP;
        }
        if (state.getBlock() == JoyeuxLibBlocks.KOY_BLOCK) {
            ret = JoyeuxLibSounds.BOOWOMP_BUTEVIL;
        }
        return ret;
    }


    @Override
    public boolean isShapeFullCube(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing());
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING});
    }

}

