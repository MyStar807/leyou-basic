package cn.study.item.model;

import cn.study.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author Meteor
 * @Date 2022/3/30 23:42
 * @Description
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_brand")
public class Brand extends BaseModel {
    @TableId
    private Long id;
    private String name;
    private String image;
    private Character letter;
}
