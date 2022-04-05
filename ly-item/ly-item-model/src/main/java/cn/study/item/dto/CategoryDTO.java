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
 * @Date 2022/3/30 23:02
 * @Description
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CategoryDTO extends BaseDTO {
    private Long id;
    private String name;
    private Long parentId;
    private Boolean isParent;
    private Integer sort;

    public CategoryDTO(BaseModel model) {
        super(model);
    }

    public static <T extends BaseModel> List<CategoryDTO> conventModelList(Collection<T> collection) {
        return collection == null ? Collections.emptyList() : collection.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }
}
