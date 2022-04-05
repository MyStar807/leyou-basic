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
 * @Date 2022/3/30 23:24
 * @Description
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpecParamDTO extends BaseDTO {
    private Long id;
    private Long categoryId;
    private Long groupId;
    private String name;
    private Boolean numeric;
    private String unit;
    private Boolean generic;
    private Boolean searching;
    private String segments;
    private String options;

    private Object value;

    public SpecParamDTO(BaseModel model) {
        super(model);
    }

    public static <T extends BaseModel> List<SpecParamDTO> conventModelList(Collection<T> collection){
        return collection == null ? Collections.emptyList() : collection.stream().map(SpecParamDTO::new).collect(Collectors.toList());
    }
}
