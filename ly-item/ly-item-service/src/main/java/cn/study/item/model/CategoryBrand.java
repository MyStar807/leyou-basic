package cn.study.item.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author Meteor
 * @Date 2022/3/30 23:43
 * @Description
 */
@Data
@AllArgsConstructor(staticName = "of")
@TableName("tb_category_brand")
public class CategoryBrand {

    // IdType.INPUT，代表主键采用自己填写而不是自增长。
    @TableId(type = IdType.INPUT)
    private Long categoryId;
    @TableId(type = IdType.INPUT)
    private Long brandId;

}
