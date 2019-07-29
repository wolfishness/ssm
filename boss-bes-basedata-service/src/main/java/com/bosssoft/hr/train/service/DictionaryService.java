package com.bosssoft.hr.train.service;

import com.bosssoft.hr.train.pojo.dto.DictionaryPage;
import com.bosssoft.hr.train.pojo.entity.Dictionary;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * dictionary 业务接口
 *
 * @author lujinshan
 * @version 1.0
 * @date 2019.07.25
 */
public  interface DictionaryService {

    /**
     * 保存一个实体，null值也会被保存，不会使用数据库默认值
     *
     * @param dictionary 实体对象
     * @return 返回影响的行数
     */
     int save(Dictionary dictionary);

    /**
     * 保存一个实体，null的属性不会被保存，会使用数据库默认值
     *
     * @param dictionary 实体对象
     * @return 返回影响的行数
     */
     int saveSelective(Dictionary dictionary);

    /**
     * 根据数据库主键保存
     *
     * @param dictionary 实体对象
     * @return 返回影响的行数
     */
     int saveUseGeneratedKeys(Dictionary dictionary);

    /**
     * 查询相关记录
     *
     * @param dictionaryPage 实体对象
     * @return 返回查找到的数据，为list类型，有可能为空
     */
    PageInfo<Dictionary> queryList(DictionaryPage dictionaryPage);

    /**
     * 查询一条记录，如果查询到的记录数多于两条会报错
     *
     * @param dictionary 实体对象
     * @return 返回查找到的数据，一条记录，有可能为空
     */
     Dictionary selectOne(Dictionary dictionary);

    /**
     * 更新数据，根据主键更新属性不为null的值
     *
     * @param dictionary 实体对象
     * @return 返回影响的行数
     */
     int updateByPrimaryKeySelective(Dictionary dictionary);

    /**
     * 更新数据，根据主键更新所有值，包括null值
     *
     * @param dictionary
     * @return
     */
     int updateByPrimaryKey(Dictionary dictionary);

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     *
     * @param dictionary 实体对象
     * @return 返回影响的行数
     */
     int delete(Dictionary dictionary);

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param id 需要删除的Id
     * @return 返回影响的行数
     */
     int deleteByPrimaryKey(long id);

    /**
     * 分页查找
     *
     * @param dictionary 实体对象
     * @param index      分页的页数
     * @return 返回查询到的数据，类型为list，有可能为空
     */
     List<Dictionary> query(Dictionary dictionary, int index);

}
