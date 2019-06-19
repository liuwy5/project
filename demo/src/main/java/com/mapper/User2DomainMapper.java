package com.mapper;

import com.domain.User2Domain;

public interface User2DomainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User2Domain record);

    int insertSelective(User2Domain record);

    User2Domain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User2Domain record);

    int updateByPrimaryKey(User2Domain record);
}