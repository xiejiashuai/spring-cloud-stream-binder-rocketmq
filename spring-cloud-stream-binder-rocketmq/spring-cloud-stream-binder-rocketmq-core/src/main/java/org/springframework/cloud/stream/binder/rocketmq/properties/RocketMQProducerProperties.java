package org.springframework.cloud.stream.binder.rocketmq.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author js.xie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RocketMQProducerProperties {

	private String groupName;
	
	private String tags;
	
	private int bufferSize = 16384;

	private boolean sync;

	private int batchTimeout;

	private Map<String, String> configuration = new HashMap<>();

}
