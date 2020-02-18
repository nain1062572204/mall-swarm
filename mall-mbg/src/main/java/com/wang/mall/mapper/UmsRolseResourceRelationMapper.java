package com.wang.mall.mapper;

import com.wang.mall.model.UmsRolseResourceRelation;
import com.wang.mall.model.UmsRolseResourceRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsRolseResourceRelationMapper {
    long countByExample(UmsRolseResourceRelationExample example);

    int deleteByExample(UmsRolseResourceRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsRolseResourceRelation record);

    int insertSelective(UmsRolseResourceRelation record);

    List<UmsRolseResourceRelation> selectByExample(UmsRolseResourceRelationExample example);

    UmsRolseResourceRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsRolseResourceRelation record, @Param("example") UmsRolseResourceRelationExample example);

    int updateByExample(@Param("record") UmsRolseResourceRelation record, @Param("example") UmsRolseResourceRelationExample example);

    int updateByPrimaryKeySelective(UmsRolseResourceRelation record);

    int updateByPrimaryKey(UmsRolseResourceRelation record);
}