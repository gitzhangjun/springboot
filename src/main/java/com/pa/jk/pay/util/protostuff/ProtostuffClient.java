package com.pa.jk.pay.util.protostuff;

import java.io.IOException;

import org.springframework.stereotype.Repository;

import com.pa.jk.pay.util.protostuff.obj.Entity;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;

/***
 * protostuff 序列化工场
 * 
 * @author zhangjun19
 */
@Repository("protostuffClient")
@SuppressWarnings("all")
public class ProtostuffClient implements Serialize {

	// 序列化
	@Override
	public byte[] serialize(Object obj) throws IOException {

		io.protostuff.Schema schema = RuntimeSchema.getSchema(obj.getClass());

		LinkedBuffer buffer = LinkedBuffer.allocate(4096);
		byte[] b = ProtostuffIOUtil.toByteArray(obj, schema, buffer);
		return b;
	}

	// 反序列化
	@Override
	public <T extends Entity> T deSerialize(byte[] in, Class<? extends Entity> clazz) throws IOException, InstantiationException, IllegalAccessException {

		Entity entity = clazz.newInstance();
		io.protostuff.Schema schema = RuntimeSchema.getSchema(entity.getClass());
		ProtostuffIOUtil.mergeFrom(in, entity, schema);

		return (T) entity;
	}

}
