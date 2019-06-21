
package org.chris.common.mq.mover.domain.bo;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author chris-cai
 */
@Data
public class QueueInfo {
    @NotEmpty
    private String queueName;
    @NotEmpty
    private String factoryName;
}
