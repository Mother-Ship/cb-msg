package top.mothership.cb.msg.onebot.processor;

public interface OneBotMessageProcessor {
    void process(String rawMessage);

    boolean isFit();
}
