package slexom.earthtojava.utils;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import slexom.earthtojava.Earth2JavaMod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<String> breakItemTooltip(String input) {
        List<String> cjkLocales = Arrays.asList("ja_jp", "ko_kr", "zh_cn", "zh_tw");
        String currentLocale = Minecraft.getInstance().getLanguageManager().getSelected();
        if (cjkLocales.contains(currentLocale)) {
            return breakLine(input, 30);
        }
        return breakLine(input, 40);
    }

    public static List<String> breakLine(String input, int maxLineLength) {
        List<String> res = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\b.{1," + (maxLineLength - 1) + "}\\b\\W?", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            res.add(matcher.group());
        }
        return res;
    }

    @Environment(EnvType.CLIENT)
    public static void appendE2JTooltip(String translationKey, List<Component> tooltip) {
        if (!I18n.exists(translationKey)) return;

        MutableComponent description = Component.translatable(translationKey);
        List<String> strings = Utils.breakItemTooltip(description.getString());
        strings.forEach(string -> tooltip.add(Component.translatable(string).withStyle(ChatFormatting.GRAY)));
    }

    public static ResourceLocation modResourceLocationOf(String registryName) {
        return ResourceLocation.fromNamespaceAndPath(Earth2JavaMod.MOD_ID, registryName);
    }

    public static ResourceKey<LootTable> lootTableOf(String registryName) {
        return ResourceKey.create(Registries.LOOT_TABLE, Utils.modResourceLocationOf(registryName));

    }
}
