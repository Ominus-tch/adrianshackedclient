package adrian.hacks.gui;

import adrian.hacks.AdriansMod.FabricModAdriansMod;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import adrian.hacks.AdriansMod.config.Config;

import java.util.function.Function;

public class AdriansModsScreenBuilder {

    private static final Function<Boolean, Text> yesNoTextSupplier = bool -> {
        if (bool) return Text.translatable("options.hackbutton.toggle.on");
        else return Text.translatable("options.hackbutton.toggle.off");
    };

    public static Screen buildScreen(FabricModAdriansMod modHacks, Screen parentScreen) {

        Config defaults = new Config();
        Config config = modHacks.getConfig();

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parentScreen)
                .setTitle(Text.translatable("options.autofish.title"))
                .transparentBackground()
                .setDoesConfirmSave(true)
                .setSavingRunnable(() -> {
                    modHacks.getConfig().enforceConstraints();
                    modHacks.getConfigManager().writeConfig(true);
                });

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ConfigCategory configCat = builder.getOrCreateCategory(Text.translatable("options.autofish.config"));

        //Enable Flight Hack
        AbstractConfigListEntry toggleFlightHack = entryBuilder.startBooleanToggle(Text.translatable("options.flight.enable.title"), config.isFlightHackEnabled())
                .setDefaultValue(defaults.isFlightHackEnabled())
                .setTooltip(Text.translatable("options.flight.enable.tooltip"))
                .setSaveConsumer(newValue -> {
                    modHacks.getConfig().setFlightHackEnabled(newValue);
                })
                .setYesNoTextSupplier(yesNoTextSupplier)
                .build();


        //Enable Autofish
        AbstractConfigListEntry toggleAutofish = entryBuilder.startBooleanToggle(Text.translatable("options.autofish.enable.title"), config.isAutofishEnabled())
                .setDefaultValue(defaults.isAutofishEnabled())
                .setTooltip(Text.translatable("options.autofish.enable.tooltip"))
                .setSaveConsumer(newValue -> {
                    modHacks.getConfig().setAutofishEnabled(newValue);
                })
                .setYesNoTextSupplier(yesNoTextSupplier)
                .build();

        //Enable MultiRod
        AbstractConfigListEntry toggleMultiRod = entryBuilder.startBooleanToggle(Text.translatable("options.autofish.multirod.title"), config.isMultiRod())
                .setDefaultValue(defaults.isMultiRod())
                .setTooltip(
                        Text.translatable("options.autofish.multirod.tooltip_0"),
                        Text.translatable("options.autofish.multirod.tooltip_1"),
                        Text.translatable("options.autofish.multirod.tooltip_2")
                )
                .setSaveConsumer(newValue -> {
                    modHacks.getConfig().setMultiRod(newValue);
                })
                .setYesNoTextSupplier(yesNoTextSupplier)
                .build();

        //Enable Break Protection
        AbstractConfigListEntry toggleBreakProtection = entryBuilder.startBooleanToggle(Text.translatable("options.autofish.break_protection.title"), config.isNoBreak())
                .setDefaultValue(defaults.isNoBreak())
                .setTooltip(
                        Text.translatable("options.autofish.break_protection.tooltip_0"),
                        Text.translatable("options.autofish.break_protection.tooltip_1")
                )
                .setSaveConsumer(newValue -> {
                    modHacks.getConfig().setNoBreak(newValue);
                })
                .setYesNoTextSupplier(yesNoTextSupplier)
                .build();

        //Enable Persistent Mode
        AbstractConfigListEntry togglePersistentMode = entryBuilder.startBooleanToggle(Text.translatable("options.autofish.persistent.title"), config.isPersistentMode())
                .setDefaultValue(defaults.isPersistentMode())
                .setTooltip(
                        Text.translatable("options.autofish.persistent.tooltip_0"),
                        Text.translatable("options.autofish.persistent.tooltip_1"),
                        Text.translatable("options.autofish.persistent.tooltip_2"),
                        Text.translatable("options.autofish.persistent.tooltip_3"),
                        Text.translatable("options.autofish.persistent.tooltip_4"),
                        Text.translatable("options.autofish.persistent.tooltip_5")
                )
                .setSaveConsumer(newValue -> {
                    modHacks.getConfig().setPersistentMode(newValue);
                })
                .setYesNoTextSupplier(yesNoTextSupplier)
                .build();


        //Enable Sound Detection
        AbstractConfigListEntry toggleSoundDetection = entryBuilder.startBooleanToggle(Text.translatable("options.autofish.sound.title"), config.isUseSoundDetection())
                .setDefaultValue(defaults.isUseSoundDetection())
                .setTooltip(
                        Text.translatable("options.autofish.sound.tooltip_0"),
                        Text.translatable("options.autofish.sound.tooltip_1"),
                        Text.translatable("options.autofish.sound.tooltip_2"),
                        Text.translatable("options.autofish.sound.tooltip_3"),
                        Text.translatable("options.autofish.sound.tooltip_4"),
                        Text.translatable("options.autofish.sound.tooltip_5"),
                        Text.translatable("options.autofish.sound.tooltip_6"),
                        Text.translatable("options.autofish.sound.tooltip_7"),
                        Text.translatable("options.autofish.sound.tooltip_8"),
                        Text.translatable("options.autofish.sound.tooltip_9")
                )
                .setSaveConsumer(newValue -> {
                    modHacks.getConfig().setUseSoundDetection(newValue);
                    modHacks.getAutofish().setDetection();
                })
                .setYesNoTextSupplier(yesNoTextSupplier)
                .build();

        //Enable Force MP Detection
        AbstractConfigListEntry toggleForceMPDetection = entryBuilder.startBooleanToggle(Text.translatable("options.autofish.multiplayer_compat.title"), config.isForceMPDetection())
                .setDefaultValue(defaults.isPersistentMode())
                .setTooltip(
                        Text.translatable("options.autofish.multiplayer_compat.tooltip_0"),
                        Text.translatable("options.autofish.multiplayer_compat.tooltip_1"),
                        Text.translatable("options.autofish.multiplayer_compat.tooltip_2")
                )
                .setSaveConsumer(newValue -> {
                    modHacks.getConfig().setForceMPDetection(newValue);
                })
                .setYesNoTextSupplier(yesNoTextSupplier)
                .build();

        //Recast Delay
        AbstractConfigListEntry recastDelaySlider = entryBuilder.startLongSlider(Text.translatable("options.autofish.recast_delay.title"), config.getRecastDelay(), 500, 5000)
                .setDefaultValue(defaults.getRecastDelay())
                .setTooltip(
                        Text.translatable("options.autofish.recast_delay.tooltip_0"),
                        Text.translatable("options.autofish.recast_delay.tooltip_1")
                )
                .setTextGetter(value -> Text.translatable("options.autofish.recast_delay.value", value))
                .setSaveConsumer(newValue -> {
                    modHacks.getConfig().setRecastDelay(newValue);
                })
                .build();

        //ClearLag Regex
        AbstractConfigListEntry clearLagRegexField = entryBuilder.startTextField(Text.translatable("options.autofish.clear_regex.title"), config.getClearLagRegex())
                .setDefaultValue(defaults.getClearLagRegex())
                .setTooltip(
                        Text.translatable("options.autofish.clear_regex.tooltip_0"),
                        Text.translatable("options.autofish.clear_regex.tooltip_1"),
                        Text.translatable("options.autofish.clear_regex.tooltip_2")
                )
                .setSaveConsumer(newValue -> {
                    modHacks.getConfig().setClearLagRegex(newValue);
                })
                .build();

        SubCategoryBuilder subCategoryBuilderFlightHack = entryBuilder.startSubCategory(Text.translatable("options.flight.flight.title"));
        subCategoryBuilderFlightHack.add(toggleFlightHack);
        subCategoryBuilderFlightHack.setExpanded(true);

        SubCategoryBuilder subCategoryBuilderAutoFish = entryBuilder.startSubCategory(Text.translatable("options.autofish.autofish.title"));
        subCategoryBuilderAutoFish.add(toggleAutofish);
        subCategoryBuilderAutoFish.add(toggleMultiRod);
        subCategoryBuilderAutoFish.add(toggleBreakProtection);
        subCategoryBuilderAutoFish.add((togglePersistentMode));
        subCategoryBuilderAutoFish.add(toggleSoundDetection);
        subCategoryBuilderAutoFish.add(toggleForceMPDetection);
        subCategoryBuilderAutoFish.add(recastDelaySlider);
        subCategoryBuilderAutoFish.add(clearLagRegexField);
        subCategoryBuilderAutoFish.setExpanded(true);

        configCat.addEntry(subCategoryBuilderFlightHack.build());
        configCat.addEntry(subCategoryBuilderAutoFish.build());

        return builder.build();
    }
}
