package com.bosssoft.hr.train.pojo.entity;

/**
 * 日志输出实体
 * @author lujnshan
 * @version 1.0
 * @date 2019.07.25
 */
public class SystemLog {
    /**
     * 类名
     */
    private String className;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 参数
     */
    private String params;
    /**
     * 运行时间
     */
    private Long exeuTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private String createDate;

    public SystemLog() {

    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Long getExeuTime() {
        return exeuTime;
    }

    public void setExeuTime(Long exeuTime) {
        this.exeuTime = exeuTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "SystemLog{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params='" + params + '\'' +
                ", exeuTime=" + exeuTime +
                ", remark='" + remark + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
