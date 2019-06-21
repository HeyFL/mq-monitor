package org.chris.common.mq.mover.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.chris.common.mq.mover.domain.dos.MonitorQueue;

@Mapper
public interface IMonitorQueueMapper {
    int insert(MonitorQueue record);

    int insertSelective(MonitorQueue record);

    List<MonitorQueue> findByAll(MonitorQueue monitorQueue);

}