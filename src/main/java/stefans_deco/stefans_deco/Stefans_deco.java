package stefans_deco.stefans_deco;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;
//import stefans_deco.stefans_deco.BlocksAndItems.CreativeDecoTab;
import stefans_deco.stefans_deco.BlocksAndItems.globe;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Stefans_deco.MODID)
public class Stefans_deco {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "stefans_deco";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "stefans_deco" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "stefans_deco" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "stefans_deco" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DECO_TAB =
            CREATIVE_MODE_TABS.register("stefans_tab", () ->
                            CreativeModeTab.builder()
                                    .title(Component.translatable("creativetab.stefans_deco"))
                                    .icon(() -> new ItemStack(globe.GLOBE_ITEM.get()))
                                    .displayItems((parameters, output) -> {
                                        output.accept(globe.GLOBE_ITEM.get());
                                    })
                                    .build()
            );
    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Stefans_deco(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
