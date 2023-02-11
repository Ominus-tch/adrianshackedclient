package adrian.mixin;

import adrian.hacks.fullbright.GammaUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StatusEffectUtil.class)
public class MixinStatusEffectUtil {

    @Inject(method = "durationToString", at = @At("HEAD"), cancellable = true)
    private static void durationToString(StatusEffectInstance effect, float multiplier, CallbackInfoReturnable<Text> info) {
        if (effect.getEffectType().equals(GammaUtils.BRIGHT) || effect.getEffectType().equals(GammaUtils.DIM)) {
            int gamma = (int)Math.round(MinecraftClient.getInstance().options.getGamma().getValue() * 100);
            info.setReturnValue(Text.literal(gamma + "%"));
        }
    }
}
