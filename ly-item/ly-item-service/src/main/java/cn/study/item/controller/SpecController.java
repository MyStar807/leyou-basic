package cn.study.item.controller;

import cn.study.item.dto.SpecGroupDTO;
import cn.study.item.dto.SpecParamDTO;
import cn.study.item.model.SpecGroup;
import cn.study.item.service.SpecGroupService;
import cn.study.item.service.SpecParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Meteor
 * @Date 2022/3/31 22:22
 * @Description
 */
@RestController
@RequestMapping("/spec")
public class SpecController {

    @Autowired
    private SpecGroupService groupService;

    @Autowired
    private SpecParamService paramService;

    //  根据分类id查询对应的规格参数组集合
    @GetMapping("/groups/of/category")
    public ResponseEntity<List<SpecGroupDTO>> listGroupByCategory(@RequestParam("id") Long cid) {
        return ResponseEntity.ok(this.groupService.listGroupByCategory(cid));
    }

    // 根据条件查询规格参数
    @GetMapping("/params")
    public ResponseEntity<List<SpecParamDTO>> listParam(@RequestParam(required = false) Long groupId, @RequestParam(required = false) Long categoryId, @RequestParam(required = false) Boolean searching) {
        return ResponseEntity.ok(this.paramService.listParam(groupId, categoryId, searching));
    }

    // 根据分类id查询对应的规格参数组以及组内参数
    @GetMapping("/list")
    public ResponseEntity<List<SpecGroupDTO>> querySpecByCategory(@RequestParam("id")Long cid){
        return ResponseEntity.ok(this.groupService.querySpecByCategory(cid));
    }
}
