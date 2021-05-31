package de.jeff_media.magicvaults.serialization;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ItemStackSerializer {

    public static ItemStack deserializeItemStack(byte[] data) {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
             BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream)) {
            return (ItemStack) dataInput.readObject();
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
            return null;
        }
    }

    public static byte[] serializeItemStack(ItemStack itemStack) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream)) {
            dataOutput.writeObject(itemStack);
            return outputStream.toByteArray();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return null;
        }
    }

}
