package cn.study.item.model;

import cn.study.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author Meteor
 * @Date 2022/3/30 23:47
 * @Description
 */
@Data
@TableName("tb_spec_param")
@EqualsAndHashCode(callSuper = false)
public class SpecParam extends BaseModel {
    @TableId
    private Long id;
    private Long categoryId;
    private Long groupId;
    @TableField("`name`")
    private String name;
    @TableField("`numeric`")
    private Boolean numeric;
    private String unit;
    private Boolean generic;
    private Boolean searching;
    private String segments;
    private String options;
}
