package com.timthebrick.tinystorage.util;

import net.minecraft.entity.Entity;

public class MathHelper {

	public static int CalcModuloIncSign(int pInput, int pModulo) {
		int tResult = pInput % pModulo;
		if (pInput < 0) {
			tResult *= -1;
		}
		return tResult;
	}

	public static void setEntityMotionFromVector(Entity entity, Vector3 originalPosVector, float modifier) {
		Vector3 entityVector = Vector3.fromEntityCenter(entity);
		Vector3 finalVector = originalPosVector.copy().subtract(entityVector);

		if (finalVector.mag() > 1) {
			finalVector.normalize();
		}
		entity.motionX = finalVector.x * modifier;
		entity.motionY = finalVector.y * modifier;
		entity.motionZ = finalVector.z * modifier;
	}

	public static void setEntityVelocityFromVector(Entity entity, Vector3 originalPosVector, double modifier) {
		Vector3 entityVector = Vector3.fromEntityCenter(entity);
		Vector3 finalVector = originalPosVector.copy().subtract(entityVector);

		if (finalVector.mag() > 1) {
			finalVector.normalize();
		}
		entity.addVelocity(finalVector.x * modifier, finalVector.y * modifier, finalVector.z * modifier);
	}

}
