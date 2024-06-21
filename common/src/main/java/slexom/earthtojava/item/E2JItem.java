package slexom.earthtojava.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import slexom.earthtojava.utils.Utils;

import java.util.List;

public class E2JItem extends Item {
    public E2JItem(Properties properties) {
        super(properties);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        String translationKey = getDescriptionId() + ".desc";
        Utils.appendE2JTooltip(translationKey, list);
    }

}
