package adrian.hacks.AdriansMod;

import adrian.hacks.autofish.Autofish;
import adrian.hacks.AdriansMod.config.Config;
import adrian.hacks.AdriansMod.config.ConfigManager;
import adrian.hacks.autofish.scheduler.AutofishScheduler;
import adrian.hacks.flight.BoatFlight;
import adrian.hacks.flight.Flight;
import adrian.hacks.fullbright.config.FullbrightConfig;
import adrian.hacks.fullbright.config.FullbrightConfigManager;
import adrian.hacks.fullbright.statuseffect.StatusEffectManager;
import adrian.hacks.gui.AdriansModsScreenBuilder;
import adrian.hacks.xray.Xray;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;

import org.lwjgl.glfw.GLFW;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FabricModAdriansMod implements ClientModInitializer {
    private static FabricModAdriansMod instance;
    private Flight flight;
    private BoatFlight BoatFlight;
    public Xray xRay;

    private boolean xrayState = false;
    private boolean flightState = false;
    private boolean boatFlightState = false;
    private boolean fullBrightState = false;

    private boolean isXrayKeyDown = false;
    private boolean isFlightKeyDown = false;
    private boolean isBoatFlightKeyDown = false;
    private boolean isBoatFullbrightKeyDown = false;

    private Autofish autofish;
    private AutofishScheduler scheduler;

    private KeyBinding AdriansModGUIKey;
    private KeyBinding AdriansModXrayKey;
    private KeyBinding AdriansModFlightKey;
    private KeyBinding AdriansModBoatFlightKey;
    private KeyBinding AdriansModFullbrightKey;

    private ConfigManager configManager;

    private FullbrightConfigManager fullbrightConfigManager;
    //private StatusEffectManager statusEffectManager;

    public static final Logger LOGGER = LoggerFactory.getLogger("FabricModAdriansMod");


    @Override
    public void onInitializeClient() {
        if (instance == null) instance = this;
        FabricModAdriansMod.LOGGER.info("Client Initialized");

        this.configManager = new ConfigManager(this);
        this.fullbrightConfigManager = new FullbrightConfigManager(this);
        //this.statusEffectManager = new StatusEffectManager(this);
        FabricModAdriansMod.LOGGER.info("Xray Enabled: "+getConfig().isXrayEnabled()+" and "+getConfig().isXrayActive());
        //Register Keybinding
        AdriansModGUIKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.adrian's_mod.open_gui", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_V, "Adrian's mod"));
        AdriansModXrayKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.adrian's_mod.activate_xray", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_X, "Adrian's mod"));
        AdriansModFlightKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.adrian's_mod.enable_flight", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G, "Adrian's mod"));
        AdriansModBoatFlightKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.adrian's_mod.enable_boat_flight", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_H, "Adrian's mod"));
        AdriansModFullbrightKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.adrian's_mod.enable_fullbright", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_Y, "Adrian's mod"));
        //Register Tick Callback
        ClientTickEvents.END_CLIENT_TICK.register(this::tick);
        //Create Scheduler instance
        this.scheduler = new AutofishScheduler(this);
        //Create Autofisher instance
        this.autofish = new Autofish(this);
        //Create Flight instance
        this.flight = new Flight(this);
        //Create Boat Flight instance
        this.BoatFlight = new BoatFlight(this);
        //Create xRay instance
        this.xRay = new Xray();

        ClientTickEvents.END_CLIENT_TICK.register(this::tick);
    }

    public void tick(MinecraftClient client) {
        if (AdriansModXrayKey.isPressed()) {
            if (!isXrayKeyDown) {
                xrayState = !xrayState;
                getConfig().setXrayActive(xrayState);
                LOGGER.info("X-ray turned " + (xrayState ? "on" : "off"));
            }
            isXrayKeyDown = true;
        } else {
            isXrayKeyDown = false;
        }
        if (AdriansModFlightKey.isPressed()) {
            if (!isFlightKeyDown) {
                flightState = !flightState;
                getConfig().setFlightHackEnabled(flightState);
                LOGGER.info("Flight turned " + (flightState ? "on" : "off"));
            }
            isFlightKeyDown = true;
        } else {
            isFlightKeyDown = false;
        }
        if (AdriansModBoatFlightKey.isPressed()) {
            if (!isBoatFlightKeyDown) {
                boatFlightState = !boatFlightState;
                getConfig().setBoatFlightHackEnabled(boatFlightState);
                LOGGER.info("Boat flight turned " + (boatFlightState ? "on" : "off"));
            }
            isBoatFlightKeyDown = true;
        } else {
            isBoatFlightKeyDown = false;
        }
        if (AdriansModGUIKey.wasPressed()) {
            client.setScreen(AdriansModsScreenBuilder.buildScreen(this, client.currentScreen));
        }
        autofish.tick(client);
        scheduler.tick(client);

        flight.tick(client);
        BoatFlight.tick(client);
    }

    public static FabricModAdriansMod getInstance() {
        return instance;
    }


    public void handlePacket(Packet<?> packet) {
        autofish.handlePacket(packet);
    }

    /**
     * Mixin callback for chat packets
     */
    public void handleChat(GameMessageS2CPacket packet) {
        autofish.handleChat(packet);
    }

    /**
     * Mixin callback for catchingFish method of EntityFishHook (singleplayer detection)
     */
    public void tickFishingLogic(Entity owner, int ticksCatchable) {
        autofish.tickFishingLogic(owner, ticksCatchable);
    }

    public Autofish getAutofish() {
        return autofish;
    }

    public Xray getXRay() {
        return xRay;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public FullbrightConfigManager getFullbrightConfigManager() {
        return fullbrightConfigManager;
    }

    public Config getConfig() {
        return configManager.getConfig();
    }

    public FullbrightConfig getFullbrightConfig() { return fullbrightConfigManager.getFullbrightConfig(); }

    public void updateNightVision() {
        StatusEffectManager.updateNightVision();
    }

    public AutofishScheduler getScheduler() {
        return scheduler;
    }

    private void loadEvents() {}

    private static void setGlobalConstants() {}
}