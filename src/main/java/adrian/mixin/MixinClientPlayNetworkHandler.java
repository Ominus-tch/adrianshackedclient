package adrian.mixin;

import adrian.hacks.AdriansMod.FabricModAdriansMod;
import adrian.hacks.fullbright.statuseffect.StatusEffectManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class MixinClientPlayNetworkHandler {

    @Shadow private MinecraftClient client;

    @Inject(method = "onPlaySound", at = @At("HEAD"))
    public void onPlaySound(PlaySoundS2CPacket playSoundS2CPacket_1, CallbackInfo ci) {
        if (client.isOnThread()) FabricModAdriansMod.getInstance().handlePacket(playSoundS2CPacket_1);
    }

    @Inject(method = "onEntityVelocityUpdate", at = @At("HEAD"))
    public void onVelocityUpdate(EntityVelocityUpdateS2CPacket entityVelocityUpdateS2CPacket_1, CallbackInfo ci) {
        if (client.isOnThread()) FabricModAdriansMod.getInstance().handlePacket(entityVelocityUpdateS2CPacket_1);
    }

    @Inject(method = "onGameMessage", at = @At("HEAD"))
    public void onChatMessage(GameMessageS2CPacket chatMessageS2CPacket_1, CallbackInfo ci) {
        if (client.isOnThread()) FabricModAdriansMod.getInstance().handleChat(chatMessageS2CPacket_1);
    }

    @Inject(method = "onPlayerAbilities", at = @At("TAIL"))
    private void onPlayerAbilities(PlayerAbilitiesS2CPacket packet, CallbackInfo info) {
        StatusEffectManager.updateGammaStatusEffect();
        StatusEffectManager.updateNightVision();
    }

    @Inject(method = "onPlayerRespawn", at = @At("TAIL"))
    private void onPlayerRespawn(PlayerRespawnS2CPacket packet, CallbackInfo info) {
        StatusEffectManager.updateGammaStatusEffect();
        StatusEffectManager.updateNightVision();
    }
}