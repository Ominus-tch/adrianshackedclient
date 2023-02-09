package adrian.hacks.autofish.monitor;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.network.Packet;
import adrian.hacks.autofish.Autofish;

public interface FishMonitorMP {

    void hookTick(Autofish autofish, MinecraftClient minecraft, FishingBobberEntity hook);

    void handleHookRemoved();

    void handlePacket(Autofish autofish, Packet<?> packet, MinecraftClient minecraft);

}
