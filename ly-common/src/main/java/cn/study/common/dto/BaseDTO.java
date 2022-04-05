package cn.study.common.dto;

import cn.study.common.model.BaseModel;
import cn.study.common.utils.BeanHelper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * @Author Meteor
 * @Date 2022/3/30 13:59
 * @Description DTO父类 [提供DTO和Entity之间的转换]
 */
@Data
@NoArgsConstructor
public abstract class BaseDTO {
    public <T> T toEntity(Class<T> cls){
        return BeanHelper.copyProperties(this, cls);
    }

    public BaseDTO(BaseModel model) {
        if (model != null) {
            BeanUtils.copyProperties(model, this);
        }
    }
}
