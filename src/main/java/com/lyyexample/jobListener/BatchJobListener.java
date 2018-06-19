package com.lyyexample.jobListener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Created by liuyangyang on 2018/6/12.
 */
@Component
public class BatchJobListener implements JobExecutionListener  {
    private static final Logger log = LoggerFactory.getLogger(BatchJobListener.class);
    @Override
    public void beforeJob(JobExecution jobExecution){
        log.info("任务处理开始");
    };

    @Override
    public void afterJob(JobExecution jobExecution){
        log.info("任务处理结束");
    };


}
