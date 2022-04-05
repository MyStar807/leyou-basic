package cn.study.item.dto;

import cn.study.common.dto.BaseDTO;
import cn.study.common.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Meteor
 * @Date 2022/3/30 22:20
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO extends BaseDTO {
    private Long id;
    private String name;
    private String image;
    private Character letter;
    private List<Long> categoryIds;

    public BrandDTO(BaseModel model) {
        super(model);
    }

    // 转换成DTO
    public static <T extends BaseModel> List<BrandDTO> conventModelList(Collection<T> collection){
        return collection == null ? Collections.emptyList() : collection.stream().map(BrandDTO::new).collect(Collectors.toList());
    }
}
