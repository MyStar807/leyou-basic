package cn.study.common.utils;

import cn.study.common.dto.BaseDTO;
import org.springframework.beans.BeanUtils;

/**
 * @Author Meteor
 * @Date 2022/3/30 14:02
 * @Description Bean转换工具类
 */
public class BeanHelper {

    public static <T> T copyProperties(Object source, Class<T> target) {
        try {
            T o = target.newInstance();
            BeanUtils.copyProperties(source, o);
            return o;
        } catch (InstantiationException e) {
            throw new RuntimeException(target.getName() + "无法被实例化，可能是一个接口或抽象类");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(target.getName() + "无法被实例化，构造函数无法访问");
        }
    }
}
