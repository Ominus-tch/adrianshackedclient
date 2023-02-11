package adrian.hacks.fullbright;

import adrian.hacks.fullbright.config.FullbrightConfig;
import adrian.hacks.fullbright.statuseffect.StatusEffectManager;
import adrian.hacks.fullbright.util.InfoProvider;
import net.minecraft.client.MinecraftClient;

import java.util.Timer;
import java.util.TimerTask;

public class GammaOptions {
    private static final MinecraftClient client = MinecraftClient.getInstance();
    private static final FullbrightConfig config = GammaUtils.getConfig();
    private static Timer timer = null;

    private GammaOptions() {
    }

    public static void toggleGamma() {
        double value = client.options.getGamma().getValue();
        if (value == config.getDefaultGamma()) {
            value = config.getToggledGamma();
        }
        else {
            value = config.getDefaultGamma();
        }
        setGamma(value, true);
    }

    public static void increaseGamma(double value) {
        double newValue = client.options.getGamma().getValue();
        if (value == 0) {
            newValue += config.getGammaStep();
        }
        else {
            newValue += value;
        }
        setGamma(newValue, false);
    }

    public static void decreaseGamma(double value) {
        double newValue = client.options.getGamma().getValue();
        if (value == 0) {
            newValue -= config.getGammaStep();
        }
        else {
            newValue -= value;
        }
        setGamma(newValue, false);
    }

    public static void minGamma() {
        setGamma(config.getMinGamma(), true);
    }

    public static void maxGamma() {
        setGamma(config.getMaxGamma(), true);
    }

    public static void setGamma(double newValue, boolean smoothTransition) {
        if (timer != null) {
            timer.cancel();
        }

        if (config.getMaxGamma() > config.getMinGamma() && config.isLimitCheckEnabled()) {
            newValue = Math.max(config.getMinGamma(), Math.min(newValue, config.getMaxGamma()));
        }

        if (smoothTransition && config.isSmoothTransitionEnabled()) {
            double valueChangePerTick = config.getTransitionSpeed() / 100;
            if (newValue < client.options.getGamma().getValue()) {
                valueChangePerTick *= -1;
            }
            startTimer(newValue, valueChangePerTick);
        }
        else {
            client.options.getGamma().setValue(newValue);
            StatusEffectManager.updateGammaStatusEffect();
            InfoProvider.showGammaHudMessage();
        }

        if (config.isUpdateToggleEnabled() && newValue != config.getDefaultGamma() && newValue != config.getToggledGamma()) {
            config.setToggledGamma(newValue);
        }
    }

    private static void startTimer(double newValue, double valueChangePerTick) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                double nextValue = client.options.getGamma().getValue() + valueChangePerTick;
                if ((valueChangePerTick > 0 && nextValue >= newValue) ||
                        (valueChangePerTick < 0 && nextValue <= newValue)) {
                    timer.cancel();
                    client.options.getGamma().setValue(newValue);
                    StatusEffectManager.updateGammaStatusEffect();
                }
                else {
                    client.options.getGamma().setValue(nextValue);
                }
                InfoProvider.showGammaHudMessage();
            }
        }, 0, 10);
    }
}

