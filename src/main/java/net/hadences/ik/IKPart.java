package net.hadences.ik;

import net.hadences.mixin.BlockDisplayEntityInvoker;
import net.hadences.mixin.DisplayEntityInvoker;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.AffineTransformation;
import net.minecraft.util.math.Vec3d;
import org.joml.Quaternionf;
import org.joml.Vector3f;

/***
 * instance of a part in the main IK chain
 */
public class IKPart {
    private Vec3d position;
    private Vec3d rotation;
    private Vec3d scale;
    private double length;
    private IKPart parent;

    public IKPart(double length) {
        this.position = new Vec3d(0, 0, 0);
        this.length = length;
    }

    public AffineTransformation createTransformation(Vec3d translationVec, Vec3d rotationVec, Vec3d scaleVec) {
        Vector3f translation = vec3dToVector3f(translationVec);
        Quaternionf rotation = eulerToQuaternion(rotationVec);
        Vector3f scale = vec3dToVector3f(scaleVec);
        return new AffineTransformation(translation, rotation, scale, rotation);  // Using the same rotation for left and right as an example
    }

    public Quaternionf eulerToQuaternion(Vec3d euler) {
        double cy = Math.cos(euler.z * 0.5);
        double sy = Math.sin(euler.z * 0.5);
        double cp = Math.cos(euler.y * 0.5);
        double sp = Math.sin(euler.y * 0.5);
        double cr = Math.cos(euler.x * 0.5);
        double sr = Math.sin(euler.x * 0.5);

        Quaternionf q = new Quaternionf();
        q.set(
                (float)(cr * cp * cy + sr * sp * sy),
                (float)(sr * cp * cy - cr * sp * sy),
                (float)(cr * sp * cy + sr * cp * sy),
                (float)(cr * cp * sy - sr * sp * cy)
        );
        return q;
    }

    public Vector3f vec3dToVector3f(Vec3d vec) {
        return new Vector3f((float)vec.x, (float)vec.y, (float)vec.z);
    }

    public Vec3d getEndPosition() {
        return position.add(new Vec3d(0, 0, length));
    }

    public Vec3d getEndPosition(Vec3d position, Vec3d rotation) {
        return position.add(new Vec3d(0, 0, length));
    }

    public IKPart getParent() {
        return parent;
    }

    public void setParent(IKPart parent) {
        this.parent = parent;
    }

    public Vec3d getPosition() {
        return position;
    }

    public void setPosition(Vec3d position) {
        this.position = position;
    }

    public Vec3d getRotation() {
        return rotation;
    }

    public void setRotation(Vec3d rotation) {
        this.rotation = rotation;
    }

    public Vec3d getScale() {
        return scale;
    }

    public void setScale(Vec3d scale) {
        this.scale = scale;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }


}
