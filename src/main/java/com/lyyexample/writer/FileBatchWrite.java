package com.lyyexample.writer;

import com.lyyexample.fileReadWrite.entry.FileEntry;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Created by liuyangyang on 2018/6/12.
 */
public class FileBatchWrite implements ItemWriter<FileEntry> {

    @Override
    public void write(List<? extends FileEntry> list) throws Exception {
        System.out.println("batch 写入！");

    }
}
