package com.roof.coupon;

import com.roof.coupon.outerapi.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author liuxin
 * @since 2018/4/1
 */
@Controller
public class JingdongOauthController {
    private OauthService oauthService;

    @RequestMapping(value = "/jingdong/oauth/connect", method = RequestMethod.GET)
    public @ResponseBody
    String connect(String code) {
        oauthService.getToken(code);
        return "ok";
    }

    @RequestMapping(value = "/jingdong/oauth/refresh", method = RequestMethod.GET)
    public @ResponseBody
    String refresh() {
        oauthService.refresh();
        return "ok";
    }

    @Autowired
    public void setOauthService(@Qualifier("jingdongOauthService") OauthService oauthService) {
        this.oauthService = oauthService;
    }
}
