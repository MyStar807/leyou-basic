package cn.study.item.service;
import cn.study.item.dto.SpecParamDTO;
import cn.study.item.model.SpecParam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface SpecParamService extends IService<SpecParam> {
    List<SpecParamDTO> listParam(Long groupId, Long categoryId,Boolean searching);
}