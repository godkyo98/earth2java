package slexom.earthtojava;

import com.google.common.base.Suppliers;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.RegistrySupplier;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slexom.earthtojava.config.ModConfig;
import slexom.earthtojava.events.ModEvents;
import slexom.earthtojava.init.*;

import java.util.function.Supplier;

public class Earth2JavaMod {

    public static final String MOD_ID = "earthtojavamobs";
    public static final Supplier<RegistrarManager> REGISTRIES = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

    public static final Registrar<BlockEntityType<?>> BLOCK_ENTITY_TYPE_REGISTRAR = REGISTRIES.get().get(Registries.BLOCK_ENTITY_TYPE);
    public static final Registrar<Block> BLOCK_REGISTRAR = REGISTRIES.get().get(Registries.BLOCK);
    public static final Registrar<EntityType<?>> ENTITY_TYPE_REGISTRAR = REGISTRIES.get().get(Registries.ENTITY_TYPE);
    public static final Registrar<Item> ITEM_REGISTRAR = REGISTRIES.get().get(Registries.ITEM);
    public static final Registrar<SoundEvent> SOUND_EVENT_REGISTRAR = REGISTRIES.get().get(Registries.SOUND_EVENT);
    public static final Registrar<CreativeModeTab> TABS = REGISTRIES.get().get(Registries.CREATIVE_MODE_TAB);

    public static final ResourceLocation ITEM_GROUP_IDENTIFIER = ResourceLocation.fromNamespaceAndPath(MOD_ID, "group");


    public static final RegistrySupplier<CreativeModeTab> CREATIVE_TAB_SUPPLIER = TABS.register(
            ITEM_GROUP_IDENTIFIER, // Tab ID
            () -> CreativeTabRegistry.create(
                    Component.translatable("itemGroup.earthtojavamobs.group"), // Tab Name
                    () -> new ItemStack(ItemInit.HORN.get()) // Icon
            )
    );

    private static final Logger LOGGER = LogManager.getLogger("Earth2Java");

    public static void initialize() {
        ModTags.init();
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        ModEvents.init();
        SoundEventsInit.init();
        BlockInit.init();
        BiomeInit.init();
        EntityTypesInit.init();
        EntityAttributeInit.init();
        ItemInit.init();
        BlockEntityTypeInit.init();
        LOGGER.info("[Earth2Java] Mod loaded! Enjoy :D");
    }

    public static void onPostInit() {
        BlockInit.onPostInit();
        EntitySpawnInit.init();
    }

}
