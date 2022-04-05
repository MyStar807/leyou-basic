package cn.study.item.service.impl;

import cn.study.item.dto.SpecGroupDTO;
import cn.study.item.dto.SpecParamDTO;
import cn.study.item.mapper.SpecGroupMapper;
import cn.study.item.model.SpecGroup;
import cn.study.item.service.SpecGroupService;
import cn.study.item.service.SpecParamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SpecGroupServiceImpl extends ServiceImpl<SpecGroupMapper, SpecGroup> implements SpecGroupService {

    @Autowired
    SpecGroupService specGroupServer;
    @Autowired
    private SpecParamService paramService;

    @Override
    public List<SpecGroupDTO> listGroupByCategory(Long cid) {
        return SpecGroupDTO.conventModelList(this.query().eq("category_id", cid).list());
    }

    @Override
    public List<SpecGroupDTO> querySpecByCategory(Long cid) {
        // 查出分组
        List<SpecGroupDTO> specGroupDTOS = this.listGroupByCategory(cid);
        // 查出参数
        List<SpecParamDTO> specParamDTOS = this.paramService.listParam(null, cid, null);
        // 转为map
        Map<Long, List<SpecParamDTO>> specParamMap = specParamDTOS.stream().collect(Collectors.groupingBy(specParamDTO -> specParamDTO.getGroupId()));
        // 填充参数
        specGroupDTOS.forEach(specGroupDTO -> specGroupDTO.setParams(specParamMap.get(specGroupDTO.getId())));
        return specGroupDTOS;
    }
}