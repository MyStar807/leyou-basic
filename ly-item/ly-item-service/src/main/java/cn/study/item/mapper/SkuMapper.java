package cn.study.item.mapper;


import cn.study.item.model.Sku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

/**
 * 库存自增自减
 */
public interface SkuMapper extends BaseMapper<Sku> {

    @Update("update tb_sku set stock = stock - #{num},sold = sold + #{num} where id = #{skuId}")
    Integer minusStock(Map<String,Object> param);

    @Update("update tb_sku set stock = stock + #{num},sold = sold - #{num} where id = #{skuId}")
    Integer plusStock(Map<String,Object> param);

}