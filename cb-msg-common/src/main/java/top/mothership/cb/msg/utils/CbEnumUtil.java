package top.mothership.cb.msg.utils;

import java.util.Locale;

public class CbEnumUtil {
    public static<T extends Enum<T>> T getOneBotEnum(Class<T> enumClass, String type){
        try {
            return Enum.valueOf(enumClass, type.toUpperCase(Locale.ROOT));
        } catch (Exception ex) {
            return null;
        }
    }
}
