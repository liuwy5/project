package com.mapper;

import com.entity.Resource;

import java.util.List;

public interface ResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Long id);

    List<Resource> selectAll();

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);
}