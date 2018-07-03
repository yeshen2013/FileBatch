package com.lyyexample.process;

import com.lyyexample.entry.FileEntry;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by liuyangyang on 2018/6/12.
 */
public class TxtFileProcess implements ItemProcessor<FileEntry, FileEntry> {


    @Override
    public FileEntry process(FileEntry fileEntry) throws Exception {
        return fileEntry;
    }
}
