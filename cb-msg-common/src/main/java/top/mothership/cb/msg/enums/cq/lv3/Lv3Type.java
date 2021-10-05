package top.mothership.cb.msg.enums.cq.lv3;

import top.mothership.cb.msg.enums.cq.lv2.Lv2Type;

public interface Lv3Type {
    Lv2Type getParentType();
    String name();
}
