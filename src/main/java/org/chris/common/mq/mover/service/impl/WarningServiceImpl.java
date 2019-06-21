
package org.chris.common.mq.mover.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.chris.common.mq.mover.domain.bo.QueueWarnInfo;
import org.chris.common.mq.mover.service.IWarningService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;

/**
 * @author caizq
 * @date 2019/6/18
 * @since v1.0.0
 */
@Slf4j
@Service
public class WarningServiceImpl implements IWarningService {
    @Override
    public void warn(QueueWarnInfo queue) {
        if (queue.getMsgCount() > queue.getWarnThreshold()) {
            //大于阈值 --> 进行预警
            if (StringUtils.isEmpty(queue.getWarnMsgFormat())) {
                queue.setWarnMsgFormat("VH：{0} 队列：{1} 消息当前消息条数{2}  超过阈值：{3} 请及时排查问题");
            }
            String warnMsg = MessageFormat.format(queue.getWarnMsgFormat(), queue.getVirtualHost(), queue.getQueueName(), queue.getMsgCount(), queue.getWarnThreshold());

            //TODO 这里改为邮件、钉钉等消息通知到对应的人
            System.out.println(warnMsg);
        }
    }
}
