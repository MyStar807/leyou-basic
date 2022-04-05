package cn.study.common.model;

import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * @Author Meteor
 * @Date 2022/3/30 14:07
 * @Description 实体类的父类
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BaseModel {
    private Date createTime;
    private Date updateTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
