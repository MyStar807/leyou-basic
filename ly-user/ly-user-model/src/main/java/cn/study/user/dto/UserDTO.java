package cn.study.user.dto;

import cn.study.common.dto.BaseDTO;
import cn.study.common.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author Meteor
 * @Date 2022/3/30 17:55
 * @Description
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BaseDTO {
    private Long id;
    private String phone;
    private String username;

    public UserDTO(BaseModel model){
        super(model);
    }
}
