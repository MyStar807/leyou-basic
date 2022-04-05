package cn.study.common.utils;

import cn.study.common.constants.RegexPatterns;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * @Author Meteor
 * @Date 2022/3/30 19:24
 * @Description
 */
public class RegexUtils {

    // 符合 true
    public static boolean isPhone(String phone){
        return matches(phone, RegexPatterns.PHONE_REGEX);
    }

    private static boolean matches(String str, String regex) {
        return StringUtils.isBlank(str) ? false : str.matches(regex);
    }
}
