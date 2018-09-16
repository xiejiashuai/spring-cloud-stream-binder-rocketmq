package org.springframework.cloud.stream.binder.rocketmq;

import java.util.Collections;
import java.util.Map;

import org.springframework.cloud.stream.binder.rocketmq.utils.CommonUtils;
import org.springframework.core.AttributeAccessor;
import org.springframework.integration.support.ErrorMessageStrategy;
import org.springframework.integration.support.ErrorMessageUtils;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.ErrorMessage;

public class RawRecordHeaderErrorMessageStrategy implements ErrorMessageStrategy {

	@Override
	public ErrorMessage buildErrorMessage(Throwable throwable, AttributeAccessor attributes) {
		Object inputMessage = attributes.getAttribute(ErrorMessageUtils.INPUT_MESSAGE_CONTEXT_KEY);
		Map<String, Object> headers = Collections.singletonMap(CommonUtils.ROCKETMQ_RAW_DATA,
				attributes.getAttribute(CommonUtils.ROCKETMQ_RAW_DATA));
		return  new ErrorMessage(throwable, headers, inputMessage instanceof Message ? (Message<?>) inputMessage : null);
	}

}
