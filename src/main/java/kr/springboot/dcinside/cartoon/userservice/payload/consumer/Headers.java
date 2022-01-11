package kr.springboot.dcinside.cartoon.userservice.payload.consumer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Headers{

	@JsonProperty("kafka_messageKey")
	private String kafkaMessageKey;

	@JsonProperty("id")
	private String id;

	@JsonProperty("timestamp")
	private long timestamp;

	public String getKafkaMessageKey(){
		return kafkaMessageKey;
	}

	public String getId(){
		return id;
	}

	public long getTimestamp(){
		return timestamp;
	}
}