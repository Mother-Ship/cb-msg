package top.mothership.cb.msg.utils;

import org.apache.commons.lang.enums.EnumUtils;
import top.mothership.cb.msg.enums.cq.lv2.Lv2Type;

import java.util.Locale;
import java.util.ServiceLoader;

public class CbEnumUtil {
    public static<T extends Enum<T>> T getCqType(Class<T> enumClass, String type){
        return Enum.valueOf(enumClass, type.toUpperCase(Locale.ROOT));
    }
}
