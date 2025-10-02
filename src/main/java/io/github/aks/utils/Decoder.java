package io.github.aks.utils;

import net.querz.nbt.io.NBTInputStream;
import net.querz.nbt.io.NamedTag;
import net.querz.nbt.tag.CompoundTag;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class Decoder {
    public static String intArrayToString(int[] arr){
        StringBuilder sb = new StringBuilder(arr.length);
        for(int i : arr){
            sb.append((char) i);
        }
        return sb.toString();
    }
    public static byte[] decodeToNBT(byte[] compressed){
        try {
            GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(compressed));
            return gis.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong while GZIP decompressing",e);
        }
    }
    public static CompoundTag parsedNBT(byte[] nbt){
        try(NBTInputStream nis = new NBTInputStream(new ByteArrayInputStream(nbt))){
            NamedTag tag = nis.readTag(512);
            return (CompoundTag) tag.getTag();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static byte[] intArrayToBytes(int[] arr){
        byte[] result = new byte[arr.length];
        for(int i = 0; i < arr.length; i++){
            result[i] = (byte) arr[i];
        }
        return result;
    }
}
