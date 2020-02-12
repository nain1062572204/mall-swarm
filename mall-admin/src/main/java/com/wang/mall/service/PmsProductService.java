package com.wang.mall.service;

import com.wang.mall.dto.PmsProductParam;
import com.wang.mall.dto.PmsProductQueryParam;
import com.wang.mall.dto.PmsProductResult;
import com.wang.mall.model.PmsProduct;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品管理Service
 *
 * @author 王念
 * @create 2020-02-11 16:42
 */
public interface PmsProductService {
    /**
     * 创建商品
     */
    @Transactional
    int create(PmsProductParam productParam);

    /**
     * 商品id获取更新信息
     */
    PmsProductResult updateInfo(Long id);

    /**
     * 更新商品
     */
    @Transactional
    int update(Long id, PmsProductParam productParam);

    /**
     * 分页查询商品
     */
    List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum);

    /**
     * 批量修改商品审核状态
     *
     * @param ids          商品id
     * @param verifyStatus 审核状态
     * @param detail       审核说明
     */
    @Transactional
    int updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail);

    /**
     * 批量修改商品上架状态
     */
    int updatePublishStatus(List<Long> ids, Integer publishStatus);

    /**
     * 批量修改商品推荐状态
     */
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * 批量修改新品状态
     */
    int updateNewStatus(List<Long> ids, Integer newStatus);

    /**
     * 批量删除商品
     */
    int updateDeleteStatus(List<Long> ids, Integer deleteStatus);

    /**
     * 根据商品名称或者货号模糊查询
     */
    List<PmsProduct> list(String keyword);
}
