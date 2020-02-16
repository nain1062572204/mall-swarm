package com.wang.mall.service.impl;

import com.wang.mall.dao.PmsSkuStockDao;
import com.wang.mall.mapper.PmsSkuStockMapper;
import com.wang.mall.model.PmsSkuStock;
import com.wang.mall.model.PmsSkuStockExample;
import com.wang.mall.service.PmsSkuStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 商品
 *
 * @author 王念
 * @create 2020-02-15 20:34
 */
@Service
public class PmsSkuStockServiceImpl implements PmsSkuStockService {
    @Autowired
    private PmsSkuStockMapper skuStockMapper;
    @Autowired
    private PmsSkuStockDao skuStockDao;

    @Override
    public List<PmsSkuStock> list(Long pid, String keyword) {
        PmsSkuStockExample example = new PmsSkuStockExample();
        PmsSkuStockExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(pid);
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andSkuCodeLike("%" + keyword + "%");
        }
        return skuStockMapper.selectByExample(example);
    }

    @Override
    public int update(Long pid, List<PmsSkuStock> skuStocks) {
        return skuStockDao.replaceList(skuStocks);
    }
}
