package adrian.hacks.fullbright.util;

import adrian.hacks.fullbright.config.FullbrightConfig;
import adrian.hacks.fullbright.GammaUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static net.minecraft.text.Style.EMPTY;

public final class InfoProvider {
    private static final MinecraftClient client = MinecraftClient.getInstance();
    private static final FullbrightConfig config = GammaUtils.getConfig();

    private InfoProvider() {
    }

    public static void showGammaHudMessage() {
        if (!config.isGammaMessageEnabled()) {
            return;
        }

        int gamma = (int)Math.round(client.options.getGamma().getValue() * 100);
        MutableText message = Text.translatable("text.gamma_utils.message.gamma", gamma);

        Formatting format;
        if (gamma < 0) {
            format = Formatting.DARK_RED;
        }
        else if (gamma > 100) {
            format = Formatting.GOLD;
        }
        else {
            format = Formatting.DARK_GREEN;
        }

        message.setStyle(EMPTY.withColor(format));
        client.inGameHud.setOverlayMessage(message, false);
    }

    public static void showNightVisionHudMessage(boolean enabled) {
        if (!config.isGammaMessageEnabled()) {
            return;
        }

        MutableText message;
        if (enabled) {
            message = Text.translatable("text.gamma_utils.message.nightvision.enabled");
            message.setStyle(EMPTY.withColor(Formatting.DARK_GREEN));
        }
        else {
            message = Text.translatable("text.gamma_utils.message.nightvision.disabled");
            message.setStyle(EMPTY.withColor(Formatting.DARK_RED));
        }

        client.inGameHud.setOverlayMessage(message, false);
    }
}

