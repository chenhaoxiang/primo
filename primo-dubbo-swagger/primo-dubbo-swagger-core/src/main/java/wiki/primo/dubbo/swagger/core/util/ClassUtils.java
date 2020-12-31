package wiki.primo.dubbo.swagger.core.util;

import io.swagger.annotations.ApiModelProperty;
import javassist.CannotCompileException;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.BooleanMemberValue;
import javassist.bytecode.annotation.StringMemberValue;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author chenhx
 */
@Slf4j
public class ClassUtils {

    public static Class make(String className, Class[] fieldTypes, List<String> names, List<String> values) {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass;
        try {
            ctClass = pool.get(className);
        } catch (NotFoundException notFoundException) {
            ctClass = pool.makeClass(className);
            try {
                for (int i = 0; i < fieldTypes.length; i++) {
                    ctClass.addField(createField(fieldTypes[i], names.get(i), values.get(i), ctClass));
                }
                ctClass.writeFile("target/classes");
                return ctClass.toClass();
            } catch (Exception e) {
                log.error("Dynamically generated class error :", e);
            }
        }
        if (Objects.nonNull(ctClass)) {
            try {
                return pool.getClassLoader().loadClass(className);
            } catch (ClassNotFoundException e) {
                log.error("ClassNotFoundException : ", e);
            }
        }
        return null;
    }

    private static CtField createField(Class aClass, String name, String value, CtClass ctClass) throws NotFoundException, CannotCompileException {
        ClassPool.getDefault().insertClassPath(new ClassClassPath(aClass));
        CtField field = new CtField(ClassPool.getDefault().get(aClass.getName()), name, ctClass);
        field.setModifiers(Modifier.PUBLIC);
        ConstPool constPool = ctClass.getClassFile().getConstPool();
        AnnotationsAttribute attr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation ann = new Annotation(ApiModelProperty.class.getName(), constPool);
        ann.addMemberValue("value", new StringMemberValue(value, constPool));
        ann.addMemberValue("name", new StringMemberValue(name, constPool));
        ann.addMemberValue("required", new BooleanMemberValue(true, constPool));
        attr.addAnnotation(ann);
        field.getFieldInfo().addAttribute(attr);
        return field;
    }

    /**
     * 判断一个类是否为基本数据类型。
     *
     * @param clazz 要判断的类。
     * @return true 表示为基本数据类型。
     */
    public static boolean isBaseDataType(Class clazz) {
        return
                (
                        clazz.equals(String.class) || clazz.equals(Integer.class) ||
                                clazz.equals(Byte.class) ||
                                clazz.equals(Long.class) ||
                                clazz.equals(Double.class) ||
                                clazz.equals(Float.class) ||
                                clazz.equals(Character.class) ||
                                clazz.equals(Short.class) ||
                                clazz.equals(BigDecimal.class) ||
                                clazz.equals(BigInteger.class) ||
                                clazz.equals(Boolean.class) ||
                                clazz.equals(Date.class) ||
                                clazz.isPrimitive()
                );
    }
}
