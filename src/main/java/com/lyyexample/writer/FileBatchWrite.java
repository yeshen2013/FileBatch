package com.lyyexample.writer;

import com.lyyexample.fileReadWrite.entry.FileEntry;
import com.lyyexample.listener.ServiceListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Created by liuyangyang on 2018/6/12.
 */
public class FileBatchWrite implements ItemWriter<FileEntry> {
    private static final Logger log = LoggerFactory.getLogger(FileBatchWrite.class);

    @Override
    public void write(List<? extends FileEntry> list) throws Exception {
        log.info("batch 写入！");

    }
}
