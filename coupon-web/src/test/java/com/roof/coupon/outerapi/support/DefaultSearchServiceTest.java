package com.roof.coupon.outerapi.support;

import com.roof.coupon.apilog.entity.ApiLog;
import com.roof.coupon.outerapi.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.roof.roof.dataaccess.api.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


/**
 * @author liuxin
 * @since 2018/4/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath*:spring.xml"})
public class DefaultSearchServiceTest {
    private SearchService searchService;

    @Test
    public void doSearch() {
        Page page = new Page();
        page.setLimit(10L);
        page.setCurrentPage(1L);
        page = searchService.doSearch("休闲", page, new String[]{ApiLog.PLATFORM_JINGTUITUI, ApiLog.PLATFORM_TAOKE});
        for (Object o : page.getDataList()) {
            System.out.println(o);
        }
    }

    @Autowired
    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }
}