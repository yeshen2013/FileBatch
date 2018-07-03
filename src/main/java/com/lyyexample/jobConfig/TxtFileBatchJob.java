package com.lyyexample.jobConfig;

import com.lyyexample.entry.FileEntry;
import com.lyyexample.lineMapper.TxtLineMapper;
import com.lyyexample.process.TxtFileProcess;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by liuyangyang on 2018/6/12.
 */
@Configuration
@EnableBatchProcessing
public class TxtFileBatchJob {

    @Bean("fileRead")
    public FlatFileItemReader<FileEntry> reader(){
        FlatFileItemReader<FileEntry> fileRead = new FlatFileItemReader<>();
        fileRead.setResource(new ClassPathResource("test.txt"));
        fileRead.setLineMapper(new TxtLineMapper());
        return fileRead;
    }

    @Bean("fileWriter")
    public FlatFileItemWriter<FileEntry> writer(){
        FlatFileItemWriter<FileEntry> fileWriter = new FlatFileItemWriter<>();
        fileWriter.setAppendAllowed(true);
        fileWriter.setEncoding("UTF-8");
        fileWriter.setResource(new ClassPathResource("/data/output.txt"));
        return fileWriter;
    }

    @Bean("fileProcess")
    public TxtFileProcess process(){
        return new TxtFileProcess();
    }

    @Bean("fileStep")
    @JobScope
    public Step txtStep(StepBuilderFactory stepBuilderFactory,
                        @Qualifier("fileRead") FlatFileItemReader fileRead,
                        @Qualifier("fileProcess") TxtFileProcess fileProcess,
                        @Qualifier("fileWriter") FlatFileItemWriter fileWriter){
        return stepBuilderFactory.get("txtStep").<FileEntry,FileEntry>chunk(10)
                .reader(fileRead)
                .processor(fileProcess)
                .writer(fileWriter).build();

    }

    @Bean("txtJob")
    public Job txtJob(JobBuilderFactory jobBuilderFactory,
                      @Qualifier("fileStep") Step fileStep){
        return jobBuilderFactory.get("txtJob")
                .incrementer(new RunIdIncrementer())
                .start(fileStep).build();
    }

}
