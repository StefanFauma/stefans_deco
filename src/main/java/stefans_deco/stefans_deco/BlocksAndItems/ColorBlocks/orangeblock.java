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

@EventBusSubscriber(modid = Stefans_deco.MODID, bus = EventBusSubscriber.Bus.MOD)
public class orangeblock {

    //
    // Block
    //
    public static final DeferredBlock<Block> ORANGEBLOCK =
            Stefans_deco.BLOCKS.register(
                    "orangeblock",
                    () -> new Block(
                            BlockBehaviour.Properties.of()
                                    .strength(0.1f, 1f)
                    )
            );

    //
    // BlockItem
    //
    public static final DeferredItem<BlockItem> ORANGEBLOCK_ITEM =
            Stefans_deco.ITEMS.register(
                    "orangeblock",
                    () -> new BlockItem(
                            ORANGEBLOCK.get(),
                            new Item.Properties()
                    )
            );

    //
    // Creative Tab
    //
    @SubscribeEvent
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ORANGEBLOCK_ITEM);
        }
    }
}
