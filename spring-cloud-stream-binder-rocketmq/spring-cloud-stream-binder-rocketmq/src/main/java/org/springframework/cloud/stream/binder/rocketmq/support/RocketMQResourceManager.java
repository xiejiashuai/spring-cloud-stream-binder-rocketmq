package org.springframework.cloud.stream.binder.rocketmq.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.binder.rocketmq.properties.RocketMQBinderConfigurationProperties;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RocketMQResourceManager {
	private RocketMQBinderConfigurationProperties configurationProperties;
	private ObjectMapper mapper;
	private Logger logger = LoggerFactory.getLogger(RocketMQResourceManager.class);
	
	public RocketMQResourceManager(RocketMQBinderConfigurationProperties configurationProperties) {
		this.configurationProperties = configurationProperties;
		this.mapper = new ObjectMapper();
	}
	
	public RocketMQBinderConfigurationProperties getConfigurationProperties() {
		return this.configurationProperties;
	}
	
}
