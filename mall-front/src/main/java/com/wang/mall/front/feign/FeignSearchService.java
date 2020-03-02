package com.wang.mall.front.feign;

import com.wang.mall.common.api.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author 王念
 * @create 2020-02-25 18:09
 */
@FeignClient("mall-search")
public interface FeignSearchService {

    /**
     * 根据关键字搜索名称或者副标题
     */
    @GetMapping("/esProduct/search/simple")
    CommonResult search(@RequestParam(required = false) String keyword,
                        @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                        @RequestParam(required = false, defaultValue = "5") Integer pageSize);

    /**
     * 根据关键字搜索名称或者副标题复合查询
     */
    @GetMapping("/esProduct/search")
    CommonResult search(@RequestParam(required = false) String keyword,
                        @RequestParam(required = false) Long productCategoryId,
                        @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                        @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                        @RequestParam(required = false, defaultValue = "0") Integer sort);


    /**
     * 根据id推荐商品
     */
    @GetMapping("/esProduct/recommend/{id}")
    CommonResult recommend(@PathVariable Long id,
                           @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                           @RequestParam(required = false, defaultValue = "5") Integer pageSize);

    /**
     * 获取搜索的相关品牌、分类及筛选属性
     */
    @GetMapping("/esProduct/search/relate")
    CommonResult searchRelatedInfo(@RequestParam(required = false) String keyword);
}
