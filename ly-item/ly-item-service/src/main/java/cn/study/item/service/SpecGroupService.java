package cn.study.item.service;


import cn.study.item.dto.SpecGroupDTO;
import cn.study.item.model.SpecGroup;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SpecGroupService extends IService<SpecGroup> {
    List<SpecGroupDTO> listGroupByCategory(Long cid);

    List<SpecGroupDTO> querySpecByCategory(Long cid);
}