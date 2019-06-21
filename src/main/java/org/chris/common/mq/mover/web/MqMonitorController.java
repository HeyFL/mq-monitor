
package org.chris.common.mq.mover.web;

import org.chris.common.mq.mover.domain.bo.QueueInfo;
import org.chris.common.mq.mover.domain.ResponseMsg;
import org.chris.common.mq.mover.service.IMqMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 *
 * @author caizq
 * @date 2018/4/20
 * @since v1.0.0
 */
@RestController
public class MqMonitorController {
    @Autowired
    private IMqMonitorService mqMonitorService;
    @RequestMapping("/getMsgCount")
    public ResponseMsg getMsgCount(@RequestBody @Valid QueueInfo queueInfo) {
        return ResponseMsg.buildSuccessMsg( mqMonitorService.getMsgCount(queueInfo));
    }

    /**
     * @return
     */
    @RequestMapping("/getAllQueueCurrentInfo")
    public ResponseMsg getAllQueueCurrentInfo() {
        return ResponseMsg.buildSuccessMsg( mqMonitorService.getAllQueueCurrentInfo());
    }


    //public static void main(String[] args) {
    //    System.out.println(MessageFormat.format("操作{0},返回{1}", "request","success"));
    //}

}
