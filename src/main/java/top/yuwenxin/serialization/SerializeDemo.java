package top.yuwenxin.serialization;


import com.google.gson.Gson;
import com.google.protobuf.InvalidProtocolBufferException;
import top.yuwenxin.serialization.json.Entity;
import top.yuwenxin.serialization.protobuf.EntityBuf;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @Author: wenxin.yu
 * @Date: 2021/1/17 16:55
 */
public class SerializeDemo {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        protobuf();
        json();
    }

    private static void json() {
        Entity entity = new Entity(1, "test");
        Gson gson = new Gson();
        String entityJson = gson.toJson(entity);
        System.out.println(entityJson);
        Entity fromJson = gson.fromJson(entityJson, Entity.class);
        System.out.println(entityJson);
    }

    private static void protobuf() throws InvalidProtocolBufferException {
        EntityBuf.entity.Builder builder = EntityBuf.entity.newBuilder();
        builder.setId(1);
        builder.setName("hello protoBuf");

        EntityBuf.entity entity = builder.build();
        System.out.println(entity);
        byte[] res = entity.toByteArray();
        System.out.println(Arrays.toString(res));
        System.out.println(new String(res, StandardCharsets.ISO_8859_1));
        EntityBuf.entity entityFromBytes = EntityBuf.entity.parseFrom(res);
        System.out.println(entityFromBytes);
    }
}
