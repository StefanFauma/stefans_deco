package stefans_deco.stefans_deco.BlocksAndItems;

import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import stefans_deco.stefans_deco.Stefans_deco;

public class ModItems {

    // Item registry
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(Stefans_deco.MODID);

    // Example item
    public static final DeferredHolder<Item, Item> DECO =
            ITEMS.register("deco",
                    () -> new Item(new Item.Properties()));
    public static Holder<Item> globe;

    // Call from mod constructor
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
