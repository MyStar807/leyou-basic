package cn.study.item.service;


import cn.study.item.model.Sku;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface SkuService extends IService<Sku> {
    void minusStock(Map<Long, Integer> cartsMap);

    void plusStock(Map<Long, Integer> detailsMap);
}
