package com.lyyexample.fileReadWrite.process;

import com.lyyexample.fileReadWrite.entry.FileEntry;
import com.lyyexample.listener.ServiceListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by liuyangyang on 2018/6/12.
 */
public class TxtFileProcess implements ItemProcessor<FileEntry, FileEntry> {
    private static final Logger log = LoggerFactory.getLogger(TxtFileProcess.class);

    @Override
    public FileEntry process(FileEntry fileEntry) throws Exception {
        log.info("process !!!"+fileEntry.getContent());
        return fileEntry;
    }
}
