package stefans_deco.stefans_deco.BlocksAndItems;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import stefans_deco.stefans_deco.Stefans_deco;

@EventBusSubscriber(modid = Stefans_deco.MODID, bus = EventBusSubscriber.Bus.MOD)
public class mirror {

    // Block
    public static final DeferredBlock<Block> MIRROR =
            Stefans_deco.BLOCKS.registerSimpleBlock(
                    "mirror",
                    BlockBehaviour.Properties.of()
                            .strength(1.5f, 6.0f)
                            .requiresCorrectToolForDrops()
                            .noOcclusion()
            );

    // BlockItem
    public static final DeferredItem<BlockItem> MIRROR_ITEM =
            Stefans_deco.ITEMS.register(
                    "mirror",
                    () -> new BlockItem(
                            MIRROR.get(),
                            new Item.Properties()
                    )
            );

    // Creative Tab
    @SubscribeEvent
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(MIRROR_ITEM);
        }
    }

    // Eigene Blockklasse direkt in der Klasse
    public static class MirrorFeatures extends Block {
        private static final VoxelShape SHAPE = Block.box(2, 0, 2, 14, 16, 14); // hier deine gewünschte Hitbox

        public MirrorFeatures(Properties props) {
            super(props);
        }

        @Override
        public VoxelShape getCollisionShape(
                BlockState state,
                BlockGetter level,
                BlockPos pos,
                CollisionContext context
        ) {
            return SHAPE;
        }
    }
}
