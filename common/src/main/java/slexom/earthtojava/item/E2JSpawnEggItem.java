package slexom.earthtojava.item;

import dev.architectury.core.item.ArchitecturySpawnEggItem;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import slexom.earthtojava.utils.Utils;

import java.util.List;

public class E2JSpawnEggItem extends ArchitecturySpawnEggItem {

    public E2JSpawnEggItem(RegistrySupplier<? extends EntityType<? extends Mob>> entityType, int primaryColor, int secondaryColor, Properties properties) {
        super(entityType, primaryColor, secondaryColor, properties);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        String translationKey = getDescriptionId() + ".desc";
        Utils.appendE2JTooltip(translationKey, list);
    }

}