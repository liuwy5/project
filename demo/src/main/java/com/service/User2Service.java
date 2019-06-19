package com.service;

import com.domain.User2Domain;
import com.mapper.User2DomainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class User2Service {
    @Autowired
    private User2DomainMapper user2DomainMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertRequired(User2Domain user2) {
        user2DomainMapper.insert(user2);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertRequiredExc(User2Domain user2) {
        user2DomainMapper.insert(user2);

        System.out.println(4/0);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertRequiresNew(User2Domain user2) {
        user2DomainMapper.insert(user2);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertRequiresNewExc(User2Domain user2) {
        user2DomainMapper.insert(user2);

        System.out.println(4/0);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void insertNested(User2Domain user2Domain) {
        user2DomainMapper.insert(user2Domain);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void insertNestedExc(User2Domain user2Domain) {
        user2DomainMapper.insert(user2Domain);
        System.out.println(4/0);
    }
}
