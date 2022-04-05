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
 * @Date 2022/3/30 23:21
 * @Description
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpecGroupDTO extends BaseDTO {
    private Long id;
    private Long categoryId;
    private String name;
    private List<SpecParamDTO> params;

    public SpecGroupDTO(BaseModel model) {
        super(model);
    }

    public static <T extends BaseModel> List<SpecGroupDTO> conventModelList(Collection<T> collection) {
        return collection == null ? Collections.emptyList() : collection.stream().map(SpecGroupDTO::new).collect(Collectors.toList());
    }
}
