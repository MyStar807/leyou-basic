package cn.study.item.service.impl;


import cn.study.item.dto.SpecParamDTO;
import cn.study.item.mapper.SpecParamMapper;
import cn.study.item.model.SpecParam;
import cn.study.item.service.SpecParamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecParamServiceImpl extends ServiceImpl<SpecParamMapper, SpecParam> implements SpecParamService {
    @Override
    public List<SpecParamDTO> listParam(Long groupId, Long categoryId, Boolean searching) {
        List<SpecParam> specParams = this.query()
                .eq(null!=groupId,"group_id", groupId)
                .eq(null!=categoryId,"category_id",categoryId)
                .eq(null!=searching,"searching",searching)
                .list();
        return SpecParamDTO.conventModelList(specParams);
    }
}