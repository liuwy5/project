package com.service;

import com.domain.BankDomain;
import com.mapper.BankDomainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankService {
    @Autowired
    private BankDomainMapper bankDomainMapper;

    public BankDomain find(int i) {
        return bankDomainMapper.selectByPrimaryKey(i);
    }

    public int add(BankDomain bankDomain) {
        return bankDomainMapper.insert(bankDomain);
    }

}
