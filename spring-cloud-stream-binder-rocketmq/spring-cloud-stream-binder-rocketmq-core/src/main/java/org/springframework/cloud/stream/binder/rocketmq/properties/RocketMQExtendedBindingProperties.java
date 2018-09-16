package org.springframework.cloud.stream.binder.rocketmq.properties;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.stream.binder.ExtendedBindingProperties;

/**
 * @author js.xie
 */
@ConfigurationProperties("spring.cloud.stream.rocketmq")
public class RocketMQExtendedBindingProperties
		implements ExtendedBindingProperties<RocketMQConsumerProperties, RocketMQProducerProperties> {

	private Map<String, RocketMQBindingProperties> bindings = new HashMap<>();

	public Map<String, RocketMQBindingProperties> getBindings() {
		return this.bindings;
	}

	public void setBindings(Map<String, RocketMQBindingProperties> bindings) {
		this.bindings = bindings;
	}

	@Override
	public RocketMQConsumerProperties getExtendedConsumerProperties(String channelName) {
		if (this.bindings.containsKey(channelName) && this.bindings.get(channelName).getConsumer() != null) {
			return this.bindings.get(channelName).getConsumer();
		}
		else {
			return new RocketMQConsumerProperties();
		}
	}

	@Override
	public RocketMQProducerProperties getExtendedProducerProperties(String channelName) {
		if (this.bindings.containsKey(channelName) && this.bindings.get(channelName).getProducer() != null) {
			return this.bindings.get(channelName).getProducer();
		}
		else {
			return new RocketMQProducerProperties();
		}
	}
}
