package com.pa.jk.pay.util.protostuff;

import java.io.IOException;

import com.pa.jk.pay.util.protostuff.obj.Entity;
/**
 * 序列化接口
 * @author zhangjun19
 *
 */
public interface Serialize {
	
	byte[] serialize(Object obj) throws IOException;
	
	public <T extends Entity> T deSerialize(byte[] in,Class<? extends Entity> clazz) throws IOException, InstantiationException, IllegalAccessException;

}
