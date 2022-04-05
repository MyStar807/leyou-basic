package cn.study.item.service.impl;


import cn.study.common.dto.PageDTO;
import cn.study.common.exception.LyException;
import cn.study.common.utils.JsonUtils;
import cn.study.item.dto.SkuDTO;
import cn.study.item.dto.SpecParamDTO;
import cn.study.item.dto.SpuDTO;
import cn.study.item.dto.SpuDetailDTO;
import cn.study.item.model.Category;
import cn.study.item.model.Sku;
import cn.study.item.model.Spu;
import cn.study.item.model.SpuDetail;
import cn.study.item.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    SpuService spuService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SpuDetailService detailService;
    @Autowired
    private SkuService skuService;
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private SpecParamService paramService;

    // spu + brandName + categoryName
    @Override
    public PageDTO<SpuDTO> spuPageQuery(Integer page, Integer rows, Long cid, Long bid, Boolean saleable, Long id) {
        IPage<Spu> iPage = new Page<>();
        // 动态sql
        this.spuService.page(iPage, new QueryWrapper<Spu>()
                .eq(null != cid, "cid3", cid)
                .eq(null != bid, "brand_id", bid)
                .eq(saleable != null, "saleable", saleable)
                .eq(null != id, "id", id)
        );
        List<SpuDTO> spuDTOs = SpuDTO.conventModelList(iPage.getRecords());
        spuDTOs.forEach(spuDTO -> {
            spuDTO.setBrandName(this.brandService.getById(spuDTO.getBrandId()).getName());
            //TODO 考虑缓存问题，
            String names = this.categoryService.listByIds(spuDTO.getCategoryIds())
                    .stream().map(Category::getName)
                    .collect(Collectors.joining("/"));
            spuDTO.setCategoryName(names);
        });
        return new PageDTO<>(iPage.getTotal(), iPage.getPages(), spuDTOs);
    }

    @Override
    @Transactional
    public void addGoods(SpuDTO spuDTO) {
        if (CollectionUtils.isEmpty(spuDTO.getSkus())) {
            throw new LyException(400, "sku不能为空");
        }
        //保存spu，并主键回显
        Spu spu = spuDTO.toEntity(Spu.class);
        this.spuService.save(spu);
        log.info("保存spu成功");

        SpuDetail spuDetail = spuDTO.getSpuDetail().toEntity(SpuDetail.class);
        spuDetail.setSpuId(spu.getId());
        this.detailService.save(spuDetail);
        log.info("保存spuDetail成功");

        // 处理库存信息
        List<Sku> skus = spuDTO.getSkus().stream().map(skuDTO -> {
            Sku sku = skuDTO.toEntity(Sku.class);
            sku.setSpuId(spu.getId());
            sku.setSaleable(true);
            return sku;
        }).collect(Collectors.toList());
        this.skuService.saveBatch(skus);
        log.info("批量保存sku成功");
    }

    @Override
    public void modifySaleable(Long id, Boolean saleable) {
        // 修改spu 和 sku 的 saleable属性 因为设置了mybatis的null值不改变所以可以传入空值修改
        Spu spu = new Spu();
        spu.setId(id);
        spu.setSaleable(saleable);
        this.spuService.updateById(spu);

        Sku sku = new Sku();
        sku.setSaleable(saleable);

        this.skuService.update(sku, new QueryWrapper<Sku>().eq("spu_id", id));

        // TODO mq发送通知 ly-search 删除或创建es索引
        // String routingKey = saleable ? "item.up" : "item.down";
        // this.amqpTemplate.convertAndSend("jhj", routingKey, id);
    }

    @Override
    public SpuDTO queryGoodsById(Long id) {
        //1.获取spu对象  [使用分页方法获得更多的信息]
        PageDTO<SpuDTO> spuDTOPageDTO = this.spuPageQuery(1, 1, null, null, null, id);
        List<SpuDTO> items = spuDTOPageDTO.getItems();

        if (null == items || 0 == items.size()) {
            throw new LyException(204, "此id查询对应信息为空");
        }
        SpuDTO spuDTO = items.get(0);
        //2.添加spu对象的detail属性  [根据spu id 获得 detail对象 因为实体类中定义类spu_id 为 id所以此时可以getById]
        spuDTO.setSpuDetail(new SpuDetailDTO(this.detailService.getById(id)));
        //3.添加spu对象的sku属性
        spuDTO.setSkus(SkuDTO.conventModelList(this.skuService.query().eq("spu_id", id).list()));
        return spuDTO;
    }

    @Override
    @Transactional
    public void updateGoods(SpuDTO spuDTO) {
        // 修改商品 + 详细信息 + sku  [参数不为空则代表需要修改]
        Long id = spuDTO.getId();
        if (id != null) {
            this.spuService.updateById(spuDTO.toEntity(Spu.class));
        }

        SpuDetailDTO spuDetailDTO = spuDTO.getSpuDetail();
        if (spuDetailDTO != null) {
            this.detailService.updateById(spuDetailDTO.toEntity(SpuDetail.class));
        }

        List<SkuDTO> skuDTOs = spuDTO.getSkus();
        if (null != skuDTOs && 0 != skuDTOs.size()) {
            ///true 修改删除 false 代表删除
            Map<Boolean, List<Sku>> skuMap = skuDTOs.stream().map(skuDTO -> skuDTO.toEntity(Sku.class)).collect(Collectors.groupingBy(sku -> sku.getSaleable() == null));

            List<Sku> toBeAddOrUpdate = skuMap.get(true);
            if (!CollectionUtils.isEmpty(toBeAddOrUpdate)) {
                this.skuService.saveOrUpdateBatch(toBeAddOrUpdate);
            }

            List<Sku> toBeDelete = skuMap.get(false);
            if (!CollectionUtils.isEmpty(toBeDelete)) {
                this.skuService.removeByIds(toBeDelete.stream().map(Sku::getId).collect(Collectors.toList()));
            }
        }
    }

    @Override
    public List<SkuDTO> listSkuByIds(List<Long> ids) {
        return SkuDTO.conventModelList(this.skuService.listByIds(ids));
    }

    @Override
    public List<SkuDTO> listSkuBySpu(Long id) {
        return SkuDTO.conventModelList(this.skuService.query().eq("spu_id", id).list());
    }

    @Override
    public SpuDetailDTO querySpuDetailById(Long spuId) {
        return new SpuDetailDTO(this.detailService.getById(spuId));
    }

    @Override
    public SpuDTO querySpuById(Long id) {
        return new SpuDTO(this.spuService.getById(id));
    }

    @Override
    public List<SpecParamDTO> querySpecParamValue(Long id, Boolean searching) {
        //1.获得分类id
        Long categoryId = this.spuService.getById(id).getCid3();
        //2.查出分类下的参数对象
        List<SpecParamDTO> specParamDTOS = this.paramService.listParam(null, categoryId, searching);
        //3.查出商品的参数json
        String specification = this.detailService.getById(id).getSpecification();
        //4.将json转为map
        Map<Long, Object> paramMap = JsonUtils.nativeRead(specification, new TypeReference<Map<Long, Object>>() {
        });
        //5.设置产品参数值 [查出参数名称没查出当前产品值]
        specParamDTOS.forEach(specParamDTO -> specParamDTO.setValue(paramMap.get(specParamDTO.getId())));
        return specParamDTOS;
    }

    @Override
    public void minusStock(Map<Long, Integer> cartsMap) {
        this.skuService.minusStock(cartsMap);
    }

    @Override
    public void plusStock(Map<Long, Integer> detailsMap) {
        this.skuService.plusStock(detailsMap);
    }
}
