package com.lyyexample.jobConfig;

import com.lyyexample.entry.FileEntry;
import com.lyyexample.process.TxtFileProcess;
import com.lyyexample.reader.FileBatchReader;
import com.lyyexample.writer.FileBatchWrite;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 * Created by liuyangyang on 2018/6/12.
 */
@Component
public class TxtFileBatchJob {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public FileBatchReader fileBatchReader;

    @Autowired
    public TxtFileProcess txtFileProcess;

    @Autowired
    public FileBatchWrite fileBatchWrite;

    @Bean
    public FlatFileItemReader<FileEntry> reader(){
        FlatFileItemReader<FileEntry> fileRead = new FlatFileItemReader<>();
        fileRead.setResource(new ClassPathResource("test.txt"));
        return fileRead;
    }

    @Bean
    public FlatFileItemWriter<FileEntry> writer(){
        FlatFileItemWriter<FileEntry> fileWriter = new FlatFileItemWriter<>();
        fileWriter.setAppendAllowed(true);
        fileWriter.setEncoding("UTF-8");
        fileWriter.setResource(new ClassPathResource("/data/output.txt"));
        return fileWriter;
    }

    public Step txtStep(){
        return stepBuilderFactory.get("txtStep").<FileEntry,FileEntry>chunk(10)
                .reader(fileBatchReader)
                .processor(txtFileProcess)
                .writer(fileBatchWrite).build();

    }

    @Bean
    public Job txtJob(){
        jobBuilderFactory.get("txtJob").incrementer(new RunIdIncrementer())
                .flow(txtStep()).end().build();
    }

}
