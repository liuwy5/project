package com.mapper;

import com.domain.BankDomain;

public interface BankDomainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BankDomain record);

    int insertSelective(BankDomain record);

    BankDomain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BankDomain record);

    int updateByPrimaryKey(BankDomain record);
}