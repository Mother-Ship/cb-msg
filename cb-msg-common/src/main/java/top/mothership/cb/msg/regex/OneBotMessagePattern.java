package top.mothership.cb.msg.regex;

import java.util.regex.Pattern;

public class OneBotMessagePattern {
    /**
     * 匹配所有非汉字、字母、数字字符；用于复读禁言时去除干扰文本
     */
    public final static Pattern REPEAT_DE_INTERFERING_REGEX = Pattern.compile("[^\\u4e00-\\u9fa5a-zA-Z0-9]");

    /**
     * 匹配所有CQ码；用于复读禁言计数时，去除消息内的CQ码
     */
    public final static Pattern CQ_CODE = Pattern.compile("^\\[CQ:.+,.+=.+]$");

}
