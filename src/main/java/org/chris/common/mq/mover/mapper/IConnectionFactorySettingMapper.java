package org.chris.common.mq.mover.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.chris.common.mq.mover.domain.dos.ConnectionFactorySetting;
import org.chris.common.mq.mover.domain.query.ConnectionFactorySettingQuery;

import java.util.List;

/**
 *
 */
@Mapper
public interface IConnectionFactorySettingMapper {
    /**
     * @param record
     * @return
     */
    int insert(ConnectionFactorySetting record);

    /**
     * @param record
     * @return
     */
    int insertSelective(ConnectionFactorySetting record);

    /**
     * @param monitorList
     * @return
     */
    List<ConnectionFactorySetting> findByAll(ConnectionFactorySettingQuery monitorList);


}