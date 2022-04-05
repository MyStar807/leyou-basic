package cn.study.item.model;

import cn.study.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author Meteor
 * @Date 2022/3/30 23:46
 * @Description
 */
@Data
@TableName("tb_spec_group")
@EqualsAndHashCode(callSuper = false)
public class SpecGroup extends BaseModel {

    @TableId
    private Long id;
    private Long categoryId;
    private String name;

}
