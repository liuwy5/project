package com.mapper;

import com.domain.BankDomain;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BankDomainMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BankDomain record);

    int insertSelective(BankDomain record);

    BankDomain selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BankDomain record);

    int updateByPrimaryKey(BankDomain record);
}