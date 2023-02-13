package adrian.hacks.stopfall;

import adrian.hacks.AdriansMod.FabricModAdriansMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

public class StopFall {

    double FALL_SPEED = 0;
    double acceleration = 0;

    private MinecraftClient client;
    private FabricModAdriansMod modHacks;

    public StopFall(FabricModAdriansMod modHacks) {
        this.modHacks = modHacks;
        this.client = MinecraftClient.getInstance();
    }


    public void tick(MinecraftClient client) {
        if (client.player!=null) {
            Entity object = client.player;

            Vec3d velocity = object.getVelocity();

            Vec3d newVelocity = new Vec3d(velocity.x, -FALL_SPEED, velocity.z);
            boolean noFallPressed = modHacks.isNoFallKeyDown();
            if(noFallPressed) {
                newVelocity = client.player.getRotationVector().negate().multiply(acceleration);
            }
        }
    }
}
