package cn.study.item.controller;

import cn.study.common.dto.PageDTO;
import cn.study.item.dto.BrandDTO;
import cn.study.item.model.Brand;
import cn.study.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Meteor
 * @Date 2022/3/31 13:43
 * @Description
 */
@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    // 品牌分页
    @GetMapping("/page")
    public ResponseEntity<PageDTO<BrandDTO>> pageQuery(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer rows, @RequestParam(required = false) String key) {
        return ResponseEntity.ok(brandService.pageQuery(page,rows,key));
    }

    // 品牌新增
    @PostMapping
    public ResponseEntity<Void> addBrand(BrandDTO brandDTO) {
        this.brandService.addBrand(brandDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 品牌修改
    @PutMapping
    public ResponseEntity<Void> updateBrand(BrandDTO brandDTO){
        this.brandService.updateBrand(brandDTO);
        return ResponseEntity.ok().build();
    }

    // 根据品牌id查询对应的品牌对象
    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO> queryBrandById(
            @PathVariable("id")Long id){
        return ResponseEntity.ok(this.brandService.queryBrandById(id));
    }

    // 根据id的集合查询对应的品牌集合
    @GetMapping("/list")
    public ResponseEntity<List<BrandDTO>> listByBrandIds(@RequestParam List<Long> ids){
        return ResponseEntity.ok(brandService.listByBrandIds(ids));
    }

    // 根据分类查询对应的品牌集合
    @GetMapping("/of/category")
    public ResponseEntity<List<BrandDTO>> listBrandByCategory(@RequestParam("id") Long cid){
        return ResponseEntity.ok(this.brandService.listBrandByCategory(cid));
    }
}
