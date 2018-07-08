package com.lyyexample.mySqlBatchReadWrite.process;

import com.lyyexample.mySqlBatchReadWrite.entry.Message;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by liuyangyang on 2018/7/8.
 */
public class MySqlBatchProcess implements ItemProcessor<Message, Message> {
    @Override
    public Message process(Message item) throws Exception {
        item.setMessageContent(item.getMessageContent()+" to "+item.getReceiver());
        return item;
    }
}
