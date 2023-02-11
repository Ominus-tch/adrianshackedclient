package adrian.hacks.AdriansMod.config;

import adrian.hacks.AdriansMod.FabricModAdriansMod;
import adrian.hacks.xray.Xray;
import com.google.gson.annotations.Expose;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;

import static java.lang.Double.MAX_VALUE;

public class Config {

    int i;
    MinecraftClient client;

    @Expose boolean isFlightHackEnabled = false;
    @Expose boolean isBoatFlightHackEnabled = false;
    @Expose boolean isAutofishEnabled = false;
    @Expose boolean isXrayEnabled = false;
    @Expose boolean isXrayActive = false;
    @Expose boolean multiRod = false;
    @Expose boolean noBreak = false;
    @Expose boolean persistentMode = false;
    @Expose boolean useSoundDetection = false;
    @Expose boolean forceMPDetection = false;
    @Expose long recastDelay = 1500;
    @Expose String clearLagRegex = "\\[ClearLag\\] Removed [0-9]+ Entities!";

    public boolean isFlightHackEnabled() {
        return isFlightHackEnabled;
    }

    public boolean isBoatFlightHackEnabled() {
        return isBoatFlightHackEnabled;
    }

    public boolean isAutofishEnabled() {
        return isAutofishEnabled;
    }

    public boolean isXrayEnabled() {
        return isXrayEnabled;
    }
    public boolean isXrayActive() {
        return isXrayActive;
    }

    public boolean isMultiRod() {
        return multiRod;
    }

    public boolean isNoBreak() {
        return noBreak;
    }

    public boolean isPersistentMode() { return persistentMode; }

    public boolean isUseSoundDetection() {
        return useSoundDetection;
    }

    public boolean isForceMPDetection() { return forceMPDetection; }

    public long getRecastDelay() {
        return recastDelay;
    }

    public String getClearLagRegex() {
        return clearLagRegex;
    }

    public void setFlightHackEnabled(boolean FlightHackEnabled) { isFlightHackEnabled = FlightHackEnabled; }

    public void setBoatFlightHackEnabled(boolean BoatFlightHackEnabled) { isBoatFlightHackEnabled = BoatFlightHackEnabled; }

    public void setXrayEnabled(boolean XrayEnabled) { isXrayEnabled = XrayEnabled; }

    public void updateChunks() {
        this.client = MinecraftClient.getInstance();
        if(i==1) {
            client.options.getViewDistance().setValue(7);
            i = 0;
        } else {
            client.options.getViewDistance().setValue(8);
            i = 1;
        }
    }

    public void setXrayActive(boolean XrayActive) {
        isXrayActive = XrayActive;
        updateChunks();
    }

    public void setAutofishEnabled(boolean autofishEnabled) { isAutofishEnabled = autofishEnabled; }

    public void setMultiRod(boolean multiRod) { this.multiRod = multiRod; }

    public void setNoBreak(boolean noBreak) { this.noBreak = noBreak; }

    public void setPersistentMode(boolean persistentMode) { this.persistentMode = persistentMode; }

    public void setUseSoundDetection(boolean useSoundDetection) {
        this.useSoundDetection = useSoundDetection;
    }

    public void setForceMPDetection(boolean forceMPDetection) { this.forceMPDetection = forceMPDetection; }

    public void setRecastDelay(long recastDelay) {
        this.recastDelay = recastDelay;
    }

    public void setClearLagRegex(String clearLagRegex) {
        this.clearLagRegex = clearLagRegex;
    }

    /**
     * @return true if anything was changed
     */
    public boolean enforceConstraints() {
        boolean changed = false;
        if (recastDelay < 500) {
            recastDelay = 500;
            changed = true;
        }
        if (clearLagRegex == null) {
            clearLagRegex = "";
            changed = true;
        }
        return changed;
    }
}
