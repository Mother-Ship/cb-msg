package top.mothership.cb.msg.utils;

import java.util.Locale;

public class CbEnumUtil {
    public static <T extends Enum<T>> T getOneBotEnumOrNull(Class<T> enumClass, String type) {
        try {
            return Enum.valueOf(enumClass, type.toUpperCase(Locale.ROOT));
        } catch (Exception ex) {
            return null;
        }
    }

    public static <T extends Enum<T>> T getEnumOrNull(Class<T> enumClass, String type) {
        try {
            return Enum.valueOf(enumClass, type);
        } catch (Exception ex) {
            return null;
        }
    }

    public static <T extends Enum<T>> T getEnumOrException(Class<T> enumClass, String type) {
        try {
            return Enum.valueOf(enumClass, type);
        } catch (Exception ex) {
            throw new RuntimeException("没有找到枚举值，指定枚举类："
                    + (enumClass == null ? "null" : enumClass.getSimpleName())
                    + "，枚举值：" + type);
        }
    }
}
