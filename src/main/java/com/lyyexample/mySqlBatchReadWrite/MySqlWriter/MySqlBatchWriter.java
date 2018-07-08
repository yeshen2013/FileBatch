package com.lyyexample.mySqlBatchReadWrite.MySqlWriter;

import com.lyyexample.fileReadWrite.entry.FileEntry;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Created by liuyangyang on 2018/7/8.
 */
public class MySqlBatchWriter implements ItemWriter<FileEntry> {

    @Override
    public void write(List<? extends FileEntry> list) throws Exception {

    }
}
