package adrian.hacks.xray;

import net.minecraft.block.BlockState;

import java.util.HashSet;

public class Xray {
    private HashSet<String> xrayBlocks = new HashSet<>();

    public Xray() {
        xrayBlocks.add("Block{minecraft:coal_ore}");
    }

    public boolean showBlock(BlockState state) {
        /*if(xrayBlocks.contains(state.getBlock().toString())) {
            return true;
        }*/
        return false;
    }
}