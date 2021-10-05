package top.mothership.cb.msg.entry.cq.processor.impl;


import top.mothership.cb.msg.entry.cq.processor.CqMessageProcessor;
import top.mothership.cb.msg.enums.cq.PostType;
import top.mothership.cb.msg.model.BaseCqMessage;
import top.mothership.cb.msg.utils.CbEnumUtil;

public class GroupMessageProcessor implements CqMessageProcessor {
    @Override
    public void process(String rawCqMessage) {

    }

    @Override
    public boolean isFit(BaseCqMessage baseCqMessage) {
        PostType postType = CbEnumUtil.getCqType(PostType.class, baseCqMessage.getPostType());
        return false;
    }
}
