package org.springframework.cloud.stream.binder.rocketmq.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author js.xie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RocketMQBindingProperties {

	private RocketMQConsumerProperties consumer = new RocketMQConsumerProperties();

	private RocketMQProducerProperties producer = new RocketMQProducerProperties();

}
