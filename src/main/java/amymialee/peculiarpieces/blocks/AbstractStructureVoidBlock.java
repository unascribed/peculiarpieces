package amymialee.peculiarpieces.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public abstract class AbstractStructureVoidBlock extends Block {
    private static final VoxelShape SHAPE = Block.createCuboidShape(3.0D, 3.0D, 3.0D, 13.0D, 13.0D, 13.0D);

    public AbstractStructureVoidBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}