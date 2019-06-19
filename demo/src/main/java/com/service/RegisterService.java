package com.service;

import com.domain.UserDomain;
import com.mapper.UserDomainMapper;
import com.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private UserDomainMapper userDomainMapper;

    private static final Logger logger = LoggerFactory.getLogger(RegisterService.class);

    public void reg(UserVo userVo) {
        UserDomain userDomain = new UserDomain();
        userDomain.setUuid("122344343");
        userDomain.setUsername(userVo.getUsername());
        userDomain.setPassword(userVo.getPassword());
        userDomain.setName(userVo.getName());
        userDomainMapper.insert(userDomain);
    }
}
