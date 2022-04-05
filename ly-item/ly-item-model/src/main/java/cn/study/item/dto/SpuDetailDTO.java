package cn.study.item.dto;

import cn.study.common.dto.BaseDTO;
import cn.study.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author Meteor
 * @Date 2022/3/30 23:26
 * @Description
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SpuDetailDTO extends BaseDTO {
    private Long spuId;// 对应的SPU的id
    private String description;// 商品描述
    private String packingList;// 包装清单
    private String afterService;// 售后服务
    private String specification;// 规格参数

    public SpuDetailDTO(BaseModel model) {
        super(model);
    }

}
