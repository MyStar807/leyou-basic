package cn.study.search.service;

import cn.study.item.dto.SpuDTO;
import cn.study.search.model.Goods;

/**
 * @Author Meteor
 * @Date 2022/4/3 11:40
 * @Description
 */
public interface IndexService {
    void loadData();

    Goods buildGoods(SpuDTO spuDTO);

    void deleteById(Long id);

    void createIndexById(Long id);
}
