package cn.study.item.controller;

import cn.study.item.dto.CategoryDTO;
import cn.study.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Meteor
 * @Date 2022/3/31 15:59
 * @Description
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // 根据父id查询对应的分类集合
    @GetMapping("/of/parent")
    public ResponseEntity<List<CategoryDTO>> listCategoryByPid(@RequestParam Long pid) {
        return ResponseEntity.ok(this.categoryService.listCategoryByPid(pid));
    }

    // 根据id查询对应的分类对象
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> queryCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(this.categoryService.queryCategoryById(id));
    }

    // 根据商品分类id的集合查询对应的分类集合但是前端参数，字符串
    @GetMapping("/list")
    public ResponseEntity<List<CategoryDTO>> listCategoryByIds(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(this.categoryService.listCategoryByIds(ids));
    }

    // 根据品牌id查询对应的分类集合
    @GetMapping("/of/brand")
    public ResponseEntity<List<CategoryDTO>> queryCategoryByBrand(@RequestParam("id") Long bid) {
        return ResponseEntity.ok(this.categoryService.queryCategoryByBrand(bid));
    }
}
