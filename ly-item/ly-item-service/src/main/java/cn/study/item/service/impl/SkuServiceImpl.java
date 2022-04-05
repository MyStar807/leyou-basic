package cn.study.item.service.impl;


import cn.study.item.mapper.SkuMapper;
import cn.study.item.model.Sku;
import cn.study.item.service.SkuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

    @Override
    public void plusStock(Map<Long, Integer> detailsMap) {
        executeBatch(sqlSession -> {
            //循环想sqlSession中添加statement，myBatis，map传参
            detailsMap.entrySet().forEach(cart -> {
                Map<String,Object> paramMap = new HashMap<>();
                paramMap.put("skuId",cart.getKey());
                paramMap.put("num",cart.getValue());
                sqlSession.update("cn.study.item.mapper.SkuMapper.minusStock",paramMap);
            });
            sqlSession.flushStatements();
        });
    }

    @Override
    public void minusStock(Map<Long, Integer> detailsMap) {
        executeBatch(sqlSession -> {
            //循环想sqlSession中添加statement，myBatis，map传参
            detailsMap.entrySet().forEach(cart -> {
                Map<String,Object> paramMap = new HashMap<>();
                paramMap.put("skuId",cart.getKey());
                paramMap.put("num",cart.getValue());
                sqlSession.update("cn.study.item.mapper.SkuMapper.plusStock",paramMap);
            });
            sqlSession.flushStatements();
        });
    }
}
