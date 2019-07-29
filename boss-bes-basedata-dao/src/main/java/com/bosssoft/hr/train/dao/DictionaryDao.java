package com.bosssoft.hr.train.dao;

import com.bosssoft.hr.train.dao.tkmapper.TkMapper;
import com.bosssoft.hr.train.pojo.entity.Dictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * 设置dictionaryDao接口
 * @author lujinshan
 * @date 2019.07.25
 * @version 1.0
 */
@Mapper
@Component
public interface DictionaryDao extends TkMapper<Dictionary> {

}
