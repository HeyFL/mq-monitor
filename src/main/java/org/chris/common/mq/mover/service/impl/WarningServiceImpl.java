
package org.chris.common.mq.mover.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.chris.common.mq.mover.domain.bo.QueueWarnInfo;
import org.chris.common.mq.mover.service.IWarningService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * @author caizq
 * @date 2019/6/18
 * @since v1.0.0
 */
@Slf4j
@Service
public class WarningServiceImpl implements IWarningService {
    @Override
    public List<String> getWarnMsg(List<QueueWarnInfo> queueWarnInfoList) {

        List<String> result = new LinkedList<>();
        for (QueueWarnInfo queue : queueWarnInfoList) {
            if (queue.getMsgCount() > queue.getWarnThreshold()) {
                //大于阈值 --> 进行预警
                if (StringUtils.isEmpty(queue.getWarnMsgFormat())) {
                    queue.setWarnMsgFormat("VH：{0} 队列：{1} 消息当前消息条数{2}  超过阈值：{3} 请及时排查问题");
                }
                String warnMsg = MessageFormat.format(queue.getWarnMsgFormat(), queue.getVirtualHost(), queue.getQueueName(), queue.getMsgCount(), queue.getWarnThreshold());
                result.add(warnMsg);

            }
        }
        return result;
    }

    @Override
    public void doWarn(List<String> warnMsgs) {

        if (CollectionUtils.isEmpty(warnMsgs)) {
            return;
        }

        for (String warnMsg : warnMsgs) {
            //TODO 这里改为邮件、钉钉等消息通知到对应的人
            System.out.println(warnMsg);
        }
    }
}
