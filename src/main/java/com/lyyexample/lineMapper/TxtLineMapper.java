package com.lyyexample.lineMapper;

import com.lyyexample.entry.FileEntry;
import org.springframework.batch.item.file.LineMapper;

/**
 * Created by liuyangyang on 2018/7/3.
 */
public class TxtLineMapper implements LineMapper {
    @Override
    public FileEntry mapLine(String s, int i) throws Exception {
        FileEntry fileEntry = new FileEntry();
        fileEntry.setContent(s);
        return fileEntry;
    }
}
