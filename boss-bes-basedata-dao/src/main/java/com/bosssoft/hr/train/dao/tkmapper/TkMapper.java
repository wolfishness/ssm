package com.bosssoft.hr.train.dao.tkmapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 设置通用Mapper接口
 * @author lujinshan
 * @date 2019.07.28
 * @version 1.0
 * @param <T>
 */
public interface TkMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
