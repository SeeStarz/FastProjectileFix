package com.seestarz.fast_projectile_fix.mixin;

import com.seestarz.fast_projectile_fix.FastProjectileFix;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.math.vector.Vector3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;

@Mixin(SEntityVelocityPacket.class)
public class MixinSEntityVelocityPacket {
    @Shadow
    private int entityID;

    @Shadow
    private int motionX;

    @Shadow
    private int motionY;

    @Shadow
    private int motionZ;

    @Inject(method = "Lnet/minecraft/network/play/server/SEntityVelocityPacket;<init>" +
            "(ILnet/minecraft/util/math/vector/Vector3d;)V", at = @At(value = "RETURN"))
    public void SEntityVelocityPacket(int entityId, Vector3d motionVector, CallbackInfo ci) {
        this.motionX = (int)(motionVector.x * 8000.0D);
        this.motionY = (int)(motionVector.y * 8000.0D);
        this.motionZ = (int)(motionVector.z * 8000.0D);

        FastProjectileFix.LOGGER.info(motionVector.toString());
        FastProjectileFix.LOGGER.info("entityId: ${entityId}");
    }

    @Inject(method = "Lnet/minecraft/network/play/server/SEntityVelocityPacket;readPacketData" +
            "(Lnet/minecraft/network/PacketBuffer;)V", at = @At(value = "HEAD"), cancellable = true)
    public void readPacketData(PacketBuffer buf, CallbackInfo ci) throws IOException {
        ci.cancel();
        this.entityID = buf.readVarInt();
        this.motionX = buf.readInt();
        this.motionY = buf.readInt();
        this.motionZ = buf.readInt();
    }

    @Inject(method = "Lnet/minecraft/network/play/server/SEntityVelocityPacket;writePacketData" +
            "(Lnet/minecraft/network/PacketBuffer;)V", at = @At(value = "HEAD"), cancellable = true)
    public void writePacketData(PacketBuffer buf, CallbackInfo ci) throws IOException {
        ci.cancel();
        buf.writeVarInt(this.entityID);
        buf.writeInt(this.motionX);
        buf.writeInt(this.motionY);
        buf.writeInt(this.motionZ);
    }
}
