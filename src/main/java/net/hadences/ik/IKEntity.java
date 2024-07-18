package net.hadences.ik;

import net.hadences.Mimir;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an entity with an inverse kinematics (IK) chain, like a robotic arm or a leg.
 */
public class IKEntity {
    private List<IKPart> parts;  // List of IK chain parts
    private Vec3d targetPosition;  // Target position for the end effector (last part of the chain)
    private ServerWorld world;  // World where the entity exists
    private double tolerance;  // Tolerance for the IK solution accuracy
    private IKPart rootPart;  // Root part of the IK chain
    private IKPart endEffector;  // End effector part of the IK chain
    private Vec3d rootPosition;  // Position of the root part

    public IKEntity(double tolerance, ServerWorld world, Vec3d rootPosition) {
        this.rootPosition = rootPosition;
        this.world = world;
        this.parts = new ArrayList<>();
        this.tolerance = tolerance;
        this.targetPosition = new Vec3d(0, 0, 0);  // Default target, can be updated
        Mimir.ikEntityManager.registerEntity(this);
    }

    // Add a part to the IK chain
    public void addPart(IKPart part) {
        if (parts.isEmpty()) {
            part.setPosition(rootPosition);
            rootPart = part;
        } else {
            part.setParent(parts.get(parts.size() - 1));
            part.setPosition(parts.get(parts.size() - 1).getEndPosition());
        }
        parts.add(part);
        if (parts.size() > 1) {
            endEffector = parts.get(parts.size() - 1);
        }

        updateIK();
    }

    // Set the target position for the IK chain
    public void setTargetPosition(Vec3d target) {
        this.targetPosition = target;
    }

    // Perform IK calculations to adjust the positions of parts
    public void updateIK() {
        if (parts.isEmpty() || endEffector == null || rootPart == null) {
            return;  // Ensure there are enough parts to form a chain
        }

        final int maxIterations = 10;  // Limit the iterations to prevent excessive calculations
        double currentError = targetPosition.subtract(endEffector.getPosition()).lengthSquared();

        for (int i = 0; i < maxIterations && currentError > tolerance * tolerance; i++) {
            // Backward pass
            backwardPass();
            // Forward pass
            forwardPass();

            // Check the distance to the target position
            currentError = targetPosition.subtract(endEffector.getPosition()).lengthSquared();
        }

        renderParticles();
    }
    private void backwardPass() {
        // Start from the end effector
        parts.get(parts.size() - 1).setPosition(targetPosition);
        for (int i = parts.size() - 2; i >= 0; i--) {
            IKPart current = parts.get(i);
            IKPart next = parts.get(i + 1);
            Vec3d direction = current.getPosition().subtract(next.getPosition()).normalize();
            Vec3d newPosition = next.getPosition().add(direction.multiply(current.getLength()));
            current.setPosition(newPosition);
        }
    }

    private void forwardPass() {
        // Start from the root
        parts.get(0).setPosition(rootPart.getPosition());
        for (int i = 1; i < parts.size(); i++) {
            IKPart current = parts.get(i);
            IKPart previous = parts.get(i - 1);
            Vec3d direction = current.getPosition().subtract(previous.getPosition()).normalize();
            Vec3d newPosition = previous.getPosition().add(direction.multiply(previous.getLength()));
            current.setPosition(newPosition);
        }
    }

    private void renderParticles(){
        Mimir.LOGGER.info("Rendering particles");
        for (IKPart part : parts) {
            Vec3d position = part.getPosition();
            world.spawnParticles(ParticleTypes.FLAME, position.x, position.y, position.z, 1, 0.0, 0.0, 0.0, 0.0);
        }
    }

    public void setRootPosition(Vec3d rootPosition) {
        this.rootPosition = rootPosition;
        if (rootPart != null) {
            rootPart.setPosition(rootPosition);
            updateIK();
        }
    }

    public Vec3d getRootPosition() {
        return rootPosition;
    }

    // Getters for the parts and target position
    public List<IKPart> getParts() {
        return parts;
    }

    public Vec3d getTargetPosition() {
        return targetPosition;
    }

    public void tick() {
        // Update IK system each game tick
        updateIK();
    }
}

