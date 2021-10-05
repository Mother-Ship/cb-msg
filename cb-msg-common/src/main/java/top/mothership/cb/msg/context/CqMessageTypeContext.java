package top.mothership.cb.msg.context;

import top.mothership.cb.msg.enums.cq.lv2.Lv2Type;
import top.mothership.cb.msg.enums.cq.lv3.Lv3Type;

public class CqMessageTypeContext {
    private static ThreadLocal<Lv2Type> lv2TypeThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<Lv3Type> lv3TypeThreadLocal = new ThreadLocal<>();

    public static Lv2Type getLv2Type() {
        return lv2TypeThreadLocal.get();
    }

    public static Lv3Type getLv3Type() {
        return lv3TypeThreadLocal.get();
    }

    public static void set(Lv2Type lv2Type, Lv3Type lv3Type) {
        lv2TypeThreadLocal.set(lv2Type);
        lv3TypeThreadLocal.set(lv3Type);
    }

    public static void clear() {
        lv2TypeThreadLocal.remove();
        lv3TypeThreadLocal.remove();
    }
}
