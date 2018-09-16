package org.springframework.cloud.stream.binder.rocketmq.properties;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.stream.binder.HeaderMode;

/**
 * @author js.xie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RocketMQConsumerProperties {

	private boolean autoRebalanceEnabled = true;

	private boolean autoCommitOffset = true;

	private Boolean autoCommitOnError;

	private String startOffset;

	private boolean enableDlq;

	private String dlqName;

	private int recoveryInterval = 5000;
	
	private String tags;

	private Map<String, String> configuration = new HashMap<>();

	private HeaderMode headerMode = HeaderMode.raw;

	public enum StartOffset {
		earliest(-2L),
		latest(-1L);

		private final long referencePoint;

		StartOffset(long referencePoint) {
			this.referencePoint = referencePoint;
		}

		public long getReferencePoint() {
			return this.referencePoint;
		}
	}
}
