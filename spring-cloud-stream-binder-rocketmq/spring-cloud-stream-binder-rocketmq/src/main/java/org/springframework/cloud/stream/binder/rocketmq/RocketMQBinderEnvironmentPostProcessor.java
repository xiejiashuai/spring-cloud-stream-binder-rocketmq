package org.springframework.cloud.stream.binder.rocketmq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

/**
 * An {@link EnvironmentPostProcessor} that sets some common configuration properties (log config etc.,) for RocketMQ
 *  binder.
 *
 * @author js.xie
 */
public class RocketMQBinderEnvironmentPostProcessor implements EnvironmentPostProcessor {

	public final static String SPRING_ROCKETMQ = "spring.rocketmq";

	public final static String SPRING_ROCKETMQ_NAMESRV_ADDR = SPRING_ROCKETMQ + ".NAMESRV_ADDR";
	
	public final static String SPRING_ROCKETMQ_NAMESERVER_ADDRESS = SPRING_ROCKETMQ + ".nameserver_address";
	
	public final static String SPRING_ROCKETMQ_PRODUCER = SPRING_ROCKETMQ + ".producer";

	public final static String SPRING_ROCKETMQ_CONSUMER = SPRING_ROCKETMQ + ".consumer";

	public final static String SPRING_ROCKETMQ_PRODUCER_KEY_SERIALIZER = SPRING_ROCKETMQ_PRODUCER + "." + "keySerializer";

	public final static String SPRING_ROCKETMQ_PRODUCER_VALUE_SERIALIZER = SPRING_ROCKETMQ_PRODUCER + "." + "valueSerializer";

	public final static String SPRING_ROCKETMQ_CONSUMER_KEY_DESERIALIZER = SPRING_ROCKETMQ_CONSUMER + "." + "keyDeserializer";

	public final static String SPRING_ROCKETMQ_CONSUMER_VALUE_DESERIALIZER = SPRING_ROCKETMQ_CONSUMER + "." + "valueDeserializer";

	private static final String ROCKETMQ_BINDER_DEFAULT_PROPERTIES = "rocketmqBinderDefaultProperties";

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		if (!environment.getPropertySources().contains(ROCKETMQ_BINDER_DEFAULT_PROPERTIES)) {
			Map<String, Object> rocketmqBinderDefaultProperties = new HashMap<>();
			rocketmqBinderDefaultProperties.put("logging.level.org.apache.rocketmq", "ERROR");
	
			environment.getPropertySources().addLast(new MapPropertySource(ROCKETMQ_BINDER_DEFAULT_PROPERTIES, rocketmqBinderDefaultProperties));
		}
	}
}
