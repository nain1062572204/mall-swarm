package com.wang.mall.service.impl;

import com.wang.mall.dto.SmsFlashPromotionSessionDetail;
import com.wang.mall.mapper.SmsFlashPromotionSessionMapper;
import com.wang.mall.model.SmsFlashPromotionSession;
import com.wang.mall.model.SmsFlashPromotionSessionExample;
import com.wang.mall.service.SmsFlashPromotionProductRelationService;
import com.wang.mall.service.SmsFlashPromotionSessionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 闪购Service实现类
 *
 * @author 王念
 * @create 2020-02-14 15:04
 */
@Service
public class SmsFlashPromotionSessionServiceImpl implements SmsFlashPromotionSessionService {
    @Autowired
    private SmsFlashPromotionSessionMapper promotionSessionMapper;
    @Autowired
    private SmsFlashPromotionProductRelationService relationService;

    @Override
    public int create(SmsFlashPromotionSession promotionSession) {
        promotionSession.setCreateTime(new Date());
        return promotionSessionMapper.insert(promotionSession);
    }

    @Override
    public int update(Long id, SmsFlashPromotionSession promotionSession) {
        promotionSession.setId(id);
        return promotionSessionMapper.updateByPrimaryKeySelective(promotionSession);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        SmsFlashPromotionSession promotionSession = new SmsFlashPromotionSessionDetail();
        promotionSession.setId(id);
        promotionSession.setStatus(status);
        return promotionSessionMapper.updateByPrimaryKeySelective(promotionSession);
    }

    @Override
    public int delete(Long id) {
        return promotionSessionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SmsFlashPromotionSession item(Long id) {
        return promotionSessionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SmsFlashPromotionSession> list() {
        SmsFlashPromotionSessionExample example = new SmsFlashPromotionSessionExample();
        return promotionSessionMapper.selectByExample(example);
    }

    @Override
    public List<SmsFlashPromotionSessionDetail> selectList() {
        List<SmsFlashPromotionSessionDetail> result = new ArrayList<>();
        SmsFlashPromotionSessionExample example = new SmsFlashPromotionSessionExample();
        example.createCriteria().andStatusEqualTo(1);
        List<SmsFlashPromotionSession> list = promotionSessionMapper.selectByExample(example);
        for (SmsFlashPromotionSession promotionSession : list) {
            SmsFlashPromotionSessionDetail detail = new SmsFlashPromotionSessionDetail();
            BeanUtils.copyProperties(promotionSession, detail);
            long count = relationService.getCount(promotionSession.getId());
            detail.setProductCount(count);
            result.add(detail);
        }
        return result;
    }
}
