package com.lyyexample.fileReadWrite.jobConfig;

import com.lyyexample.fileReadWrite.entry.FileEntry;
import com.lyyexample.fileReadWrite.lineMapper.FileWriteLineMapper;
import com.lyyexample.fileReadWrite.lineMapper.TxtLineMapper;
import com.lyyexample.fileReadWrite.process.TxtFileProcess;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;


/**
 * Created by liuyangyang on 2018/6/12.
 */
@Configuration
@EnableBatchProcessing
public class TxtFileBatchJob {

    @Bean("fileRead")
    @StepScope
    public FlatFileItemReader<FileEntry> reader(@Value("#{jobParameters[inputPath]}") String inputPath){
        FlatFileItemReader<FileEntry> fileRead = new FlatFileItemReader<>();
        fileRead.setResource(new ClassPathResource(inputPath));
        fileRead.setLineMapper(new TxtLineMapper());
        return fileRead;
    }

    @Bean("fileWriter")
    @StepScope
    public FlatFileItemWriter writer(){
        FlatFileItemWriter<FileEntry> fileWriter = new FlatFileItemWriter<>();
        fileWriter.setAppendAllowed(true);
//        fileWriter.setForceSync(true);
//        fileWriter.setSaveState(true);
        fileWriter.setShouldDeleteIfExists(true);
        fileWriter.setLineAggregator(new FileWriteLineMapper());
        fileWriter.setEncoding("UTF-8");
        fileWriter.setResource(new FileSystemResource("F:/data/output.txt"));
        return fileWriter;
    }

    @Bean("fileProcess")
    @StepScope
    public TxtFileProcess process(){
        return new TxtFileProcess();
    }

    @Bean("fileStep")
    public Step txtStep(
            StepBuilderFactory stepBuilderFactory,
            @Qualifier("fileRead") FlatFileItemReader fileRead,
            @Qualifier("fileProcess") TxtFileProcess fileProcess,
            @Qualifier("fileWriter") FlatFileItemWriter fileWriter){
        return stepBuilderFactory.get("txtStep").<FileEntry,FileEntry>chunk(10)
                .reader(fileRead)
                .processor(fileProcess)
                .writer(fileWriter).build();

    }

    @Bean("txtJob")
    public Job txtJob(
            JobBuilderFactory jobBuilderFactory,
            @Qualifier("fileStep") Step fileStep){
        return jobBuilderFactory.get("txtJob")
                .preventRestart()
                .incrementer(new RunIdIncrementer())
                .start(fileStep)
                .build();
    }

}
