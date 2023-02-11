package adrian.hacks.flight;

import adrian.hacks.AdriansMod.FabricModAdriansMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.Vec3d;

public class BoatFlight {
    int toggle = 0;
    int MAX_SPEED = 3;

    double FALL_SPEED = -0.01;
    double acceleration = 0.1;

    private MinecraftClient client;
    private FabricModAdriansMod modHacks;

    public BoatFlight(FabricModAdriansMod modHacks) {
        this.modHacks = modHacks;
        this.client = MinecraftClient.getInstance();
    }


    public void tick(MinecraftClient client) {

        if(client.player!=null && modHacks.getConfig().isBoatFlightHackEnabled()) {
            boolean jumpPressed = client.options.jumpKey.isPressed();
            boolean forwardPressed = client.options.forwardKey.isPressed();
            boolean backPressed = client.options.backKey.isPressed();

            if (client.player.hasVehicle()) {
                Entity vehicle = client.player.getVehicle();
                if (vehicle instanceof BoatEntity) {
                    Entity object = client.player.getVehicle();

                    Vec3d velocity = object.getVelocity();
                    Vec3d newVelocity = new Vec3d(velocity.x, -FALL_SPEED, velocity.z);
                    if(jumpPressed) {
                        if(forwardPressed) {
                            newVelocity = client.player.getRotationVector().multiply(acceleration);
                        }
                        if(backPressed) {
                            newVelocity = client.player.getRotationVector().negate().multiply(acceleration);
                        }

                        newVelocity = new Vec3d(newVelocity.x, (toggle==0 && newVelocity.y>FALL_SPEED ? FALL_SPEED : newVelocity.y), newVelocity.z);
                        object.setVelocity(newVelocity);

                        if(forwardPressed || backPressed) {
                            if(acceleration<MAX_SPEED)
                                acceleration += 0.1;
                        } else if (acceleration>0.2) {
                            acceleration -= 0.2;
                        }
                    }
                    if(toggle == 0 || newVelocity.y <= FALL_SPEED) {
                        toggle = 40;
                    }
                    toggle--;
                }
            }
        }
    }
}
