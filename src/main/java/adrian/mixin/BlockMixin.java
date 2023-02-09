package adrian.mixin;

import adrian.hacks.AdriansMod.FabricModAdriansMod;
import adrian.hacks.xray.Xray;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockMixin {

    public BlockMixin() {
    }

    @Inject(at = @At("HEAD"), method = "shouldDrawSide", cancellable = true)
    private static void shouldDrawSide(BlockState state, BlockView world, BlockPos pos, Direction side, BlockPos otherPos, CallbackInfoReturnable<Boolean> ci) {
        if (FabricModAdriansMod.getInstance().xrayEnabled && FabricModAdriansMod.getInstance().xrayActive) {
            ci.setReturnValue(FabricModAdriansMod.getInstance().xRay.showBlock(state));
        }
    }
}