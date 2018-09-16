package org.springframework.cloud.stream.binder.rocketmq.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author js.xie
 */
@ConfigurationProperties(prefix = "spring.cloud.stream.rocketmq.binder")
public class RocketMQBinderConfigurationProperties {

	private String[] nameSrvAddr = new String[] { "localhost" };

	private String defaultNameSrvPort = "9876";
	
	private Map<String, String> configuration = new HashMap<>();

	private String[] brokers = new String[] { "localhost" };

	private String defaultBrokerPort = "9092";

	private String[] headers = new String[] {};

	private int offsetUpdateTimeWindow = 10000;

	private int offsetUpdateCount;

	private int offsetUpdateShutdownTimeout = 2000;

	private int maxWait = 100;

	private boolean autoCreateTopics = true;

	private boolean autoAddPartitions;

	private int socketBufferSize = 2097152;

	/**
	 *  session timeout in milliseconds.
	 */
	private int nameSrvSessionTimeout = 10000;

	/**
	 * Connection timeout in milliseconds.
	 */
	private int nameSrvConnectionTimeout = 10000;

	private int requiredAcks = 1;

	private int replicationFactor = 1;

	private int fetchSize = 1024 * 1024;

	private int minPartitionCount = 1;

	private int queueSize = 8192;

	/**
	 * Time to wait to get partition information in seconds; default 60.
	 */
	private int healthTimeout = 60;

	public String getNameSrvConnectionString() {
		return toConnectionString(this.nameSrvAddr, this.defaultNameSrvPort);
	}

	public String getRocketMQConnectionString() {
		return toConnectionString(this.brokers, this.defaultBrokerPort);
	}

	public String[] getHeaders() {
		return this.headers;
	}

	public int getOffsetUpdateTimeWindow() {
		return this.offsetUpdateTimeWindow;
	}

	public int getOffsetUpdateCount() {
		return this.offsetUpdateCount;
	}

	public int getOffsetUpdateShutdownTimeout() {
		return this.offsetUpdateShutdownTimeout;
	}

	public String[] getNameSrvs() {
		return this.nameSrvAddr;
	}

	public void setNameSrvs(String... nameSrvAddr) {
		this.nameSrvAddr = nameSrvAddr;
	}

	public void setDefaultNameSrvPort(String defaultNameSrvPort) {
		this.defaultNameSrvPort = defaultNameSrvPort;
	}

	public String[] getBrokers() {
		return this.brokers;
	}

	public void setBrokers(String... brokers) {
		this.brokers = brokers;
	}

	public void setDefaultBrokerPort(String defaultBrokerPort) {
		this.defaultBrokerPort = defaultBrokerPort;
	}

	public void setHeaders(String... headers) {
		this.headers = headers;
	}

	public void setOffsetUpdateTimeWindow(int offsetUpdateTimeWindow) {
		this.offsetUpdateTimeWindow = offsetUpdateTimeWindow;
	}

	public void setOffsetUpdateCount(int offsetUpdateCount) {
		this.offsetUpdateCount = offsetUpdateCount;
	}

	public void setOffsetUpdateShutdownTimeout(int offsetUpdateShutdownTimeout) {
		this.offsetUpdateShutdownTimeout = offsetUpdateShutdownTimeout;
	}

	public int getNameSrvSessionTimeout() {
		return this.nameSrvSessionTimeout;
	}

	public void setNameSrvSessionTimeout(int nameSrvSessionTimeout) {
		this.nameSrvSessionTimeout = nameSrvSessionTimeout;
	}

	public int getNameSrvConnectionTimeout() {
		return this.nameSrvConnectionTimeout;
	}

	public void setNameSrvConnectionTimeout(int nameSrvConnectionTimeout) {
		this.nameSrvConnectionTimeout = nameSrvConnectionTimeout;
	}

	/**
	 * Converts an array of host values to a comma-separated String.
	 *
	 * It will append the default port value, if not already specified.
	 */
	private String toConnectionString(String[] hosts, String defaultPort) {
		String[] fullyFormattedHosts = new String[hosts.length];
		for (int i = 0; i < hosts.length; i++) {
			if (hosts[i].contains(":") || StringUtils.isEmpty(defaultPort)) {
				fullyFormattedHosts[i] = hosts[i];
			}
			else {
				fullyFormattedHosts[i] = hosts[i] + ":" + defaultPort;
			}
		}
		return StringUtils.arrayToDelimitedString(fullyFormattedHosts, ";");
	}

	public int getMaxWait() {
		return this.maxWait;
	}

	public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	}

	public int getRequiredAcks() {
		return this.requiredAcks;
	}

	public void setRequiredAcks(int requiredAcks) {
		this.requiredAcks = requiredAcks;
	}

	public int getReplicationFactor() {
		return this.replicationFactor;
	}

	public void setReplicationFactor(int replicationFactor) {
		this.replicationFactor = replicationFactor;
	}

	public int getFetchSize() {
		return this.fetchSize;
	}

	public void setFetchSize(int fetchSize) {
		this.fetchSize = fetchSize;
	}

	public int getMinPartitionCount() {
		return this.minPartitionCount;
	}

	public void setMinPartitionCount(int minPartitionCount) {
		this.minPartitionCount = minPartitionCount;
	}

	public int getHealthTimeout() {
		return this.healthTimeout;
	}

	public void setHealthTimeout(int healthTimeout) {
		this.healthTimeout = healthTimeout;
	}

	public int getQueueSize() {
		return this.queueSize;
	}

	public void setQueueSize(int queueSize) {
		this.queueSize = queueSize;
	}

	public boolean isAutoCreateTopics() {
		return this.autoCreateTopics;
	}

	public void setAutoCreateTopics(boolean autoCreateTopics) {
		this.autoCreateTopics = autoCreateTopics;
	}

	public boolean isAutoAddPartitions() {
		return this.autoAddPartitions;
	}

	public void setAutoAddPartitions(boolean autoAddPartitions) {
		this.autoAddPartitions = autoAddPartitions;
	}

	public int getSocketBufferSize() {
		return this.socketBufferSize;
	}

	public void setSocketBufferSize(int socketBufferSize) {
		this.socketBufferSize = socketBufferSize;
	}

	public Map<String, String> getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Map<String, String> configuration) {
		this.configuration = configuration;
	}

	public Map<String, Object> getConsumerConfiguration() {
		Map<String, Object> consumerConfiguration = new HashMap<>();

		return Collections.unmodifiableMap(consumerConfiguration);
	}

	public Map<String, Object> getProducerConfiguration() {
		Map<String, Object> producerConfiguration = new HashMap<>();
		return Collections.unmodifiableMap(producerConfiguration);
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RocketMQBinderConfigurationProperties [nameSrvAddr=").append(Arrays.toString(nameSrvAddr))
				.append(", defaultNameSrvPort=").append(defaultNameSrvPort).append(", configuration=")
				.append(configuration).append(", brokers=").append(Arrays.toString(brokers))
				.append(", defaultBrokerPort=").append(defaultBrokerPort).append(", headers=")
				.append(Arrays.toString(headers)).append(", offsetUpdateTimeWindow=").append(offsetUpdateTimeWindow)
				.append(", offsetUpdateCount=").append(offsetUpdateCount).append(", offsetUpdateShutdownTimeout=")
				.append(offsetUpdateShutdownTimeout).append(", maxWait=").append(maxWait).append(", autoCreateTopics=")
				.append(autoCreateTopics).append(", autoAddPartitions=").append(autoAddPartitions)
				.append(", socketBufferSize=").append(socketBufferSize).append(", nameSrvSessionTimeout=")
				.append(nameSrvSessionTimeout).append(", nameSrvConnectionTimeout=").append(nameSrvConnectionTimeout)
				.append(", requiredAcks=").append(requiredAcks).append(", replicationFactor=").append(replicationFactor)
				.append(", fetchSize=").append(fetchSize).append(", minPartitionCount=").append(minPartitionCount)
				.append(", queueSize=").append(queueSize).append(", healthTimeout=").append(healthTimeout)
				.append(", jaas=").append("]");
		return builder.toString();
	}

}
