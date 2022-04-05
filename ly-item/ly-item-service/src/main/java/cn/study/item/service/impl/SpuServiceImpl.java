package cn.study.item.service.impl;


import cn.study.item.mapper.SpuMapper;
import cn.study.item.model.Spu;
import cn.study.item.service.SpuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService {
}
