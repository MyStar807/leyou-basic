package cn.study.item.dto;

import cn.study.common.dto.BaseDTO;
import cn.study.common.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Meteor
 * @Date 2022/3/30 23:28
 * @Description
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SpuDTO extends BaseDTO {
    private Long id;
    private Long brandId;
    private Long cid1;// 1级类目
    private Long cid2;// 2级类目
    private Long cid3;// 3级类目
    private String name;// 名称
    private String title;// 标题
    private Boolean saleable;// 是否上架
    private String categoryName; // 商品分类名称拼接
    private String brandName;// 品牌名称

    /**
     * spu下的sku的集合
     */
    private List<SkuDTO> skus;

    /**
     * 商品详情
     */
    private SpuDetailDTO spuDetail;

    @JsonIgnore
    public List<Long> getCategoryIds(){
        return Arrays.asList(cid1, cid2, cid3);
    }

    public SpuDTO(BaseModel model) {
        super(model);
    }

    public static <T extends BaseModel> List<SpuDTO> conventModelList(Collection<T> collection){
        return collection == null ? Collections.emptyList() : collection.stream().map(SpuDTO::new).collect(Collectors.toList());
    }
}
