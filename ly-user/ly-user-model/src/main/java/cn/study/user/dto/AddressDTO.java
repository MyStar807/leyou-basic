package cn.study.user.dto;

import cn.study.common.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author Meteor
 * @Date 2022/3/30 17:54
 * @Description
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AddressDTO extends BaseDTO {
    /**
     * 收件人
     */
    private String addressee;


    /**
     * 区
     */
    private String district;


    /**
     * 地址自己的id
     */
    private Long id;


    /**
     * 是否为默认值
     */
    private Boolean isDefault;


    /**
     * 手机号
     */
    private String phone;


    /**
     * 邮编
     */
    private String postcode;

    /**
     * 省
     */
    private String province;


    /**
     * 街道
     */
    private String street;


    /**
     * 市
     */
    private String city;


    /**
     * 地址对应的用户的id
     */
    private Long userId;

}
