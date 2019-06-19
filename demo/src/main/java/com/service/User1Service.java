package com.service;

import com.domain.User1Domain;
import com.domain.User2Domain;
import com.mapper.User1DomainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class User1Service {

    @Autowired
    private User2Service user2Service;

    @Autowired
    private User1DomainMapper user1DomainMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertRequired(User1Domain user1) {
        user1DomainMapper.insert(user1);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertRequiredExc(User1Domain user1) {
        user1DomainMapper.insert(user1);

        System.out.println(4/0);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertRequiresNew(User1Domain user1) {
        user1DomainMapper.insert(user1);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertRequiresNewExc(User1Domain user1) {
        user1DomainMapper.insert(user1);

        System.out.println(4/0);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void insertNested(User1Domain user1Domain) {
        user1DomainMapper.insert(user1Domain);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void insertNestedExc(User1Domain user1Domain) {
        user1DomainMapper.insert(user1Domain);
        System.out.println(4/0);
    }


}
