package cn.study.user.model;

import cn.study.common.constants.RegexPatterns;
import cn.study.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author Meteor
 * @Date 2022/3/30 18:31
 * @Description
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_user")
public class User extends BaseModel {

    @TableId
    private Long id;

    @NotNull(message = "用户名不能为空")
    @Length(min = 4, max = 30, message = "用户名长度限定为4-30")
    private String username;

    @NotNull(message = "密码不能为空")
    @Length(min = 4, max = 30, message = "密码长度限定为4-30")
    private String password;

    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = RegexPatterns.PHONE_REGEX, message = "手机号格式不匹配")
    private String phone;

}
