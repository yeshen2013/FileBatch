package com.lyyexample.reader;

import com.lyyexample.fileReadWrite.entry.FileEntry;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by liuyangyang on 2018/6/12.
 */
public class FileBatchReader implements ItemReader{

    @Override
    @Bean
    public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        FlatFileItemReader<FileEntry> fileRead = new FlatFileItemReader<>();
        fileRead.setResource(new ClassPathResource("c152.txt"));
        return fileRead;
    }
}
