package com.lyyexample.fileReadWrite.lineMapper;

import com.lyyexample.fileReadWrite.entry.FileEntry;
import org.springframework.batch.item.file.transform.LineAggregator;

/**
 * Created by liuyangyang on 2018/7/7.
 */
public class FileWriteLineMapper implements LineAggregator<FileEntry> {
    @Override
    public String aggregate(FileEntry fileEntry) {
        StringBuffer tmp = new StringBuffer();
        tmp.append(fileEntry.getContent());
        return tmp.toString();
    }
}
