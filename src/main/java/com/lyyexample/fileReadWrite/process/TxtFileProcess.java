package com.lyyexample.fileReadWrite.process;

import com.lyyexample.fileReadWrite.entry.FileEntry;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by liuyangyang on 2018/6/12.
 */
public class TxtFileProcess implements ItemProcessor<FileEntry, FileEntry> {


    @Override
    public FileEntry process(FileEntry fileEntry) throws Exception {
        System.out.println("process !!!"+fileEntry.getContent());
        return fileEntry;
    }
}
