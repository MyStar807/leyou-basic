package cn.study.item.service.impl;


import cn.study.item.mapper.SpuDetailMapper;
import cn.study.item.model.SpuDetail;
import cn.study.item.service.SpuDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SpuDetailServiceImpl extends ServiceImpl<SpuDetailMapper, SpuDetail> implements SpuDetailService {
}
