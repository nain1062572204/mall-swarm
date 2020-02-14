package com.wang.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.wang.mall.mapper.SmsHomePromoMapper;
import com.wang.mall.model.SmsHomePromo;
import com.wang.mall.model.SmsHomePromoExample;
import com.wang.mall.service.SmsHomePromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 首页促销Service实现类
 *
 * @author 王念
 * @create 2020-02-14 14:00
 */
@Service
public class SmsHomePromoServiceImpl implements SmsHomePromoService {

    @Autowired
    private SmsHomePromoMapper promoMapper;

    @Override
    public int create(SmsHomePromo promo) {
        return promoMapper.insert(promo);
    }

    @Override
    public int delete(List<Long> ids) {
        SmsHomePromoExample example = new SmsHomePromoExample();
        example.createCriteria().andIdIn(ids);
        return promoMapper.deleteByExample(example);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        SmsHomePromo promo = new SmsHomePromo();
        promo.setId(id);
        promo.setStatus(status);
        return promoMapper.updateByPrimaryKeySelective(promo);
    }

    @Override
    public SmsHomePromo item(Long id) {
        return promoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Long id, SmsHomePromo promo) {
        promo.setId(id);
        return promoMapper.updateByPrimaryKeySelective(promo);
    }

    @Override
    public List<SmsHomePromo> list(String endTime, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        SmsHomePromoExample example = new SmsHomePromoExample();
        SmsHomePromoExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("sort desc");
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
        return promoMapper.selectByExample(example);
    }
}
