package com.lyyexample.fileReadWrite.task;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by liuyangyang on 2018/7/3.
 */
@Service
@EnableScheduling
public class TxtTask {

    @Autowired
    public JobLauncher jobLauncher;

    @Autowired
    public Job fileJob;

    @Scheduled(fixedDelay = 5000)
    public void execut() throws Exception{
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("beginTime", System.currentTimeMillis())
                .addString("inputPath","/data/input.txt")
                .toJobParameters();
        JobExecution JobExecution = jobLauncher.run(fileJob,jobParameters);
        System.out.println("fileJob跑批结果："+JobExecution.getExitStatus()
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
