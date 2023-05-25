package cn.tanglaoer.demo;

import cn.tanglaoer.annotation.MyAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.*;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Set;

/**
 * @author <a href="https://github.com/TangLaoEr">tks</a>
 * @Date 2023/5/11
 */

@Slf4j
public class TestReflections {
    public static void main(String[] args) {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("cn.tanglaoer"))
                .setScanners(Scanners.TypesAnnotated, Scanners.SubTypes)
        );

        Set<Class<?>> annotations = reflections.getTypesAnnotatedWith(MyAnnotation.class);
        System.out.println(annotations.size());
        for (Class<?> aClass: annotations) {
            MyAnnotation annotation = aClass.getAnnotation(MyAnnotation.class);
            System.out.println(annotation.value());
        }
    }

    @Test
    public void test() {

    }
}
