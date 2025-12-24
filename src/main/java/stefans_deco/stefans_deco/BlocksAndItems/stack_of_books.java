package stefans_deco.stefans_deco.BlocksAndItems;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import stefans_deco.stefans_deco.Stefans_deco;

@EventBusSubscriber(modid = Stefans_deco.MODID, bus = EventBusSubscriber.Bus.MOD)
public class stack_of_books {

    //
    // Block
    //
    public static final DeferredBlock<Block> STACK_OF_BOOKS =
            Stefans_deco.BLOCKS.register(
                    "stack_of_books",
                    () -> new StackOfBooksExtras(
                            BlockBehaviour.Properties.of()
                                    .strength(0.5f, 0.5f)
                                    .noOcclusion()
                    )
            );

    //
    // BlockItem
    //
    public static final DeferredItem<BlockItem> STACK_OF_BOOKS_ITEM =
            Stefans_deco.ITEMS.register(
                    "stack_of_books",
                    () -> new BlockItem(
                            STACK_OF_BOOKS.get(),
                            new Item.Properties()
                    )
            );

    //
    // Creative Tab
    //
    @SubscribeEvent
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(STACK_OF_BOOKS_ITEM);
        }
    }

    //
    // Extrafunktionen
    //
    public static class StackOfBooksExtras extends Block {

        // Rotation
        public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

        // Standard-Hitbox (für NORTH)
        private static final VoxelShape HITBOX = Block.box(1, 0, 1, 15, 12, 15);

        // Standard-Collisionbox (für NORTH)
        private static final VoxelShape COLLISION = Block.box(1, 0, 1, 15, 12, 15);

        //Alles für Rotaion
        public StackOfBooksExtras(Properties props) {
            super(props);
            this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
        }

        @Override
        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
            builder.add(FACING);
        }

        @Override
        public BlockState getStateForPlacement(BlockPlaceContext context) {
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
        }

        @Override
        public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
            return rotateShape(HITBOX, state.getValue(FACING));
        }

        @Override
        public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
            return rotateShape(COLLISION, state.getValue(FACING));
        }

        //Block kann nur auf vollen Blöcken plaziert werden
        @Override
        public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
            BlockPos below = pos.below();
            BlockState belowState = level.getBlockState(below);
            return belowState.isCollisionShapeFullBlock(level, below);
        }

        //Block geht kaputt, wenn der Block unter ihm abgebaut wird
        @Override
        public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level,
                                      BlockPos pos, BlockPos neighborPos) {
            if (!canSurvive(state, level, pos)) {
                return Blocks.AIR.defaultBlockState();
            }
            return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
        }

        // Hilfsmethode zum Rotieren der Hitbox/Collisionbox je nach Block-Richtung
        private static VoxelShape rotateShape(VoxelShape shape, Direction facing) {
            return switch (facing) {
                case NORTH, SOUTH -> shape;
                case EAST -> Block.box(1, 0, 1, 15, 12, 15);   // 90° Drehung
                case WEST -> Block.box(1, 0, 1, 15, 12, 15);   // 270° Drehung
                default -> shape;
            };
        }
    }
}
