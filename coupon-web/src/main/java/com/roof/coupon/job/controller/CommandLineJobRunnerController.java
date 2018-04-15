package com.roof.coupon.job.controller;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.roof.spring.CurrentSpringContext;
import org.roof.spring.Result;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.converter.DefaultJobParametersConverter;
import org.springframework.batch.core.converter.JobParametersConverter;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author hzliuxin1 on 2017/9/6
 */
@Controller
@RequestMapping("/batch")
public class CommandLineJobRunnerController {
    private JobParametersConverter jobParametersConverter = new DefaultJobParametersConverter();
    private JobLauncher jobLauncher;

    @ResponseBody
    @RequestMapping("/start")
    public Result start(String jobName, String parameterStr) {
        Job job = CurrentSpringContext.getBean(jobName, Job.class);
        String[] parameters = parameterStr.split(",");
        JobParameters jobParameters = jobParametersConverter.getJobParameters(StringUtils
                .splitArrayElementsIntoProperties(parameters, "="));
        String readFile = StringUtils.replace(jobParameters.getString("read_file"), "file:", "");
        String resultFile = resultFile(readFile);
        parameters = add(parameters, "result_file=" + resultFile);

        int linesToSkip = getLineNumber(readFile);
        parameters = add(parameters, "lines_to_skip=" + linesToSkip);

        jobParameters = jobParametersConverter.getJobParameters(StringUtils.splitArrayElementsIntoProperties(parameters, "="));
        JobExecution jobExecution;
        try {
            jobExecution = jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException
                | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            return new Result(Result.FAIL, e.getMessage());
        }
        return new Result(jobExecution.toString());
    }

    private String resultFile(String readFile) {
        readFile = StringUtils.replace(readFile, "file:", "");
        String date = DateFormatUtils.format(new Date(), "yyyy_MM_dd_HH_mm_ss");
        return readFile + "." + date;
    }

    private String[] add(String[] array, String element) {
        String[] result = new String[array.length + 1];
        System.arraycopy(array, 0, result, 0, array.length);
        result[result.length - 1] = element;
        return result;
    }

    private int getLineNumber(String readFile) {
        if (StringUtils.isEmpty(readFile)) {
            return 0;
        }
        String path = FilenameUtils.getFullPath(readFile);
        String name = FilenameUtils.getName(readFile);
        return FileUtils.filesLineNumber(path, name + ".*");
    }

    @ResponseBody
    @RequestMapping("/stop")
    public Result stop(String jobName) {
        return new Result();
    }

    @Autowired
    public void setJobLauncher(@Qualifier("jobLauncher") JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }

}
