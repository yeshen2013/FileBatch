package com.lyyexample.writer;

import com.lyyexample.entry.FileEntry;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by liuyangyang on 2018/6/12.
 */
@Component
@StepScope
public class FileBatchWrite implements ItemWriter<FileEntry> {

    @Override
    public void write(List<? extends FileEntry> list) throws Exception {
        FlatFileItemWriter<FileEntry> txtItemWriter = new FlatFileItemWriter<>();
    }
}
