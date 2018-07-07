package com.lyyexample.config;

import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liuyangyang on 2018/7/4.
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean("jobRepository")
    public JobRepository jobRepository() throws Exception{
        MapJobRepositoryFactoryBean jobRepositoryFactoryBean = new MapJobRepositoryFactoryBean();
        return jobRepositoryFactoryBean.getObject();
    }

    @Bean("jobLauncher")
    public SimpleJobLauncher jobLauncher(@Qualifier("jobRepository") JobRepository jobRepository){
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        return jobLauncher;
    }

}
