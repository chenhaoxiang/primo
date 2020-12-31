package wiki.primo.dubbo.swagger.core.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static springfox.documentation.schema.Types.typeNameFor;

/**
 * TypeUtils
 *
 * @author chenhx
 * @date 2019-07-16 11:37
 */
public class TypeUtils {
    public static boolean isContainerType(Class type) {
        return List.class.isAssignableFrom(type) ||
                Set.class.isAssignableFrom(type) ||
                (Collection.class.isAssignableFrom(type) && !isMapType(type)) ||
                type.isArray();
    }

    public static boolean isBaseType(Class type) {
        return springfox.documentation.schema.Types.isBaseType(typeNameFor(type));
    }

    public static boolean isMapType(Class type) {
        return Map.class.isAssignableFrom(type);
    }

    public static boolean isComplexObjectType(Class<?> type) {
        return !isContainerType(type) && !isBaseType(type) && !isMapType(type);
    }

}
