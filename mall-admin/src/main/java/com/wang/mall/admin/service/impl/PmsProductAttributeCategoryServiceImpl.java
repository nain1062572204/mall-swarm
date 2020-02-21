package com.wang.mall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.wang.mall.admin.dao.PmsProductAttributeCategoryDao;
import com.wang.mall.admin.dto.PmsProductAttributeCategoryItem;
import com.wang.mall.mapper.PmsProductAttributeCategoryMapper;
import com.wang.mall.model.PmsProductAttributeCategory;
import com.wang.mall.model.PmsProductAttributeCategoryExample;
import com.wang.mall.admin.service.PmsProductAttributeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 属性分类Service实现类
 *
 * @author 王念
 * @create 2020-02-14 22:50
 */
@Service
public class PmsProductAttributeCategoryServiceImpl implements PmsProductAttributeCategoryService {
    @Autowired
    private PmsProductAttributeCategoryMapper productAttributeCategoryMapper;
    @Autowired
    private PmsProductAttributeCategoryDao productAttributeCategoryDao;

    @Override
    public int create(String name) {
        PmsProductAttributeCategory category = new PmsProductAttributeCategoryItem();
        category.setName(name);
        return productAttributeCategoryMapper.insertSelective(category);
    }

    @Override
    public int update(Long id, String name) {
        PmsProductAttributeCategory productAttributeCategory = PmsProductAttributeCategoryItem.builder()
                .id(id)
                .name(name)
                .build();
        return productAttributeCategoryMapper.updateByPrimaryKeySelective(productAttributeCategory);
    }

    @Override
    public int delete(Long id) {
        return productAttributeCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PmsProductAttributeCategory item(Long id) {
        return productAttributeCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PmsProductAttributeCategory> list(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return productAttributeCategoryMapper.selectByExample(new PmsProductAttributeCategoryExample());
    }

    @Override
    public List<PmsProductAttributeCategoryItem> listWithAttr() {
        return productAttributeCategoryDao.listWithAttr();
    }
}
