package cn.study.item.service;

import cn.study.common.dto.PageDTO;
import cn.study.item.dto.BrandDTO;
import cn.study.item.model.Brand;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @Author Meteor
 * @Date 2022/3/31 13:06
 * @Description
 */
public interface BrandService extends IService<Brand> {
    PageDTO<BrandDTO> pageQuery(Integer page, Integer rows, String key);

    void addBrand(BrandDTO brandDTO);

    void updateBrand(BrandDTO brandDTO);

    BrandDTO queryBrandById(Long id);

    List<BrandDTO> listByBrandIds(List<Long> ids);

    List<BrandDTO> listBrandByCategory(Long cid);
}
