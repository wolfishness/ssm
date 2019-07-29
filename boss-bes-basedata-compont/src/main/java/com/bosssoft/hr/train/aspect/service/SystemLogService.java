package com.bosssoft.hr.train.aspect.service;

import com.bosssoft.hr.train.pojo.entity.SystemLog;

/**
 * 保存日志
 *
 * @author lujinshan
 * @date 2019/7/25 18:37
 */
public interface SystemLogService {
    /**
     * 输出日志信息
     *
     * @param systemLog
     * @return
     */
    boolean save(SystemLog systemLog);
}
