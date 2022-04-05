package cn.study.item.model;

import cn.study.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author Meteor
 * @Date 2022/3/30 23:45
 * @Description
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tb_sku")
public class Sku extends BaseModel {

    @TableId
    private Long id;
    private Long spuId;
    private String title;
    private String images;
    private Long price;
    private Long sold;
    private Integer stock;
    private String specialSpec;
    private String indexes;
    private Boolean saleable;

}
