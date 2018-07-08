package com.lyyexample.mySqlBatchReadWrite.jobConfig;

import com.lyyexample.jobListener.BatchJobListener;
import com.lyyexample.mySqlBatchReadWrite.entry.Message;
import com.lyyexample.mySqlBatchReadWrite.process.MySqlBatchProcess;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.support.PassThroughItemProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * Created by liuyangyang on 2018/7/8.
 */
@Configuration
@EnableBatchProcessing
public class MySqlJob {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mybatis/*.xml"));
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("myBatisPagingItemReader")
    @StepScope
    public MyBatisPagingItemReader<Message> bathRead(SqlSessionFactory sqlSessionFactory){
        MyBatisPagingItemReader<Message> myBatisPagingItemReader = new MyBatisPagingItemReader();
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("id",1);
        myBatisPagingItemReader.setSaveState(true);
        myBatisPagingItemReader.setQueryId("selectByPrimaryKey");
        myBatisPagingItemReader.setParameterValues(hashMap);
        myBatisPagingItemReader.setSqlSessionFactory(sqlSessionFactory);
        myBatisPagingItemReader.setPageSize(20);
        return myBatisPagingItemReader;
    }

    @Bean("passThroughItemProcessor")
    @StepScope
    public PassThroughItemProcessor<Message> process(){
        PassThroughItemProcessor<Message> passThroughItemProcessor = new PassThroughItemProcessor();
        return passThroughItemProcessor;
    }

    @Bean("mySqlBatchProcess")
    @StepScope
    public MySqlBatchProcess mySqlBatchProcess(){
        return new MySqlBatchProcess();
    }

    @Bean("myBatisBatchItemWriter")
    @StepScope
    public MyBatisBatchItemWriter<Message> writer(SqlSessionFactory sqlSessionFactory){
        MyBatisBatchItemWriter<Message> myBatisBatchItemWriter = new MyBatisBatchItemWriter<>();
        myBatisBatchItemWriter.setSqlSessionFactory(sqlSessionFactory);
        myBatisBatchItemWriter.setStatementId("updateByPrimaryKeySelective");
        return myBatisBatchItemWriter;
    }

    @Bean("mySqlBatchStep")
    public Step step(StepBuilderFactory stepBuilderFactory,
                     @Qualifier("myBatisPagingItemReader") MyBatisPagingItemReader myBatisPagingItemReader,
                     @Qualifier("mySqlBatchProcess") MySqlBatchProcess mySqlBatchProcess,
                     @Qualifier("myBatisBatchItemWriter") MyBatisBatchItemWriter myBatisBatchItemWriter){
        return stepBuilderFactory.get("mySqlBatchStep")
                .chunk(20)
                .reader(myBatisPagingItemReader)
                .processor(mySqlBatchProcess)
                .writer(myBatisBatchItemWriter)
                .build();
    }

    @Bean("mySqlBatchJob")
    public Job job(JobBuilderFactory jobBuilderFactory,
                   @Qualifier("mySqlBatchStep") Step mySqlBatchStep){
        return jobBuilderFactory.get("mySqlBatchJob")
                .start(mySqlBatchStep)
                .listener(new BatchJobListener())
                .preventRestart()
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
