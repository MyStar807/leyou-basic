package cn.study.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Meteor
 * @Date 2022/3/31 23:21
 * @Description
 */
@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class UserDetail {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
}