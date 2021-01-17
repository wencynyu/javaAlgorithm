package top.yuwenxin.serialization;


import com.google.protobuf.InvalidProtocolBufferException;

import java.util.Arrays;

/**
 * @Author: wenxin.yu
 * @Date: 2021/1/17 16:55
 */
public class SerializeDemo {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        EntityBuf.entity.Builder builder = EntityBuf.entity.newBuilder();
        builder.setId(1);
        builder.setName("hello protoBuf");

        EntityBuf.entity entity = builder.build();
        System.out.println(entity);
        byte[] res = entity.toByteArray();
        System.out.println(Arrays.toString(res));
        System.out.println(new String(res));
        EntityBuf.entity entityFromBytes = EntityBuf.entity.parseFrom(res);
        System.out.println(entityFromBytes);

    }
}
