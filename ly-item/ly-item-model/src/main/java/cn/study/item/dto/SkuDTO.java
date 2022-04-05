package cn.study.item.dto;

import cn.study.common.dto.BaseDTO;
import cn.study.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Meteor
 * @Date 2022/3/30 23:14
 * @Description
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SkuDTO extends BaseDTO {
    private Long id;
    private Long spuId;
    private String title;
    private String images;
    private Long price;
    private String specialSpec;// 商品特殊规格的键值对
    private String indexes;// 商品特殊规格的下标
    private Boolean saleable;// 是否有效，逻辑删除用
    private Integer stock; // 库存
    private Long sold; // 销量

    public SkuDTO(BaseModel model) {
        super(model);
    }

    public static <T extends BaseModel> List<SkuDTO> conventModelList(Collection<T> collection) {
        return collection == null ? Collections.emptyList() : collection.stream().map(SkuDTO::new).collect(Collectors.toList());
    }
}
