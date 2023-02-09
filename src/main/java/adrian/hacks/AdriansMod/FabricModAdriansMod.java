package adrian.hacks.AdriansMod;

import adrian.hacks.autofish.Autofish;
import adrian.hacks.AdriansMod.config.Config;
import adrian.hacks.AdriansMod.config.ConfigManager;
import adrian.hacks.autofish.scheduler.AutofishScheduler;
import adrian.hacks.flight.Flight;
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
    public Xray xRay;

    private Autofish autofish;
    private AutofishScheduler scheduler;

    private KeyBinding AdriansModGUIKey;
    private ConfigManager configManager;

    public boolean xrayEnabled = true;
    public boolean xrayActive = true;

    @Override
    public void onInitializeClient() {
        if (instance == null) instance = this;

        this.configManager = new ConfigManager(this);
        //Register Keybinding
        AdriansModGUIKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.adrian's_mod.open_gui", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_V, "Adrian's mod"));
        //Register Tick Callback
        ClientTickEvents.END_CLIENT_TICK.register(this::tick);
        //Create Scheduler instance
        this.scheduler = new AutofishScheduler(this);
        //Create Autofisher instance
        this.autofish = new Autofish(this);
        //Create Flight instance
        this.flight = new Flight(this);
        //Create xRay instance
        xRay = new Xray();

        ClientTickEvents.END_CLIENT_TICK.register(this::tick);
    }

    public void tick(MinecraftClient client) {
        if (AdriansModGUIKey.wasPressed()) {
            client.setScreen(AdriansModsScreenBuilder.buildScreen(this, client.currentScreen));
        }
        autofish.tick(client);
        scheduler.tick(client);

        flight.tick(client);
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

    public Config getConfig() {
        return configManager.getConfig();
    }

    public AutofishScheduler getScheduler() {
        return scheduler;
    }
}