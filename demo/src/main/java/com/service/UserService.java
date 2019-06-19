package com.service;

import com.domain.UserDomain;
import com.mapper.UserDomainMapper;
import com.util.FileUtil;
import com.vo.Result;
import com.vo.SuccessRes;
import com.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Value("${uploadDir}")
    private String uploadDir;

    @Value("${virtualPath}")
    private String virtualPath;

    @Autowired
    private UserDomainMapper userDomainMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<UserVo> list(UserVo userVo) {
        List<UserDomain> userDomains = userDomainMapper.selectSelective(userVo);
        List<UserVo> userVoList = new ArrayList<>(userDomains.size());
        for (UserDomain userDomain : userDomains) {
            userVoList.add(domainToVo(userDomain));
        }
        return userVoList;
    }

    public Result add(UserVo userVo) {
        try {
            byte[] bytes = userVo.getFile().getBytes();
            String filename = FileUtil.storeFile(bytes, uploadDir, userVo.getFile().getOriginalFilename());
            UserDomain userDomain = voToDomain(userVo);
            userDomain.setName(filename);
            System.out.println("---------------");
            userDomainMapper.insert(userDomain);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new SuccessRes();
    }

    private UserDomain voToDomain(UserVo userVo) {
        UserDomain userDomain = new UserDomain();
        userDomain.setId(userVo.getId());
        userDomain.setUuid(UUID.randomUUID().toString().replace("-", "").toLowerCase());
        userDomain.setUsername(userVo.getUsername());
        userDomain.setPassword(userVo.getPassword());
        userDomain.setName(userVo.getName());
        return userDomain;
    }

    private UserVo domainToVo(UserDomain userDomain) {
        UserVo userVo = new UserVo();
        userVo.setId(userDomain.getId());
        userVo.setUuid(userDomain.getUuid());
        userVo.setUsername(userDomain.getUsername());
        userVo.setPassword(userDomain.getPassword());
        userVo.setName(userDomain.getName());
        userVo.setImgPath(virtualPath + userDomain.getName());
        return userVo;
    }
}
