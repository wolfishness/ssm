package com.bosssoft.hr.train.service;

import com.bosssoft.hr.train.dao.DictionaryDao;
import com.bosssoft.hr.train.pojo.dto.DictionaryPage;
import com.bosssoft.hr.train.pojo.entity.Dictionary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DictionaryTest {
    /**
     *  注入DictionaryService对象
     */
    @Resource
    private DictionaryService dictionaryService;


    /**
     * 测试自增长式的插入语句
     * @throws Exception
     */
    @Test
    public void testInsert() throws Exception{
        //设置dictionary对象
        Dictionary dictionary = new Dictionary();
        dictionary.setName("测试");
        dictionary.setCategory("测试");
        dictionary.setValue("测试试卷");
        dictionary.setRemark("备注");
        dictionary.setStatus(1);
        dictionary.setOrgId(1L);
        dictionary.setVersion(1L);
        //主键自增长式增加
        int result = dictionaryService.saveUseGeneratedKeys(dictionary);
        System.out.println(result);
    }

    /**
     * 查询数据
     * @throws Exception
     */
    @Test
    public void testSelect() throws  Exception{
        DictionaryPage dictionaryPage = new DictionaryPage();
        //设置查询的页数为第一页
        dictionaryPage.setIndex(1);
        //只设置页数查询
        List<Dictionary> dictionaryList = dictionaryService.queryList(dictionaryPage);
        System.out.println("只有页数");
        System.out.println(dictionaryList);

        //设置状态+页数查询
        dictionaryPage.setStatus("启用");
        dictionaryList = dictionaryService.queryList(dictionaryPage);
        System.out.println("状态+页数");
        System.out.println(dictionaryList);

        //设置状态+名称+页数查询
        dictionaryPage.setName("测试");
        dictionaryList = dictionaryService.queryList(dictionaryPage);
        System.out.println("状态+名称+页数");
        System.out.println(dictionaryList);

        //设置状态+类型+名称+页数查询
        dictionaryPage.setCategory("测试");
        dictionaryList = dictionaryService.queryList(dictionaryPage);
        System.out.println("状态+类型+名称+页数");
        System.out.println(dictionaryList);

    }

    /**
     * 测试修改
     * @throws Exception
     */
    @Test
    public void testUpdate() throws  Exception{
        Dictionary dictionary = new Dictionary();
        dictionary.setCategoryId(3L);
        //修改状态标识，其他的不修改
        dictionary.setStatus(0);
        int result = dictionaryService.updateByPrimaryKeySelective(dictionary);
        System.out.println("修改结果："+result);
    }

    /**
     * 删除测试
     * @throws Exception
     */
    @Test
    public void testDelete() throws  Exception{
        Dictionary dictionary = new Dictionary();
        dictionary.setCategoryId(4L);
        //伪删除，只是将状态修改为停用
        dictionary.setStatus(0);
        int result = dictionaryService.delete(dictionary);
        System.out.println("修改结果："+result);
    }

}
