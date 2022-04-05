package cn.study.item.service;


import cn.study.item.dto.CategoryDTO;
import cn.study.item.model.Category;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CategoryService extends IService<Category> {
    List<CategoryDTO> listCategoryByPid(Long pid);

    CategoryDTO queryCategoryById(Long id);

    List<CategoryDTO> listCategoryByIds(List<Long> ids);

    List<CategoryDTO> queryCategoryByBrand(Long bid);
}
