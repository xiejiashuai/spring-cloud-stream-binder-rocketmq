package org.springframework.cloud.stream.binder.rocketmq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.binder.Binder;
import org.springframework.cloud.stream.binder.rocketmq.RocketMQMessageChannelBinder;
import org.springframework.cloud.stream.binder.rocketmq.properties.RocketMQBinderConfigurationProperties;
import org.springframework.cloud.stream.binder.rocketmq.properties.RocketMQExtendedBindingProperties;
import org.springframework.cloud.stream.binder.rocketmq.provisioning.RocketMQTopicProvisioner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author js.xie
 */
@Configuration
@ConditionalOnMissingBean(Binder.class)
@Import({PropertyPlaceholderAutoConfiguration.class})
@EnableConfigurationProperties({RocketMQBinderConfigurationProperties.class, RocketMQExtendedBindingProperties.class})
public class RocketMQBinderConfiguration {

    @Autowired
    private RocketMQBinderConfigurationProperties configurationProperties;

    @Autowired
    private RocketMQExtendedBindingProperties rocketmqExtendedBindingProperties;

    @Bean
    RocketMQTopicProvisioner provisioningProvider() {
        return new RocketMQTopicProvisioner(this.configurationProperties);
    }

    @Bean
    RocketMQMessageChannelBinder rocketmqMessageChannelBinder() {
        RocketMQMessageChannelBinder messageChannelBinder = new RocketMQMessageChannelBinder(
                this.configurationProperties, provisioningProvider());
        messageChannelBinder.setExtendedBindingProperties(this.rocketmqExtendedBindingProperties);
        return messageChannelBinder;
    }

}
