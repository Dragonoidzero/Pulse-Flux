package azzy.fabric.forgottenfruits.block;


import alexiil.mc.lib.attributes.AttributeList;
import alexiil.mc.lib.attributes.AttributeProvider;
import azzy.fabric.forgottenfruits.staticentities.blockentity.MachineEntity;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class BaseMachine extends HorizontalFacingBlock implements BlockEntityProvider, AttributeProvider {

        protected VoxelShape bounds;
        protected ParticleEffect[] effects;
        public static final VoxelShape TOPDOWN = VoxelShapes.cuboid(1, 0, 1, 15, 16, 15);
        public static final VoxelShape SIDES = VoxelShapes.cuboid(0, 1, 0, 16, 15, 16);

    public BaseMachine(Settings settings, Material material, BlockSoundGroup sound, int glow, VoxelShape bounds, ParticleEffect ... effects) {
        super(FabricBlockSettings.of(material).breakByTool(FabricToolTags.AXES, 1).sounds(sound).nonOpaque().hardness(0.4f).lightLevel(glow).build());
        this.bounds = bounds;
        this.effects = effects;
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    //Note: does not drop items when broken
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof MachineEntity) {
                // update comparators
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context){
        return bounds;
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return bounds;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return null;
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    @Override
    public void addAllAttributes(World world, BlockPos blockPos, BlockState blockState, AttributeList<?> attributeList) {
    }
}