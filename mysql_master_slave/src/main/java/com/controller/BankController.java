package com.controller;

import com.domain.BankDomain;
import com.service.BankService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.util.Random;

@RestController
@RequestMapping("/test")
public class BankController {

    @Autowired
    private BankService bankService;

    @RequestMapping("s1")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/add")
    public int add() {
        BankDomain bankDomain = new BankDomain();
        bankDomain.setMoney(new BigDecimal(new Random().nextDouble()));
        int result = bankService.add(bankDomain);
        return result;
    }

    @RequestMapping("/get")
    public BigDecimal find(@Param("id") int id) {
        BankDomain bankDomain = bankService.find(id);
        BigDecimal money = new BigDecimal("0");
        if (bankDomain != null) {
            money = bankDomain.getMoney();
        }
        return money;
    }
}
