package com.mapper;

import com.domain.User1Domain;

public interface User1DomainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User1Domain record);

    int insertSelective(User1Domain record);

    User1Domain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User1Domain record);

    int updateByPrimaryKey(User1Domain record);
}