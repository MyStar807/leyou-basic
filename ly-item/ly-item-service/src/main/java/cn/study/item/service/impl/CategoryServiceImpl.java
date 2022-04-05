package cn.study.item.service.impl;


import cn.study.item.dto.CategoryDTO;
import cn.study.item.mapper.CategoryMapper;
import cn.study.item.model.Category;
import cn.study.item.model.CategoryBrand;
import cn.study.item.service.CategoryBrandService;
import cn.study.item.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    CategoryBrandService categoryBrandService;

    @Override
    public List<CategoryDTO> listCategoryByPid(Long pid) {
        return CategoryDTO.conventModelList(this.list(new QueryWrapper<Category>().eq("parent_id", pid)));
    }

    @Override
    public CategoryDTO queryCategoryById(Long id) {
        return new CategoryDTO(getById(id));
    }

    @Override
    public List<CategoryDTO> listCategoryByIds(List<Long> ids) {
        return CategoryDTO.conventModelList(this.listByIds(ids));
    }

    @Override
    public List<CategoryDTO> queryCategoryByBrand(Long bid) {
        List<Long> categoryIds = this.categoryBrandService.query().eq("brand_id", bid).list().stream().map(CategoryBrand::getCategoryId).collect(Collectors.toList());
        return CollectionUtils.isNotEmpty(categoryIds) ? listCategoryByIds(categoryIds) : null;
    }
}
