package cn.study.item.service.impl;


import cn.study.item.mapper.CategoryBrandMapper;
import cn.study.item.model.CategoryBrand;
import cn.study.item.service.CategoryBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CategoryBrandServiceImpl extends ServiceImpl<CategoryBrandMapper, CategoryBrand> implements CategoryBrandService {
}
