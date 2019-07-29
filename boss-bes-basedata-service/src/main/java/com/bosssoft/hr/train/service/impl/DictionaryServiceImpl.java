package com.bosssoft.hr.train.service.impl;

import com.bosssoft.hr.train.dao.DictionaryDao;
import com.bosssoft.hr.train.pojo.dto.DictionaryPage;
import com.bosssoft.hr.train.pojo.entity.Dictionary;
import com.bosssoft.hr.train.service.DictionaryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * dictionary 业务
 *
 * @author lujinshan
 * @version 1.0
 * @date 2019.07.25
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Resource
    DictionaryDao dictionaryDao;
    /**
     * 每一页的记录数
     */
    private static final int PAGE_SIZE = 10;

    /**
     * 保存一个实体，null值也会被保存，不会使用数据库默认值
     *
     * @param dictionary 实体对象
     * @return 返回影响的行数
     */
    @Override
    public int save(Dictionary dictionary) {
        return dictionaryDao.insert(dictionary);
    }

    /**
     * 保存一个实体，null的属性不会被保存，会使用数据库默认值
     *
     * @param dictionary 实体对象
     * @return 返回影响的行数
     */
    @Override
    public int saveSelective(Dictionary dictionary) {
        return dictionaryDao.insertSelective(dictionary);
    }

    /**
     * 根据数据库主键保存
     *
     * @param dictionary 实体对象
     * @return 返回影响的行数
     */
    @Override
    public int saveUseGeneratedKeys(Dictionary dictionary) {
        Timestamp createdTime = new Timestamp(System.currentTimeMillis());
        dictionary.setCreatedTime(createdTime);
        dictionary.setVersion(1L);
        System.out.println(dictionary);
        return dictionaryDao.insertUseGeneratedKeys(dictionary);
    }

    /**
     * 查询相关记录
     *
     * @param dictionaryPage 实体对象
     * @return 返回查找到的数据，为list类型，有可能为空
     */
    @Override
    public PageInfo<Dictionary> queryList(DictionaryPage dictionaryPage) {
        //条件查询
        Example example = new Example(Dictionary.class);
        Example.Criteria criteria = example.createCriteria();
        //判断是否有传入name
        if (!StringUtils.isEmpty(dictionaryPage.getName())) {
            criteria.andLike("name", "%" + dictionaryPage.getName() + "%");
        }
        //判断是否有传入category
        if (!StringUtils.isEmpty(dictionaryPage.getCategory())) {
            criteria.andLike("category", "%" + dictionaryPage.getCategory() + "%");
        }
        //判断是否有传入status
        if (!StringUtils.isEmpty(dictionaryPage.getStatus())) {
            //判断传入的标识
            switch (dictionaryPage.getStatus()) {
                case "停用":
                    criteria.andEqualTo("status", 0);
                    break;
                case "启用":
                    criteria.andEqualTo("status", 1);
                    break;
                default:
                    break;
            }
        }
        //计算分页查询的条件
        PageHelper.startPage(dictionaryPage.getIndex(),PAGE_SIZE);
        List<Dictionary> dictionaryList = dictionaryDao.selectByExample(example);
        //查询相关记录
        PageInfo<Dictionary> dictionaryPages = new PageInfo<>(dictionaryList);
        return dictionaryPages;
    }

    /**
     * 查询一条记录，如果查询到的记录数多于两条会报错
     *
     * @param dictionary 实体对象
     * @return 返回查找到的数据，一条记录，有可能为空
     */
    @Override
    public Dictionary selectOne(Dictionary dictionary) {

        return dictionaryDao.selectOne(dictionary);
    }

    /**
     * 更新数据，根据主键更新属性不为null的值
     *
     * @param dictionary 实体对象
     * @return 返回影响的行数
     */
    @Override
    public int updateByPrimaryKeySelective(Dictionary dictionary) {
        Timestamp updatedTime = new Timestamp(System.currentTimeMillis());
        dictionary.setUpdatedTime(updatedTime);
        return dictionaryDao.updateByPrimaryKeySelective(dictionary);
    }

    /**
     * 更新数据，根据主键更新所有值，包括null值
     *
     * @param dictionary
     * @return
     */
    @Override
    public int updateByPrimaryKey(Dictionary dictionary) {
        return dictionaryDao.updateByPrimaryKey(dictionary);
    }

    /**
     * 将启用标识修改为0
     * 根据实体属性作为条件进行修改，查询条件使用等号
     *
     * @param dictionary 实体对象
     * @return 返回影响的行数
     */
    @Override
    public int delete(Dictionary dictionary) {
        //条件查询
        Example example = new Example(Dictionary.class);
        Example.Criteria criteria = example.createCriteria();
        //判断是否有传入dictionary
        if (StringUtils.isEmpty(dictionary)) {
            return -1;
        }
        //设置查询条件
        criteria.andEqualTo("name", dictionary.getName());
        criteria.andEqualTo("categoryId",dictionary.getCategoryId());
        criteria.andEqualTo("category",dictionary.getCategory());
        criteria.andEqualTo("status",dictionary.getStatus());
        criteria.andEqualTo("value",dictionary.getValue());
        //修改指定记录为停用
        dictionary.setStatus(0);
        //设置修改时间
        Timestamp updatedTime = new Timestamp(System.currentTimeMillis());
        dictionary.setUpdatedTime(updatedTime);
        return dictionaryDao.updateByExampleSelective(dictionary,example);

    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param id 需要删除的Id
     * @return 返回影响的行数
     */
    @Override
    public int deleteByPrimaryKey(long id) {
        return dictionaryDao.deleteByPrimaryKey(id);
    }

    /**
     * 分页查找
     *
     * @param dictionary 实体对象
     * @param index      分页的页数
     * @return 返回查询到的数据，类型为list，有可能为空
     */
    @Override
    public List<Dictionary> query(Dictionary dictionary, int index) {
        RowBounds rowBounds = new RowBounds(index, 100);
        return dictionaryDao.selectByRowBounds(dictionary, rowBounds);
    }

}
