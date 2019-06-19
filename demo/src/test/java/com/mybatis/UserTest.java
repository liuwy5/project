package com.mybatis;

import com.domain.UserDomain;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.UserDomainMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest {

    private static final Logger log = LoggerFactory.getLogger(UserTest.class);

    @Autowired
    private UserDomainMapper userDomainMapper;

    @Test
    public void testList() {
        List<UserDomain> userDomainList = userDomainMapper.selectSelective(null);
        System.out.println("------------------------" + userDomainList.size());
    }

//    @Test
    public void page() {
        final PageInfo<UserDomain> pageInfo = PageHelper.startPage(1, 10).
                setOrderBy("id desc").doSelectPageInfo(() -> userDomainMapper.selectSelective(null));
        log.info("分页信息：{}", pageInfo);
    }

    @Test
    public void pageContent() {
//        PageHelper.startPage(1, 2).setOrderBy("id desc");

        List<UserDomain> userDomainList = userDomainMapper.selectSelective(null);
        log.info("fsdf" + userDomainList);

//        PageInfo<UserDomain> userDomainPageInfo = new PageInfo<>(userDomainList);
//        log.info("page: {}", userDomainPageInfo);

        UserDomain userDomain = new UserDomain();
        userDomain.setUuid("fsdf");
        userDomain.setName("dfd");
        userDomain.setPassword("fdfd");
        userDomain.setUsername("fsdf");
        userDomainMapper.insert(userDomain);


        List<UserDomain> userDomainList1 = userDomainMapper.selectSelective(null);
        log.info("fsdf" + userDomainList1);
    }
}
