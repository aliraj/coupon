package com.roof.coupon.outerapi.jingdong;

import com.roof.coupon.outerapi.OauthService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author liuxin
 * @since 2018/4/1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:spring-test.xml"})
public class JingdongOauthServiceTest {
    private OauthService oauthService;

    @Test
    public void save() {
    }

    @Test
    public void load() {
    }

    @Test
    public void getToken() {
    }

    @Test
    public void refresh() {
        oauthService.refresh();
    }

    @Autowired
    public void setOauthService(@Qualifier("jingdongOauthService") OauthService oauthService) {
        this.oauthService = oauthService;
    }
}