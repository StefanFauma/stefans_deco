package stefans_deco.stefans_deco.BlocksAndItems;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import stefans_deco.stefans_deco.Stefans_deco;

import java.util.function.Supplier;

public class CreativeDecoTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Stefans_deco.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DECO_ITEMS_TAB =
            CREATIVE_MODE_TAB.register("deco_items", () ->
                    CreativeModeTab.builder()
                            .title(Component.translatable("creativetab.stefans_deco.BlocksAndItems"))
                            .displayItems((params, output) -> {
                                // Hier deine Items hinzufügen, z.B.:
                                // output.accept(ModItems.DEIN_ITEM.get());
                            })
                            .build()
            );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }}
