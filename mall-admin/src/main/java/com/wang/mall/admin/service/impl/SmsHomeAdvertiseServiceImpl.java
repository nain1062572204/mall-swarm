package com.wang.mall.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.wang.mall.admin.util.RedisUtil;
import com.wang.mall.cache.keys.RedisKeys;
import com.wang.mall.mapper.SmsHomeAdvertiseMapper;
import com.wang.mall.model.SmsHomeAdvertise;
import com.wang.mall.model.SmsHomeAdvertiseExample;
import com.wang.mall.admin.service.SmsHomeAdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 首页广告Service实现类
 *
 * @author 王念
 * @create 2020-02-13 23:08
 */
@Service
public class SmsHomeAdvertiseServiceImpl implements SmsHomeAdvertiseService {
    @Autowired
    private SmsHomeAdvertiseMapper advertiseMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public int create(SmsHomeAdvertise advertise) {
        redisUtil.deleteAll(new String[]{RedisKeys.HOME_ADVERTISE.getKey()});
        return advertiseMapper.insert(advertise);
    }

    @Override
    public int delete(List<Long> ids) {
        redisUtil.deleteAll(new String[]{RedisKeys.HOME_ADVERTISE.getKey()});
        SmsHomeAdvertiseExample example = new SmsHomeAdvertiseExample();
        example.createCriteria().andIdIn(ids);
        return advertiseMapper.deleteByExample(example);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        redisUtil.deleteAll(new String[]{RedisKeys.HOME_ADVERTISE.getKey()});
        SmsHomeAdvertise advertise = new SmsHomeAdvertise();
        advertise.setId(id);
        advertise.setStatus(status);
        return advertiseMapper.updateByPrimaryKeySelective(advertise);
    }

    @Override
    public SmsHomeAdvertise item(Long id) {
        return advertiseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Long id, SmsHomeAdvertise advertise) {
        redisUtil.deleteAll(new String[]{RedisKeys.HOME_ADVERTISE.getKey()});
        advertise.setId(id);
        return advertiseMapper.updateByPrimaryKeySelective(advertise);
    }

    @Override
    public List<SmsHomeAdvertise> list(Integer type, String endTime, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsHomeAdvertiseExample example = new SmsHomeAdvertiseExample();
        SmsHomeAdvertiseExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("sort desc");
        if (type != null)
            criteria.andTypeEqualTo(type);
        if (!StringUtils.isEmpty(endTime)) {
            String startStr = endTime + " 00:00:00";
            String endStr = endTime + " 23:59:59";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = null;
            try {
                start = sdf.parse(startStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date end = null;
            try {
                end = sdf.parse(endStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (start != null && end != null) {
                criteria.andEndTimeBetween(start, end);
            }
        }
        return advertiseMapper.selectByExample(example);
    }
}
