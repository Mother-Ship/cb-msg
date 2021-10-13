package top.mothership.cb.msg.utils;


import top.mothership.cb.msg.enums.onebot.Lv2Type;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReflectionUtil {
    @NotNull
    public static <T> List<T> getAllThisTypePublicFields(Class<T> clz) {
        Field[] fields = clz.getFields();
        List<T> tempList = new ArrayList<>(fields.length);
        for (Field field : fields) {
            if (Modifier.isPublic(field.getModifiers())) {
                try {
                    if (Objects.equals(field.get(null).getClass(),clz))
                        tempList.add((T) field.get(null));
                } catch (IllegalAccessException ignore) {
                }
            }
        }
        return tempList;
    }
}
