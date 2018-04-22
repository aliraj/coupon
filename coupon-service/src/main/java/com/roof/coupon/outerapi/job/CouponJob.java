package com.roof.coupon.outerapi.job;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.roof.spring.CurrentSpringContext;
import org.roof.spring.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.converter.DefaultJobParametersConverter;
import org.springframework.batch.core.converter.JobParametersConverter;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 定时任务入口
 *
 * @author liuxin
 * @since 2018/4/18
 */
@Component
public class CouponJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(CouponJob.class);

    private JobLauncher jobLauncher;

    public void start() {
        LOGGER.info("scheduled heartbeat :" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    }

    public void startTaokeQuery() {
        Map<String, JobParameter> parameters = new HashMap<>();
        parameters.put("date", new JobParameter(new Date()));
        parameters.put("api", new JobParameter("http://api.tkjidi.com/getGoodsLink"));
        start("taokeCouponJob", parameters);
        LOGGER.info("startTaokeQuery :" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

    }

    public void startJingtuituiQuery() {
        Map<String, JobParameter> parameters = new HashMap<>();
        parameters.put("date", new JobParameter(new Date()));
        parameters.put("api", new JobParameter("http://japi.jingtuitui.com/api/get_goods_list"));
        start("jingtuituiCouponJob", parameters);
        LOGGER.info("startJingtuituiQuery :" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

    }

    private void start(String jobName, Map<String, JobParameter> parameters) {
        Job job = CurrentSpringContext.getBean(jobName, Job.class);
        JobParameters jobParameters = new JobParameters(parameters);
        try {
            JobExecution jobExecution = jobLauncher.run(job, jobParameters);
            LOGGER.info(jobExecution.toString());
        } catch (JobExecutionAlreadyRunningException | JobRestartException
                | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    @Autowired
    public void setJobLauncher(@Qualifier("jobLauncher") JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }

}