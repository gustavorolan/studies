package br.com.alura.ecommerceKafka;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonDeserializer<T> implements Deserializer<T> {
	public static final String TYPE_CONFIG= "br.com.alura.ecommerceKafka.type.config";
	private final Gson gson = new GsonBuilder().create();
	private Class<T> aClass;

	public void configure(Map<String,?> configs, boolean isKey){
		String typeName = String.valueOf(configs.get("TYPE_CONFIG"));
		try {
			this.aClass = (Class<T>) Class.forName(typeName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public T deserialize(String s, byte[] bytes) {
		return gson.fromJson(new String(bytes), this.aClass);
	}
}
