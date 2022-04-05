package cn.study.item.service.impl;

import cn.study.common.dto.PageDTO;
import cn.study.item.dto.BrandDTO;
import cn.study.item.mapper.BrandMapper;
import cn.study.item.model.Brand;
import cn.study.item.model.Category;
import cn.study.item.model.CategoryBrand;
import cn.study.item.service.BrandService;
import cn.study.item.service.CategoryBrandService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper,Brand> implements BrandService {

    @Autowired
    CategoryBrandService categoryBrandService;

    // 分页查询
    @Override
    public PageDTO<BrandDTO> pageQuery(Integer page, Integer rows, String key) {
        //1.创建分页对象
        IPage<Brand> iPage = new Page<>(page, rows);
        //2.用key作为sql生成的条件
        boolean condition = StringUtils.isNotBlank(key);
        this.page(iPage, new QueryWrapper<Brand>().like(condition, "name", key).or().eq(condition, "letter", key));
        //3.结果封装
        return new PageDTO<>(iPage.getTotal(),iPage.getPages(),BrandDTO.conventModelList(iPage.getRecords()));
    }

    @Override
    @Transactional
    public void addBrand(BrandDTO brandDTO) {
        Brand brand = brandDTO.toEntity(Brand.class);
        this.save(brand);
        // 新建中间表关系
        this.saveCategoryBrand(brandDTO.getCategoryIds(), brand.getId());
    }

    @Override
    public void updateBrand(BrandDTO brandDTO) {
        Brand brand = brandDTO.toEntity(Brand.class);
        this.updateById(brand);
        // TODO 清除中间表信息 可优化判断是否修改过种类字段进行优化
        //1.删除中间表关系
        this.categoryBrandService.remove(new QueryWrapper<CategoryBrand>().eq("brand_id", brand.getId()));
        //2.重建中间表关系
        this.saveCategoryBrand(brandDTO.getCategoryIds(), brandDTO.getId());
    }

    @Override
    public BrandDTO queryBrandById(Long id) {
        return new BrandDTO(this.getById(id));
    }

    @Override
    public List<BrandDTO> listByBrandIds(List<Long> ids) {
        return BrandDTO.conventModelList(this.listByIds(ids));
    }

    @Override
    public List<BrandDTO> listBrandByCategory(Long cid) {
        List<Long> brandIds = this.categoryBrandService.query().eq("category_id", cid).list().stream().map(CategoryBrand::getBrandId).collect(Collectors.toList());
        return BrandDTO.conventModelList(this.listByIds(brandIds));
    }












    public List<BrandDTO> brandListByCategoryIds(List<Long> ids) {
        // 获取中间表bradId 遍历查询
        List<Long> brandIds = this.categoryBrandService.listByIds(ids).stream().map(CategoryBrand::getBrandId).collect(Collectors.toList());
        return BrandDTO.conventModelList(this.listByIds(brandIds));
    }
    private void saveCategoryBrand(List<Long> categoryIds, Long id) {
        //1.将参数转换成中间表对象
        List<CategoryBrand> categoryBrandList = categoryIds.stream().map(cid -> CategoryBrand.of(cid, id)).collect(Collectors.toList());
        //2.批量保存
        this.categoryBrandService.saveBatch(categoryBrandList);
    }

}
