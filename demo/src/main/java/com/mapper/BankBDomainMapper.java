package com.mapper;

import com.domain.BankBDomain;

public interface BankBDomainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BankBDomain record);

    int insertSelective(BankBDomain record);

    BankBDomain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BankBDomain record);

    int updateByPrimaryKey(BankBDomain record);
}