package com.service;

import com.domain.User1Domain;
import com.domain.User2Domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserTestService {
    @Autowired
    private User1Service user1Service;

    @Autowired
    private User2Service user2Service;

    @Transactional
    public void test() {
        User1Domain user1Domain = new User1Domain();
        user1Domain.setName("a1");

        user1Service.insertRequired(user1Domain);

        User2Domain user2Domain = new User2Domain();
        user2Domain.setName("a11");
        try {
            user2Service.insertRequiredExc(user2Domain);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        insertRequiresNewExc(user1Domain);

//        System.out.println(4/0);
    }

}
