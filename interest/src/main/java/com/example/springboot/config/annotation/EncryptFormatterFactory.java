package com.example.springboot.config.annotation;

import com.example.springboot.utils.Md5Util;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.io.Serializable;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * @ClassName EncryptFormatter
 * @Author zf
 * @Date 2020/11/9
 * @Version V1.0
 * @Description 格式化工厂
 **/
public class EncryptFormatterFactory implements AnnotationFormatterFactory<Encrypt> {
    private final EncryptFormatter encryptFormatter;
    private final Set<Class<?>> fieldTypes;

    public EncryptFormatterFactory() {
        Set<Class<?>> set = new HashSet<>();
        set.add(String.class);
        this.fieldTypes = set;
        this.encryptFormatter = new EncryptFormatter();
    }

    /**
     * 返回的是所有会用于属性上说明需要格式化的注解集合
     */
    @Override
    public Set<Class<?>> getFieldTypes() {
        return fieldTypes;
    }

    /**
     * 返回的是此格式化工厂使用到的格式化工具。
     */
    @Override
    public Printer<?> getPrinter(Encrypt encrypt, Class<?> aClass) {
        return encryptFormatter;
    }

    /**
     * 返回的是反（逆）格式化的工具。
     */
    @Override
    public Parser<?> getParser(Encrypt encrypt, Class<?> aClass) {
        return encryptFormatter;
    }

    private class EncryptFormatter implements Formatter<String>, Serializable {

        @Override
        public String parse(String text, Locale locale) throws ParseException {
            if (text != null) {
                    text = Md5Util.encode(text);
            }
            return text;
        }

        @Override
        public String print(String s, Locale locale) {
            return s;
        }
    }
}


