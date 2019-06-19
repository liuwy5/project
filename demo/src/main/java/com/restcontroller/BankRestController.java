package com.restcontroller;

import com.service.BankService;
import com.vo.BankVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api")
public class BankRestController {
    @Autowired
    private BankService bankService;

    @RequestMapping("/deposit")
    public String deposit(BankVo bankVo) throws Exception {
        try {
            bankService.deposit(bankVo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new Exception("exception");
//        return bankVo.getMoney().toString();
    }
}
