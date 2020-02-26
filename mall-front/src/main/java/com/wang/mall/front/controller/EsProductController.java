package com.wang.mall.front.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.wang.mall.common.api.CommonPage;
import com.wang.mall.common.api.CommonResult;
import com.wang.mall.front.domain.EsProduct;
import com.wang.mall.front.domain.EsProductRelatedInfo;
import com.wang.mall.front.feign.FeignSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @author 王念
 * @create 2020-02-25 18:17
 */
@Api(tags = "EsProductController", description = "商品搜索")
@RestController
@RequestMapping("/esProduct")
public class EsProductController {
    @Autowired
    private FeignSearchService searchService;

    @ApiOperation(value = "简单搜索")
    @GetMapping("/search/simple")
    public CommonResult search(@RequestParam(required = false) String keyword,
                               @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                               @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        ThreadUtil.sleep(2, TimeUnit.SECONDS);
        return searchService.search(keyword, pageNum, pageSize);
    }

    @ApiOperation(value = "综合搜索、筛选、排序")
    @ApiImplicitParam(name = "sort", value = "排序字段:0->按相关度；1->按新品；2->按销量；3->价格从低到高；4->价格从高到低",
            defaultValue = "0", allowableValues = "0,1,2,3,4", paramType = "query", dataType = "integer")
    @GetMapping("/search")
    public CommonResult search(@RequestParam(required = false) String keyword,
                               @RequestParam(required = false) Long productCategoryId,
                               @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                               @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                               @RequestParam(required = false, defaultValue = "0") Integer sort) {
        return searchService.search(keyword, productCategoryId, pageNum, pageSize, sort);

    }

    @ApiOperation(value = "根据商品id推荐商品")
    @GetMapping("/recommend/{id}")
    public CommonResult recommend(@PathVariable Long id,
                                  @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                  @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        return searchService.recommend(id, pageNum, pageSize);
    }

    @ApiOperation(value = "获取搜索的相关品牌、分类及筛选属性")
    @GetMapping("/search/relate")
    public CommonResult searchRelatedInfo(@RequestParam(required = false) String keyword) {
        return searchService.searchRelatedInfo(keyword);
    }
}
