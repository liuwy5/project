package com.service;

import com.domain.BankBDomain;
import com.domain.BankDomain;
import com.mapper.BankBDomainMapper;
import com.mapper.BankDomainMapper;
import com.vo.BankVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

@Service
public class BankService {

    private static final Logger logger = LoggerFactory.getLogger(BankService.class);

    @Autowired
    private BankDomainMapper bankDomainMapper;

    @Autowired
    private BankBDomainMapper bankBDomainMapper;

    @Transactional
    public void deposit(BankVo bankVo) throws Exception {
        Date date = new Date();
        bankVo.setCreateTime(date);
        bankVo.setModifyTime(date);
        bankDeposit(bankVo);
        bankBDeposit(bankVo);

        throw new RuntimeException();
    }

    @Transactional
    public void bankDeposit(BankVo bankVo) {
        BankDomain bankDomain = new BankDomain(bankVo.getMoney());
        bankDomain.setCreateTime(bankVo.getCreateTime());
        bankDomain.setModifyTime(bankVo.getModifyTime());
        bankDomainMapper.insert(bankDomain);
        bankDomain.setMoney(bankDomain.getMoney().add(new BigDecimal(1)));
        bankDomainMapper.insert(bankDomain);
    }

    @Transactional
    public void bankBDeposit(BankVo bankVo) {
        BankBDomain bankBDomain = new BankBDomain(bankVo.getMoney());
        bankBDomain.setCreateTime(bankVo.getCreateTime());
        bankBDomain.setModifyTime(bankVo.getModifyTime());
        bankBDomainMapper.insert(bankBDomain);
        bankBDomain.setMoney(bankBDomain.getMoney().add(new BigDecimal(1)));
        bankBDomainMapper.insert(bankBDomain);
    }
}
