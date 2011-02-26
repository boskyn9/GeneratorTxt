/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.generatortxt.generator;

import com.generatortxt.annotation.DateFormat;
import com.generatortxt.annotation.ExactLength;
import com.generatortxt.annotation.MaxLength;
import com.generatortxt.exception.ExactLengthException;
import com.generatortxt.exception.MaxLengthException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author boskyn9
 */
public class Generator {

    private Object sped;
    private StringBuilder spedString = new StringBuilder("");

    public Generator() {
    }

    public Generator(Object sped) {
        this.sped = sped;
    }

    public String toTxt() throws IllegalArgumentException, IllegalAccessException, MaxLengthException, ExactLengthException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        Class txtClass = sped.getClass();
        Field fields[] = txtClass.getDeclaredFields();
        gerarTxt(fields, sped, txtClass);
        return spedString.toString();
    }

    public void generateAnottations(Field field, Object obj) throws IllegalArgumentException, IllegalAccessException, ExactLengthException, MaxLengthException {
        Annotation[] annotations = field.getDeclaredAnnotations();
        if (annotations != null && annotations.length > 0) {
            for (int j = 0; j < annotations.length; j++) {
                Annotation annotation = annotations[j];

                if (annotation instanceof DateFormat) {
                    DateFormat ann = (DateFormat) annotation;
                    String format = ann.format();
                    Date content = (Date) field.get(obj);
                    if (content != null) {
                        spedString.append(new SimpleDateFormat(format).format(content));
                    }

                } else if (annotation instanceof ExactLength) {
                    ExactLength ann = (ExactLength) annotation;
                    int length = ann.value();
                    Object content = field.get(obj);
                    if (content.toString().length() != length) {
                        String message = String.format("O valor do campo %s é diferente do definido.", field.getName());
                        String cause = String.format("O valor do campo %s é %s e deveria ser menor ou igual a %s", field.getName(), content, length);
                        throw new ExactLengthException(message, cause);
                    } else {
                        if (content != null) {
                            spedString.append(content);
                        }
                    }

                } else if (annotation instanceof MaxLength) {
                    MaxLength ann = (MaxLength) annotation;
                    int max = ann.value();
                    Object content = field.get(obj);
                    if (content.toString().length() > max) {
                        String message = String.format("O valor do campo %s é maior que o máximo definido.", field.getName());
                        String cause = String.format("O valor do campo %s é %s e deveria ser menor ou igual a %s", field.getName(), content, max);
                        throw new MaxLengthException(message, cause);
                    } else {
                        if (content != null) {
                            switch (ann.type()) {
                                case 'z':
                                    content = zero(content, max);
                                    break;
                                case 's':
                                    content = space(content, max);
                                    break;
                            }
                            spedString.append(content);
                        }
                    }
                }
            }
        } else {
            Object content = field.get(obj);
            if (content != null) {
                spedString.append(content);
            }
        }
    }

    private void gerarTxt(Field[] fields, Object obj, Class ownerClass) throws IllegalArgumentException, IllegalAccessException, ExactLengthException, MaxLengthException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            if (field.get(obj) == null || isList(field)) {

                Class c = Class.forName(field.getType().getName());
                if (c.isAssignableFrom(List.class)) {
                    List list = (List) field.get(obj);
                    if (list != null) {
                        for (int j = 0; j < list.size(); j++) {
                            Class listFieldClass = list.get(j).getClass();

                            Constructor ct = listFieldClass.getConstructor();
                            Object o = ct.newInstance();
                            Field f[] = listFieldClass.getDeclaredFields();

                            gerarTxt(f, o, listFieldClass);

                        }
                    }
                } else if (c.isAssignableFrom(Set.class)) {
                } else {
                    Constructor ct = c.getConstructor();
                    Object o = ct.newInstance(obj);
                    Field f[] = c.getDeclaredFields();
                    gerarTxt(f, o, c);
                }

            } else {
                generateAnottations(field, obj);
            }

        }
    }

    private Object zero(Object content, int max) {
        for (int i = content.toString().length(); i < max; i++) {
            content = "0" + content;
        }
        return content;
    }

    private Object space(Object content, int max) {
        for (int i = content.toString().length(); i < max; i++) {
            content = content + " ";
        }
        return content;
    }

    private boolean isList(Field field) {
        if (field.getType().isArray() || field.getType().isAssignableFrom(List.class) || field.getType().isAssignableFrom(Set.class)) {
            return true;
        }
        return false;
    }
}
