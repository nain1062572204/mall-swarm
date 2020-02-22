package com.wang.mall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.wang.mall.admin.dao.*;
import com.wang.mall.admin.dto.PmsProductParam;
import com.wang.mall.admin.dto.PmsProductQueryParam;
import com.wang.mall.admin.dto.PmsProductResult;
import com.wang.mall.admin.feign.FeignRedisService;
import com.wang.mall.common.rediskey.RedisKeys;
import com.wang.mall.mapper.*;
import com.wang.mall.model.*;
import com.wang.mall.admin.service.PmsProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商品管理Service实现类
 *
 * @author 王念
 * @create 2020-02-11 17:23
 */
@Slf4j
@Service
public class PmsProductServiceImpl implements PmsProductService {
    @Autowired
    private PmsProductMapper productMapper;
    @Autowired
    private PmsProductLadderDao productLadderDao;
    @Autowired
    private PmsProductLadderMapper productLadderMapper;
    @Autowired
    private PmsProductFullReductionDao productFullReductionDao;
    @Autowired
    private PmsProductFullReductionMapper productFullReductionMapper;
    @Autowired
    private PmsSkuStockDao skuStockDao;
    @Autowired
    private PmsSkuStockMapper skuStockMapper;
    @Autowired
    private PmsProductAttributeValueDao productAttributeValueDao;
    @Autowired
    private PmsProductAttributeValueMapper productAttributeValueMapper;
    @Autowired
    private PmsProductDao productDao;
    @Autowired
    private PmsProductVerifyRecordDao productVerifyRecordDao;
    @Autowired
    private FeignRedisService feignRedisService;

    @Override
    public int create(PmsProductParam productParam) {
        feignRedisService.delete(RedisKeys.HOME_PRODUCT.getKey());
        for (PmsSkuStock skuStock : productParam.getSkuStockList()) {
            System.out.println(skuStock);
        }
        //创建商品
        PmsProduct product = productParam;
        product.setId(null);
        productMapper.insertSelective(product);
        //设置满减价格、阶梯价格
        Long productId = product.getId();
        //阶梯价格
        relateAndInsertList(productLadderDao, productParam.getProductLadderList(), productId);
        //满减价格
        relateAndInsertList(productFullReductionDao, productParam.getProductFullReductionList(), productId);
        //处理sku编码
        handleSkuStockCode(productParam.getSkuStockList(), productId);
        //添加sku库存
        relateAndInsertList(skuStockDao, productParam.getSkuStockList(), productId);
        //添加商品参数,添加自定义商品规格
        relateAndInsertList(productAttributeValueDao, productParam.getProductAttributeValueList(), productId);
        return 1;
    }

    private void handleSkuStockCode(List<PmsSkuStock> skuStocks, Long productId) {
        if (CollectionUtils.isEmpty(skuStocks)) return;
        for (int i = 0; i < skuStocks.size(); i++) {
            PmsSkuStock skuStock = skuStocks.get(i);
            if (StringUtils.isEmpty(skuStock.getSkuCode())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                StringBuilder sb = new StringBuilder();
                //日期
                sb.append(sdf.format(new Date()));
                //四位商品id
                sb.append(String.format("%04d", productId));
                //三位索引id
                sb.append(String.format("%03d", i + 1));
                skuStock.setSkuCode(sb.toString());
            }
        }
    }

    @Override
    public PmsProductResult updateInfo(Long id) {
        return productDao.getUpdateInfo(id);
    }

    @Override
    public int update(Long id, PmsProductParam productParam) {
        feignRedisService.delete(RedisKeys.HOME_PRODUCT.getKey());
        PmsProduct product = productParam;
        product.setId(id);
        productMapper.updateByPrimaryKeySelective(product);
        //阶梯价格
        PmsProductLadderExample ladderExample = new PmsProductLadderExample();
        ladderExample.createCriteria().andProductIdEqualTo(id);
        productLadderMapper.deleteByExample(ladderExample);
        relateAndInsertList(productLadderDao, productParam.getProductLadderList(), id);
        //满减价格
        PmsProductFullReductionExample fullReductionExample = new PmsProductFullReductionExample();
        fullReductionExample.createCriteria().andProductIdEqualTo(id);
        productFullReductionMapper.deleteByExample(fullReductionExample);
        relateAndInsertList(productFullReductionDao, productParam.getProductFullReductionList(), id);
        //修改sku库存信息
        PmsSkuStockExample skuStockExample = new PmsSkuStockExample();
        skuStockExample.createCriteria().andProductIdEqualTo(id);
        skuStockMapper.deleteByExample(skuStockExample);
        relateAndInsertList(skuStockDao, productParam.getSkuStockList(), id);
        //修改商品参数,添加自定义商品规格
        PmsProductAttributeValueExample productAttributeValueExample = new PmsProductAttributeValueExample();
        productAttributeValueExample.createCriteria().andProductIdEqualTo(id);
        productAttributeValueMapper.deleteByExample(productAttributeValueExample);
        relateAndInsertList(productAttributeValueDao, productParam.getProductAttributeValueList(), id);
        return 1;
    }

    @Override
    public List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        PmsProductExample example = new PmsProductExample();
        PmsProductExample.Criteria criteria = example.createCriteria();
        criteria.andDeleteStatusEqualTo(0);
        if (productQueryParam.getPublishStatus() != null)
            criteria.andPublishStatusEqualTo(productQueryParam.getPublishStatus());
        if (productQueryParam.getVerifyStatus() != null)
            criteria.andVerifyStatusEqualTo(productQueryParam.getVerifyStatus());
        if (!StringUtils.isEmpty(productQueryParam.getKeyword()))
            criteria.andNameLike("%" + productQueryParam.getKeyword() + "%");
        if (!StringUtils.isEmpty(productQueryParam.getProductSn()))
            criteria.andProductSnEqualTo(productQueryParam.getProductSn());
        if (productQueryParam.getProductCategoryId() != null)
            criteria.andProductCategoryIdEqualTo(productQueryParam.getProductCategoryId());

        return productMapper.selectByExample(example);
    }

    @Override
    public int updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail) {
        feignRedisService.delete(RedisKeys.HOME_PRODUCT.getKey());
        PmsProduct product = PmsProduct.builder().verifyStatus(verifyStatus).build();
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andIdIn(ids);
        List<PmsProductVerifyRecord> vertifyRecords = new ArrayList<>();
        int count = productMapper.updateByExampleSelective(product, example);
        //修改完成 插入修改记录
        for (Long id : ids) {
            PmsProductVerifyRecord record = new PmsProductVerifyRecord();
            record.setProductId(id);
            record.setCreateTime(new Date());
            record.setDetail(detail);
            record.setStatus(verifyStatus);
            record.setVerifyMan("TEST");
            vertifyRecords.add(record);
        }
        productVerifyRecordDao.insertList(vertifyRecords);
        return count;
    }

    @Override
    public int updatePublishStatus(List<Long> ids, Integer publishStatus) {
        feignRedisService.delete(RedisKeys.HOME_PRODUCT.getKey());
        PmsProduct record = PmsProduct.builder().publishStatus(publishStatus).build();
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andIdIn(ids);
        return productMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        feignRedisService.delete(RedisKeys.HOME_PRODUCT.getKey());
        PmsProduct record = new PmsProduct();
        record.setRecommandStatus(recommendStatus);
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andIdIn(ids);
        return productMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateNewStatus(List<Long> ids, Integer newStatus) {
        feignRedisService.delete(RedisKeys.HOME_PRODUCT.getKey());
        PmsProduct record = new PmsProduct();
        record.setNewStatus(newStatus);
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andIdIn(ids);
        return productMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
        feignRedisService.delete(RedisKeys.HOME_PRODUCT.getKey());
        PmsProduct record = new PmsProduct();
        record.setDeleteStatus(deleteStatus);
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andIdIn(ids);
        return productMapper.updateByExampleSelective(record, example);
    }

    @Override
    public List<PmsProduct> list(String keyword) {
        PmsProductExample productExample = new PmsProductExample();
        PmsProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andDeleteStatusEqualTo(0);
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andNameLike("%" + keyword + "%");
            productExample.or().andDeleteStatusEqualTo(0).andProductSnLike("%" + keyword + "%");
        }
        return productMapper.selectByExample(productExample);
    }

    /**
     * 建立和插入关系表操作
     *
     * @param dao       操作的dao
     * @param dataList  要插入的数据
     * @param productId 建立关系的id
     */
    private void relateAndInsertList(Object dao, List dataList, Long productId) {
        if (CollectionUtils.isEmpty(dataList)) return;
        try {
            if (CollectionUtils.isEmpty(dataList)) return;
            for (Object item : dataList) {
                Method setId = item.getClass().getMethod("setId", Long.class);
                setId.invoke(item, (Long) null);
                Method setProductId = item.getClass().getMethod("setProductId", Long.class);
                setProductId.invoke(item, productId);
            }
            Method insertList = dao.getClass().getMethod("insertList", List.class);
            insertList.invoke(dao, dataList);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException("创建商品失败");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("创建商品失败");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("创建商品失败");
        }
    }
}
