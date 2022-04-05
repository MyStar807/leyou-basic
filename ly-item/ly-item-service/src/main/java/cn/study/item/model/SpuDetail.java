package cn.study.item.model;

import cn.study.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("tb_spu_detail")
public class SpuDetail extends BaseModel {

    @TableId(type = IdType.INPUT)
    private Long spuId;// 对应的SPU的id
    private String description;// 商品描述
    private String packingList;// 包装清单
    private String afterService;// 售后服务
    private String specification;// 规格参数值

}
