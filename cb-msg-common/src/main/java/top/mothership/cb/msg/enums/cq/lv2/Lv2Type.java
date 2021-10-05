package top.mothership.cb.msg.enums.cq.lv2;

import top.mothership.cb.msg.enums.cq.PostType;
import top.mothership.cb.msg.enums.cq.lv3.Lv3Type;
import top.mothership.cb.msg.utils.CbEnumUtil;

import java.lang.reflect.Field;
import java.util.ServiceLoader;

public interface Lv2Type {
    PostType getParentType();
    String name();

}
