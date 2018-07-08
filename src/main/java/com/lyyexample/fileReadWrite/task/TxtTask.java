package com.lyyexample.fileReadWrite.task;

import com.lyyexample.fileReadWrite.process.TxtFileProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by liuyangyang on 2018/7/3.
 */
@Service
@EnableScheduling
public class TxtTask {
    private static final Logger log = LoggerFactory.getLogger(TxtTask.class);
    @Autowired
    public JobLauncher jobLauncher;

    @Resource(name="txtJob")
    public Job fileJob;

    @Scheduled(fixedDelay = 5000)
    public void execut() throws Exception{
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("beginTime", System.currentTimeMillis())
                .addString("inputPath","/data/input.txt")
                .toJobParameters();
        JobExecution JobExecution = jobLauncher.run(fileJob,jobParameters);
        log.info("fileJob跑批结果："+JobExecution.getExitStatus()
                +",耗时："+(System.currentTimeMillis()-jobParameters.getLong("beginTime"))+"ms");
    }



    //每5秒执行一次
//    @Scheduled(cron="0/5 * *  * * ? ")
//    public void exe(){
//        System.out.print("每5秒执行一次当前时间为："+new Date());
//    }
//
//    @Scheduled(fixedDelay = 10000)
//    public void execute(){
//        System.out.print("延迟秒执行一次，当前时间为："+new Date());
//    }

}
