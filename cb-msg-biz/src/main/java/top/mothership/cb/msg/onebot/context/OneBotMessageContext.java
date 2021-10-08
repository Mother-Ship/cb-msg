package top.mothership.cb.msg.onebot.context;

import top.mothership.cb.msg.enums.onebot.Lv2Type;
import top.mothership.cb.msg.enums.onebot.Lv3Type;

public class OneBotMessageContext {
    private static final ThreadLocal<Lv2Type> lv2TypeTL = new ThreadLocal<>();
    private static final ThreadLocal<Lv3Type> lv3TypeTL = new ThreadLocal<>();
    private static final ThreadLocal<String> rawMessageTL = new ThreadLocal<>();

    public static Lv2Type getLv2Type() {
        return lv2TypeTL.get();
    }

    public static Lv3Type getLv3Type() {
        return lv3TypeTL.get();
    }
    public static String getRawMessage() {
        return rawMessageTL.get();
    }

    public static void set(Lv2Type lv2Type, Lv3Type lv3Type, String raw) {
        lv2TypeTL.set(lv2Type);
        lv3TypeTL.set(lv3Type);
        rawMessageTL.set(raw);
    }

    public static void clear() {
        lv2TypeTL.remove();
        lv3TypeTL.remove();
        rawMessageTL.remove();
    }
}
