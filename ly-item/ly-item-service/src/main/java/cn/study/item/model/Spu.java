package cn.study.item.model;

import cn.study.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author Meteor
 * @Date 2022/3/30 23:48
 * @Description
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_spu")
public class Spu extends BaseModel {

    @TableId
    private Long id;
    private Long brandId;
    private Long cid1;// 1级类目
    private Long cid2;// 2级类目
    private Long cid3;// 3级类目
    private String name;// 商品名称
    private String title;// 搜索标题
    private Boolean saleable;// 是否上架

}
