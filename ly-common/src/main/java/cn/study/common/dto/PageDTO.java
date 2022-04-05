package cn.study.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author Meteor
 * @Date 2022/3/30 14:16
 * @Description 分页的结果对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO<T> {
    private Long total;
    private Long totalPage;
    private List<T> items;

    public PageDTO(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }
}
