package adrian.hacks.flight;

import adrian.hacks.AdriansMod.FabricModAdriansMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

public class Flight {
    int toggle = 0;
    int MAX_SPEED = 3;

    double FALL_SPEED = -0.04;
    double acceleration = 0.1;

    private MinecraftClient client;
    private FabricModAdriansMod modHacks;

    public Flight(FabricModAdriansMod modHacks) {
        this.modHacks = modHacks;
        this.client = MinecraftClient.getInstance();
    }


    public void tick(MinecraftClient client) {

        if(client.player!=null && modHacks.getConfig().isFlightHackEnabled()) {
            boolean jumpPressed = client.options.jumpKey.isPressed();
            boolean forwardPressed = client.options.forwardKey.isPressed();
            boolean leftPressed = client.options.leftKey.isPressed();
            boolean rightPressed = client.options.rightKey.isPressed();
            boolean backPressed = client.options.backKey.isPressed();

            Entity object = client.player;
            if(client.player.hasVehicle()) {
                object = client.player.getVehicle();
            }

            Vec3d velocity = object.getVelocity();
            Vec3d newVelocity = new Vec3d(velocity.x, -FALL_SPEED, velocity.z);
            if(jumpPressed) {
                if(forwardPressed) {
                    newVelocity = client.player.getRotationVector().multiply(acceleration);
                }
                if(leftPressed && !client.player.hasVehicle()) {
                    newVelocity = client.player.getRotationVector().multiply(acceleration).rotateY(3.1415928F/2);
                    newVelocity = new Vec3d(newVelocity.x, 0, newVelocity.z);
                }
                if(rightPressed && !client.player.hasVehicle()) {
                    newVelocity = client.player.getRotationVector().multiply(acceleration).rotateY(-3.1415928F/2);
                    newVelocity = new Vec3d(newVelocity.x, 0, newVelocity.z);
                }
                if(backPressed) {
                    newVelocity = client.player.getRotationVector().negate().multiply(acceleration);
                }

                newVelocity = new Vec3d(newVelocity.x, (toggle==0 && newVelocity.y>FALL_SPEED ? FALL_SPEED : newVelocity.y), newVelocity.z);
                object.setVelocity(newVelocity);

                if(forwardPressed || leftPressed || rightPressed || backPressed) {
                    if(acceleration<MAX_SPEED)
                        acceleration += 0.1;
                } else if (acceleration>0.2) {
                    acceleration -= 0.2;
                }
            }

            if(toggle == 0 || newVelocity.y <= -0.04) {
                toggle = 40;
            }
            toggle--;
        }
    }
}
