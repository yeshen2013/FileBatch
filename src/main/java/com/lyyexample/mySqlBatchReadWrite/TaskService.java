package com.lyyexample.mySqlBatchReadWrite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by liuyangyang on 2018/7/8.
 */
@Service
//@EnableScheduling
public class TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    public JobLauncher jobLauncher;

    @Resource(name="mySqlBatchJob")
    public Job mySqlBatchJob;

//    @Scheduled(fixedDelay=5000)
    public void execute() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("Time",new Date())
                .addLong("beginTime", System.currentTimeMillis())
                .toJobParameters();
        JobExecution jobExecution = jobLauncher.run(mySqlBatchJob,jobParameters);
        log.info("批job："+jobExecution.getJobId()+",处理结果为："+jobExecution.getExitStatus()
        +"，耗时："+(System.currentTimeMillis()-jobParameters.getLong("beginTime"))+"ms");
    }
}
