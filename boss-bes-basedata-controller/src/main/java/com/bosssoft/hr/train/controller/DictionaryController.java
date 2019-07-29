package com.bosssoft.hr.train.controller;

import com.alibaba.fastjson.JSONObject;
import com.bosssoft.hr.train.aspect.anno.SysLog;
import com.bosssoft.hr.train.pojo.dto.DictionaryPage;
import com.bosssoft.hr.train.pojo.entity.Dictionary;
import com.bosssoft.hr.train.service.DictionaryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xml.internal.serialize.Method;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据字典控制类
 *
 * @author lujinshan
 * @version 1.0
 * @date 2019.07.25
 */
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    /**
     * 实例DictionaryService
     */
    @Resource
    DictionaryService dictionaryService;

    /**
     * 根据主键自增长方式插入数据，插入数据是null值不插入
     *
     * @param dictionary
     * @return -1为没有传入创建人，-2为没有传入字典名称，-3为没有传入字典类型，-4为没有传入字典值，-5为没有传入是否启用标识
     * 0为数据插入失败，1为数据插入成功
     */
    @SysLog("增加")
    @RequestMapping(value = "/saveUseGeneratedKeys", method = RequestMethod.POST)
    public ResponseEntity<?> saveUseGeneratedKeys(@RequestBody Dictionary dictionary) {
        //判断需要的参数是否都有传入
        JSONObject jsonObject = judgeDictionary(dictionary);
        if (!StringUtils.isEmpty(jsonObject)) {
            return ResponseEntity.ok(jsonObject);
        }
        jsonObject = new JSONObject();
        //判断是否有传递创建人进来
        if (StringUtils.isEmpty(dictionary.getCreatedBy())) {
            jsonObject.put("errCode", -1);
            jsonObject.put("errMessage", "unable to create without createdBy");
            return ResponseEntity.ok(jsonObject);
        }
        //执行插入语句，主键id自增长
        int result = dictionaryService.saveUseGeneratedKeys(dictionary);
        //判断数据是否插入成功
        if (result == 0) {
            jsonObject.put("errCode", 0);
            jsonObject.put("errMessage", "插入失败");
            return ResponseEntity.ok(jsonObject);
        }
        jsonObject.put("errCode", 1);
        jsonObject.put("errMessage", "成功插入");
        return ResponseEntity.ok(jsonObject);
    }

    /**
     * 根据主键停用记录
     *
     * @param dictionary
     * @return -1为没有传入主键，-2为没有传入字典名称，-3为没有传入字典类型，-4为没有传入字典值，-5为没有传入是否启用标识
     * 0为停用失败，1为停用成功，-6为没有查询到相应的记录
     */
    @SysLog("删除")
    @RequestMapping("/deleteByPrimaryKey")
    public ResponseEntity<?> deleteByPrimaryKey(@RequestBody Dictionary dictionary) {
        //判断需要的参数是否都有传入
        JSONObject jsonObject = judgeDictionary(dictionary);
        if (!StringUtils.isEmpty(jsonObject)) {
            return ResponseEntity.ok(jsonObject);
        }
        //判断是否有传递主键进来
        if (StringUtils.isEmpty(dictionary.getCategoryId())) {
            jsonObject.put("errCode", -1);
            jsonObject.put("errMessage", "unable to delete without categoryId");
            return ResponseEntity.ok(jsonObject);
        }
        jsonObject = new JSONObject();
        //执行修改语句
        int result = dictionaryService.delete(dictionary);
        if (result == -1) {
            jsonObject.put("errCode", -6);
            jsonObject.put("errMessage", "没有查询到相应的记录");
            return ResponseEntity.ok(jsonObject);
        }
        //判断数据是否插入成功
        if (result == 0) {
            jsonObject.put("errCode", 0);
            jsonObject.put("errMessage", "停用失败");
            return ResponseEntity.ok(jsonObject);
        }
        jsonObject.put("errCode", 1);
        jsonObject.put("errMessage", "成功停用");
        return ResponseEntity.ok(jsonObject);
    }

    /**
     * 根据主键修改记录
     *
     * @param dictionary
     * @return -1为没有传入主键，-2为没有传入字典名称，-3为没有传入字典类型，-4为没有传入字典值，-5为没有传入是否启用标识
     * 0为修改失败，1为修改成功
     */
    @SysLog("修改")
    @RequestMapping("/updateByPrimaryKeySelective")
    public ResponseEntity<?> updateByPrimaryKeySelective(@RequestBody Dictionary dictionary) {
        //判断需要的参数是否都有传入
        JSONObject jsonObject = judgeDictionary(dictionary);
        if (!StringUtils.isEmpty(jsonObject)) {
            return ResponseEntity.ok(jsonObject);
        }
        //判断是否有传递主键进来
        if (StringUtils.isEmpty(dictionary.getCategoryId())) {
            jsonObject.put("errCode", -1);
            jsonObject.put("errMessage", "unable to delete without categoryId");
            return ResponseEntity.ok(jsonObject);
        }
        jsonObject = new JSONObject();
        //执行修改语句
        int result = dictionaryService.updateByPrimaryKeySelective(dictionary);
        //判断数据是否插入成功
        if (result == 0) {
            jsonObject.put("errCode", 0);
            jsonObject.put("errMessage", "修改失败");
            return ResponseEntity.ok(jsonObject);
        }
        jsonObject.put("errCode", 1);
        jsonObject.put("errMessage", "成功修改");
        return ResponseEntity.ok(jsonObject);
    }


    /**
     * 查询对象
     *
     * @return -1为没有找到数据，1为找到数据
     */
    @SysLog("查询")
    @RequestMapping("/queryList")
    public ResponseEntity<?> queryList(@RequestBody DictionaryPage dictionaryPage) {
        //查询相关数据
        PageInfo<Dictionary> dictionaryList = dictionaryService.queryList(dictionaryPage);
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(dictionaryList.getList())) {
            jsonObject.put("errCode", -1);
            jsonObject.put("errMessage", "没有找到数据");
            return ResponseEntity.ok(jsonObject);
        }
        jsonObject.put("errCode", 1);
        jsonObject.put("errMessage", "success");
        jsonObject.put("data", dictionaryList);
        ResponseEntity<?> responseEntity = ResponseEntity.ok(jsonObject);
        Object object = responseEntity.getBody();
        return ResponseEntity.ok(jsonObject);
    }


    /**
     * 判断需要的参数是否都有传入
     * @param dictionary
     * @return -2为没有传入字典名称，-3为没有传入字典类型，-4为没有传入字典值，-5为没有传入是否启用标识
     */
    public static JSONObject judgeDictionary(Dictionary dictionary) {
        JSONObject jsonObject = new JSONObject();
        //判断是否传递字典名称
        if (StringUtils.isEmpty(dictionary.getName())) {
            jsonObject.put("errCode", -2);
            jsonObject.put("errMessage", "empty name");
            return jsonObject;
        }
        //判断是否传递字典类型
        if (StringUtils.isEmpty(dictionary.getCategory())) {
            jsonObject.put("errCode", -3);
            jsonObject.put("errMessage", "empty category");
            return jsonObject;
        }
        //判断是否传递字典值
        if (StringUtils.isEmpty(dictionary.getValue())) {
            jsonObject.put("errCode", -4);
            jsonObject.put("errMessage", "empty value");
            return jsonObject;
        }
        //判断是否传递启用标识
        if (StringUtils.isEmpty(dictionary.getStatus())) {
            jsonObject.put("errCode", -5);
            jsonObject.put("errMessage", "empty status");
            return jsonObject;
        }
        return null;
    }


}
