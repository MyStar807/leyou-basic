package cn.study.auth.model;

import cn.study.common.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author Meteor
 * @Date 2022/4/1 0:01
 * @Description
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_client_info")
public class ClientInfo extends BaseModel {
    @TableId
    private Long id;
    private String clientId;
    private String secret;
    private String info;
}
