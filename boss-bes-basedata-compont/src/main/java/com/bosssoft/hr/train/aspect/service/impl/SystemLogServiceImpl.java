package com.bosssoft.hr.train.aspect.service.impl;

import com.bosssoft.hr.train.aspect.service.SystemLogService;
import com.bosssoft.hr.train.pojo.entity.SystemLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * 保存日志
 *
 * @author lujinshan
 * @date 2019/7/25 18:37
 */
@Slf4j
@Service
public class SystemLogServiceImpl implements SystemLogService {
    /**
     * 声明日志对象
     */
    private Logger logger = Logger.getLogger(SystemLogServiceImpl.class);

    /**
     * 输出日志信息
     *
     * @param systemLog
     * @return
     */
    @Override
    public boolean save(SystemLog systemLog) {
        logger.info(systemLog.toString());
        return true;
    }
}
