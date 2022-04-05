package cn.study.item.service;


import cn.study.common.dto.PageDTO;
import cn.study.item.dto.SkuDTO;
import cn.study.item.dto.SpecParamDTO;
import cn.study.item.dto.SpuDTO;
import cn.study.item.dto.SpuDetailDTO;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    PageDTO<SpuDTO> spuPageQuery(Integer page, Integer rows, Long cid, Long bid, Boolean saleable, Long id);

    void addGoods(SpuDTO spuDTO);

    void modifySaleable(Long id, Boolean saleable);

    SpuDTO queryGoodsById(Long id);

    void updateGoods(SpuDTO spuDTO);

    List<SkuDTO> listSkuByIds(List<Long> ids);

    List<SkuDTO> listSkuBySpu(Long id);

    SpuDetailDTO querySpuDetailById(Long spuId);

    SpuDTO querySpuById(Long id);

    List<SpecParamDTO> querySpecParamValue(Long id, Boolean searching);

    void minusStock(Map<Long, Integer> cartsMap);

    void plusStock(Map<Long, Integer> detailsMap);
}
