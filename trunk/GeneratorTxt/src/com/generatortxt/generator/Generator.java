package com.generatortxt.generator;

import com.generatortxt.annotation.DateFormat;
import com.generatortxt.annotation.ExactLength;
import com.generatortxt.annotation.MaxLength;
import com.generatortxt.exception.ExactLengthException;
import com.generatortxt.exception.MaxLengthException;
import com.generatortxt.generator.types.DefaultType;
import com.generatortxt.generator.types.DelimitationType;
import com.generatortxt.generator.types.TypeGenerator;
import com.generatortxt.util.NoNavegable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author boskyn9
 */
public class Generator {

    private Object sped;
    private StringBuilder spedString = new StringBuilder("");
    private TypeGenerator type;

    public String l,r;

    public Generator() {
        type = new DefaultType();
        l = ((DefaultType)type).getLeftSide();
        r = ((DefaultType)type).getRightSide();
    }

    public Generator(TypeGenerator type) {
        this.type = type;
        if(!type.hasDelimitator()){
            l = ((DefaultType)type).getLeftSide();
            r = ((DefaultType)type).getRightSide();
        }
    }

    public String toTxt(Object sped) throws IllegalArgumentException, IllegalAccessException, MaxLengthException, ExactLengthException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        this.sped = sped;
        Class txtClass = sped.getClass();
        Field fields[] = txtClass.getDeclaredFields();
        gerarTxt(fields, sped, txtClass);
        return spedString.toString();
    }

    public void generateAnottations(Field field, Object obj, int i) throws IllegalArgumentException, IllegalAccessException, ExactLengthException, MaxLengthException {
        Annotation[] annotations = field.getDeclaredAnnotations();
        if (annotations != null && annotations.length > 0) {
            for (int j = 0; j < annotations.length; j++) {
                Annotation annotation = annotations[j];

                if (annotation instanceof DateFormat) {
                    DateFormat ann = (DateFormat) annotation;
                    String format = ann.format();
                    Date content = (Date) field.get(obj);
                    if (content != null) {
                        if (type.hasDelimitator()) {
                            if(i > 0)
                            spedString.append(((DelimitationType) type).getDelimiter());
                        }
                        spedString.append(new SimpleDateFormat(format).format(content));
                    }

                } else if (annotation instanceof ExactLength) {
                    ExactLength ann = (ExactLength) annotation;
                    int length = ann.value();
                    Object content = field.get(obj);
                    int tamanho = 0;
                    if(content!=null)
                        tamanho = content.toString().length();
                    if (tamanho != length) {
                        String message = String.format("O valor do campo %s é diferente do definido.", field.getName());
                        String cause = String.format("O valor do campo %s é %s e deveria ser menor ou igual a %s", field.getName(), content, length);
                        throw new ExactLengthException(message, cause);
                    } else {
                        if (content != null) {
                            if (type.hasDelimitator()) {
                                if(i > 0)
                                spedString.append(((DelimitationType) type).getDelimiter());
                            }
                            spedString.append(content);
                        }
                    }

                } else if (annotation instanceof MaxLength) {
                    MaxLength ann = (MaxLength) annotation;
                    int max = ann.value();
                    Object content = field.get(obj);
                    int tamanho = 0;
                    if(content!=null)
                        tamanho = content.toString().length();
                    if (tamanho > max) {
                        String message = String.format("O valor do campo %s é maior que o máximo definido.", field.getName());
                        String cause = String.format("O valor do campo %s é %s e deveria ser menor ou igual a %s", field.getName(), content, max);
                        throw new MaxLengthException(message, cause);
                    } else {
                        if (content != null) {
                            if (type.hasDelimitator()) {
                                if(i > 0)
                                spedString.append(((DelimitationType) type).getDelimiter());
                            } else {
                                switch (ann.type()) {
                                    case 'z':
                                        content = zero(content, max);
                                        break;
                                    case 's':
                                        content = space(content, max);
                                        break;
                                }
                            }
                            spedString.append(content);
                        }
                    }
                }
            }
        } else {
            Object content = field.get(obj);
            if (content != null) {
                if (type.hasDelimitator()) {
                    if(i > 0)
                    spedString.append(((DelimitationType) type).getDelimiter());
                }
                spedString.append(content);
            }
        }
    }

    private void gerarTxt(Field[] fields, Object obj, Class ownerClass) throws IllegalArgumentException, IllegalAccessException, ExactLengthException, MaxLengthException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException {

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            if (isList(field) || isNavegable(field.getType())) {

                Class c = Class.forName(field.getType().getName());

                if (c.isAssignableFrom(List.class)) {
                    List list = (List) field.get(obj);
                    if (list != null) {
                        for (int j = 0; j < list.size(); j++) {
//                            Class listFieldClass = list.get(j).getClass();
//
//                            Constructor ct = listFieldClass.getConstructor();
//                            Object o = ct.newInstance();
//                            Field f[] = listFieldClass.getDeclaredFields();
//
//                            gerarTxt(f, o, listFieldClass);

                            Object o = list.get(j);
                            Class listFieldClass = o.getClass();
                            gerarTxt(listFieldClass.getDeclaredFields(), o, listFieldClass);
                        }
                    }
                } else if (c.isAssignableFrom(Set.class)) {
                    Set set = (Set) field.get(obj);
                    if (set != null) {
                        for (Iterator it = set.iterator(); it.hasNext();) {
                            Object object = it.next();
                            Class setFieldClass = object.getClass();

//                            Constructor ct = setFieldClass.getConstructor();
//                            Object o = ct.newInstance();
//                            Field f[] = setFieldClass.getDeclaredFields();

                            //gerarTxt(f, o, setFieldClass);
                            gerarTxt(setFieldClass.getDeclaredFields(), object, setFieldClass);
                        }
                    }
                } else {
                    Field f[] = c.getDeclaredFields();
                    gerarTxt(f, field.get(obj), c);
                }

            } else {
                generateAnottations(field, obj, i);
            }

        }
    }

    private Object zero(Object content, int max) {
        for (int i = content.toString().length(); i < max; i++) {
            content = l + content;
        }
        return content;
    }

    private Object space(Object content, int max) {
        for (int i = content.toString().length(); i < max; i++) {
            content = content + r;
        }
        return content;
    }

    private boolean isList(Field field) {
        if (field.getType().isArray() || field.getType().isAssignableFrom(List.class) || field.getType().isAssignableFrom(Set.class)) {
            return true;
        }
        return false;
    }

    private boolean isNavegable(Class clazz){
         NoNavegable[] no = NoNavegable.values();
            for (int i = 0; i < no.length; i++) {
                String noNavegable = no[i].toString();
                if(clazz.getName().equals(noNavegable) || clazz.getPackage().getName().equals(noNavegable)){
                    return false;
                }
            }
         return true;
    }
}
