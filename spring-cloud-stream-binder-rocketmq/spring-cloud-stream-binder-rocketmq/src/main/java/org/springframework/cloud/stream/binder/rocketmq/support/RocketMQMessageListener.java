package org.springframework.cloud.stream.binder.rocketmq.support;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.binder.EmbeddedHeaderUtils;
import org.springframework.cloud.stream.binder.ExtendedConsumerProperties;
import org.springframework.cloud.stream.binder.MessageValues;
import org.springframework.cloud.stream.binder.rocketmq.properties.RocketMQConsumerProperties;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.messaging.Message;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RocketMQMessageListener extends MessageProducerSupport {
	private RocketMQResourceManager resourceManager;
	private String topic;
	private String consumerGroup;
	private ExtendedConsumerProperties<RocketMQConsumerProperties> extendedConsumerProperties;
	private DefaultMQPushConsumer consumer;

	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	public RocketMQMessageListener(RocketMQResourceManager resourceManager, String topic, String consumerGroup,
			ExtendedConsumerProperties<RocketMQConsumerProperties> extendedConsumerProperties) {
		this.resourceManager = resourceManager;
		this.topic = topic;
		this.extendedConsumerProperties = extendedConsumerProperties;
		this.consumerGroup = consumerGroup;
	}

	@Override
	protected void doStart() {
		String nameSrvConnectionString = this.resourceManager.getConfigurationProperties().getNameSrvConnectionString();
		String tags = this.extendedConsumerProperties.getExtension().getTags();
		logger.info("[consumer]nameSrvConnectionString:{},consumerGroup:{},topic:{},tags:{}", nameSrvConnectionString,
				consumerGroup, topic, tags);
		consumer = new DefaultMQPushConsumer(consumerGroup);
		consumer.setNamesrvAddr(nameSrvConnectionString);
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
		consumer.setMessageModel(MessageModel.BROADCASTING);
		try {
			consumer.subscribe(this.topic, tags);
			consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                logger.info(Thread.currentThread().getName() + " Receive New Messages[size={}]: {}", msgs.size(),
                        msgs);
                for (MessageExt messageExt : msgs) {
                    byte[] payload = messageExt.getBody();
                    try {
                        logger.info("Listener:" + new String(payload, "UTF-8"));
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        MessageValues mv = EmbeddedHeaderUtils.extractHeaders(payload);
                        logger.info("mv.payload:{}, headers:{}", mv.getPayload(), mv.getHeaders() );
                        Message<?> internalMsgObject = getMessageBuilderFactory().withPayload((byte[])mv.getPayload())
                                .copyHeaders(mv.getHeaders()).build();
                        sendMessage(internalMsgObject);
                    } catch (Exception e) {
                        logger.info("==========" + e.getMessage());
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });

			consumer.start();
			logger.info("Consumer Started.%n");
		} catch (MQClientException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doStop() {
		if (this.consumer != null) {
			 this.consumer.shutdown();
			logger.info("Consumer shutdown.%n");
		}
	}
}
