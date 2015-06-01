package com.timthebrick.tinystorage.util;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.Random;

public class WorldHelper {

    public static void spawnEntityItemWithRandomMovement(ItemStack itemStack, World world, int x, int y, int z){
        if (itemStack != null && itemStack.stackSize > 0) {
            Random rand = new Random();

            float dX = rand.nextFloat() * 0.8F + 0.1F;
            float dY = rand.nextFloat() * 0.8F + 0.1F;
            float dZ = rand.nextFloat() * 0.8F + 0.1F;

            EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z + dZ, itemStack.copy());

            if (itemStack.hasTagCompound()) {
                entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
            }

            float factor = 0.05F;
            entityItem.motionX = rand.nextGaussian() * factor;
            entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
            entityItem.motionZ = rand.nextGaussian() * factor;
            world.spawnEntityInWorld(entityItem);
            itemStack.stackSize = 0;
        }
    }


}
