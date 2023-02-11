package adrian.hacks.fullbright;

import adrian.hacks.fullbright.config.FullbrightConfig;
import adrian.hacks.fullbright.statuseffect.*;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GammaUtils implements ClientModInitializer {
    public static final StatusEffect BRIGHT = new BrightStatusEffect();
    public static final StatusEffect DIM = new DimStatusEffect();

    public static final Logger LOGGER = LoggerFactory.getLogger("Gamma Utils");
    private static ConfigHolder<FullbrightConfig> configHolder;

    public static FullbrightConfig getConfig() {
        return configHolder.getConfig();
    }

    @Override
    public void onInitializeClient() {
        configHolder = AutoConfig.register(FullbrightConfig.class, JanksonConfigSerializer::new);
        configHolder.registerSaveListener((manager, data) -> {
            StatusEffectManager.updateGammaStatusEffect();
            return ActionResult.SUCCESS;
        });

        KeyBindings.registerBindings();
        Commands.registerCommands();

        Registry.register(Registries.STATUS_EFFECT, new Identifier("gammautils", "bright"), BRIGHT);
        Registry.register(Registries.STATUS_EFFECT, new Identifier("gammautils", "dim"), DIM);
    }
}

