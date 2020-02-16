package com.wang.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.wang.mall.dao.PmsProductAttributeDao;
import com.wang.mall.dto.PmsProductAttributeParam;
import com.wang.mall.dto.ProductAttrInfo;
import com.wang.mall.mapper.PmsProductAttributeCategoryMapper;
import com.wang.mall.mapper.PmsProductAttributeMapper;
import com.wang.mall.model.PmsProductAttribute;
import com.wang.mall.model.PmsProductAttributeCategory;
import com.wang.mall.model.PmsProductAttributeExample;
import com.wang.mall.service.PmsProductAttributeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品属性Service实现类
 *
 * @author 王念
 * @create 2020-02-14 20:42
 */
@Service
public class PmsProductAttributeServiceImpl implements PmsProductAttributeService {
    @Autowired
    private PmsProductAttributeMapper productAttributeMapper;
    @Autowired
    private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;
    @Autowired
    private PmsProductAttributeDao productAttributeDao;

    @Override
    public List<PmsProductAttribute> list(Long cid, Integer type, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        PmsProductAttributeExample example = new PmsProductAttributeExample();
        example.setOrderByClause("sort desc");
        example.createCriteria()
                .andProductAttributeCategoryIdEqualTo(cid)
                .andTypeEqualTo(type);
        return productAttributeMapper.selectByExample(example);
    }

    @Override
    public int create(PmsProductAttributeParam productAttributeParam) {
        PmsProductAttribute productAttribute = new PmsProductAttribute();
        BeanUtils.copyProperties(productAttributeParam, productAttribute);
        int count = productAttributeMapper.insertSelective(productAttribute);
        //更新商品属性数量
        PmsProductAttributeCategory productAttributeCategory = productAttributeCategoryMapper.selectByPrimaryKey(productAttribute.getProductAttributeCategoryId());
        if (productAttribute.getType() == 0)
            productAttributeCategory.setAttributeCount(productAttributeCategory.getAttributeCount() + 1);
        else if (productAttribute.getType() == 1)
            productAttributeCategory.setParamCount(productAttributeCategory.getParamCount() + 1);
        productAttributeCategoryMapper.updateByPrimaryKey(productAttributeCategory);
        return count;
    }

    @Override
    public int update(Long id, PmsProductAttributeParam productAttributeParam) {
        PmsProductAttribute productAttribute = new PmsProductAttribute();
        productAttribute.setId(id);
        BeanUtils.copyProperties(productAttributeParam, productAttribute);
        return productAttributeMapper.updateByPrimaryKeySelective(productAttribute);
    }

    @Override
    public PmsProductAttribute item(Long id) {
        return productAttributeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(List<Long> ids) {
        //获取分类
        PmsProductAttribute pmsProductAttribute = productAttributeMapper.selectByPrimaryKey(ids.get(0));
        Integer type = pmsProductAttribute.getType();
        PmsProductAttributeCategory pmsProductAttributeCategory = productAttributeCategoryMapper.selectByPrimaryKey(pmsProductAttribute.getProductAttributeCategoryId());
        PmsProductAttributeExample example = new PmsProductAttributeExample();
        example.createCriteria().andIdIn(ids);
        int count = productAttributeMapper.deleteByExample(example);
        //删除完成后修改数量
        if (type == 0) {
            if (pmsProductAttributeCategory.getAttributeCount() >= count) {
                pmsProductAttributeCategory.setAttributeCount(pmsProductAttributeCategory.getAttributeCount() - count);
            } else {
                pmsProductAttributeCategory.setAttributeCount(0);
            }
        } else if (type == 1) {
            if (pmsProductAttributeCategory.getParamCount() >= count) {
                pmsProductAttributeCategory.setParamCount(pmsProductAttributeCategory.getParamCount() - count);
            } else {
                pmsProductAttributeCategory.setParamCount(0);
            }
        }
        productAttributeCategoryMapper.updateByPrimaryKey(pmsProductAttributeCategory);
        return count;
    }

    @Override
    public List<ProductAttrInfo> productAttrInfo(Long productCategoryId) {
        return productAttributeDao.getProductAttrInfo(productCategoryId);
    }
}
