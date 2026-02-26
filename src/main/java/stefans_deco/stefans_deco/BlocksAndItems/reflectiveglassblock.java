package stefans_deco.stefans_deco.BlocksAndItems;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import stefans_deco.stefans_deco.Stefans_deco;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.Blocks;


@EventBusSubscriber(modid = Stefans_deco.MODID, bus = EventBusSubscriber.Bus.MOD)
public class reflectiveglassblock {

    //
    // Block
    //
    public static final DeferredBlock<Block> REFLECTIVEGLASS =
            Stefans_deco.BLOCKS.register(
                    "reflectiveglassblock",
                    () -> new Block(
                            BlockBehaviour.Properties.of()
                                    .strength(0.1f, 1f)
                                    .noOcclusion()
                    ) {
                        @Override
                        public RenderShape getRenderShape(BlockState state) {
                            return RenderShape.INVISIBLE;
                        }
                    }
            );

    //
    // BlockItem
    //
    public static final DeferredItem<BlockItem> REFLECTIVEGLASSBLOCK_ITEM =
            Stefans_deco.ITEMS.register(
                    "reflectiveglassblock",
                    () -> new BlockItem(
                            REFLECTIVEGLASS.get(),
                            new Item.Properties()
                    )
            );

    //
    // Creative Tab
    //
    @SubscribeEvent
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(REFLECTIVEGLASSBLOCK_ITEM);
        }
    }
}