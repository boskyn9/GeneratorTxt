package com.generatortxt.generator;

import com.generatortxt.annotation.DateFormat;
import com.generatortxt.annotation.RealFormat;
import com.generatortxt.annotation.ExactLength;
import com.generatortxt.annotation.MaxLength;
import com.generatortxt.exception.ExactLengthException;
import com.generatortxt.exception.MaxLengthException;
import com.generatortxt.generator.types.DefaultType;
import com.generatortxt.generator.types.DelimitationType;
import com.generatortxt.generator.types.TypeGenerator;
import com.generatortxt.util.NoNavegable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author boskyn9
 */
public class Generator {

    public static char CR = (char) 0x0D;
    public static char LF = (char) 0x0A;
    public static char EOF = (char) 0x1A;
    
    private Object sped;
    private StringBuilder spedString = new StringBuilder("");
    private TypeGenerator type;
    public String l, r;
    public List<Character> quebraLinha;
    public char fimArquivo = ' ';

    public Generator() {
        type = new DefaultType();
        l = ((DefaultType) type).getLeftSide();
        r = ((DefaultType) type).getRightSide();
    }
    
    public Generator(char fimArquivo, Character... quebraLinha) {
        type = new DefaultType();
        l = ((DefaultType) type).getLeftSide();
        r = ((DefaultType) type).getRightSide();
        this.quebraLinha = Arrays.asList(quebraLinha);
        this.fimArquivo = fimArquivo;
    }

    public Generator(TypeGenerator type) {
        this.type = type;
        if (!type.hasDelimitator()) {
            l = ((DefaultType) type).getLeftSide();
            r = ((DefaultType) type).getRightSide();
        }
    }

    public String toTxt(Object sped) throws IllegalArgumentException, IllegalAccessException, MaxLengthException,
                                            ExactLengthException, ClassNotFoundException, NoSuchMethodException,
                                            InstantiationException, InvocationTargetException {
        this.sped = sped;
        Class txtClass = sped.getClass();
        Field fields[] = txtClass.getDeclaredFields();
        gerarTxt(fields, sped);
        return spedString.toString() + fimArquivo;
    }

    public void generateAnottations(Field field, Object obj, int i) throws IllegalArgumentException,
                                                                           IllegalAccessException, ExactLengthException,
                                                                           MaxLengthException {
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
                            if (i > 0) {
                                spedString.append(((DelimitationType) type).getDelimiter());
                            }
                        }
                        spedString.append(new SimpleDateFormat(format).format(content));
                    }

                } else if (annotation instanceof ExactLength) {
                    ExactLength ann = (ExactLength) annotation;
                    int length = ann.value();
                    Object content = field.get(obj);
                    int tamanho = 0;
                    if (content != null) {
                        tamanho = content.toString().length();
                    }
                    if (tamanho != length) {
                        String message = String.format("O valor do campo %s é diferente do definido.", field.getName());
                        String cause = String.format("O valor do campo %s é %s e deveria ser %s", field.getName(),
                                                     content, length);
                        throw new ExactLengthException(message, cause);
                    } else {
                        if (content != null) {
                            if (type.hasDelimitator()) {
                                if (i > 0) {
                                    spedString.append(((DelimitationType) type).getDelimiter());
                                }
                            }
                            spedString.append(content);
                        }
                    }

                } else if (annotation instanceof MaxLength) {
                    MaxLength ann = (MaxLength) annotation;
                    int max = ann.value();
                    Object content = field.get(obj);
                    int tamanho = 0;
                    if (content != null) {
                        tamanho = content.toString().length();
                    }
                    if (tamanho > max) {
                        String message = String.format("O valor do campo %s é maior que o máximo definido.", field.
                                getName());
                        String cause = String.format("O valor do campo %s é %s e deveria ser menor ou igual a %s", field.
                                getName(), content, max);
                        throw new MaxLengthException(message, cause);
                    } else {                       
                            if (type.hasDelimitator()) {
                                if (i > 0) {
                                    spedString.append(((DelimitationType) type).getDelimiter());
                                }
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
                } else if (annotation instanceof RealFormat) {
                    RealFormat ann = (RealFormat) annotation;
                    String format = ann.format();
                    Object content = field.get(obj);

                    if (content != null) {
                        if (type.hasDelimitator()) {
                            if (i > 0) {
                                spedString.append(((DelimitationType) type).getDelimiter());
                            }
                        } else {
                            DecimalFormat df = new DecimalFormat(format);
//                            System.out.println(format.indexOf("."));
//                            System.out.println(format.substring(0, format.indexOf(".")));
//                            System.out.println(format.substring(format.indexOf(".")));
                            String temp = df.format(content);
                            if (!ann.commas()) {
                                temp = temp.replaceAll(",", "").replace(".", "");
                            }
                            content = temp;
                        }
                        spedString.append(content);
                    }

                }
            }
        } else {
            Object content = field.get(obj);
            if (content != null) {
                if (type.hasDelimitator()) {
                    if (i > 0) {
                        spedString.append(((DelimitationType) type).getDelimiter());
                    }
                }
                spedString.append(content);
            }
        }
    }

    private void gerarTxt(Field[] fields, Object obj) throws IllegalArgumentException, IllegalAccessException, ExactLengthException, MaxLengthException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        boolean ql = false;               
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            if (isList(field) || isNavegable(field.getType())) {

                Class c = Class.forName(field.getType().getName());

                if (c.isAssignableFrom(List.class)) {
                    List list = (List) field.get(obj);
                    if (list != null) {
                        for (int j = 0; j < list.size(); j++) {
                            Object o = list.get(j);
                            Class listFieldClass = o.getClass();
                            gerarTxt(listFieldClass.getDeclaredFields(), o);
                        }
                    }
                } else if (c.isAssignableFrom(Set.class)) {
                    Set set = (Set) field.get(obj);
                    if (set != null) {
                        for (Iterator it = set.iterator(); it.hasNext();) {
                            Object object = it.next();
                            Class setFieldClass = object.getClass();
                            gerarTxt(setFieldClass.getDeclaredFields(), object);
                        }
                    }
                } else {
                    Field f[] = c.getDeclaredFields();
                    gerarTxt(f, field.get(obj));
                }
            } else {
                generateAnottations(field, obj, i);
                ql = true;
            }
        }
        
        if(ql){
            if(quebraLinha !=null && quebraLinha.size() > 0){
                for (Character character : quebraLinha) {
                    spedString.append(character);
                }
            }else{
                spedString.append("\n");
            }
        }
            
    }

    private Object zero(Object content, int max) {
        if(content==null)
            content = l;
        for (int i = content.toString().length(); i < max; i++) {
            content = l + content;
        }
        return content;
    }

    private Object space(Object content, int max) {
        if(content==null)
            content = r;
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

    private boolean isNavegable(Class clazz) {
        NoNavegable[] no = NoNavegable.values();
        for (int i = 0; i < no.length; i++) {
            String noNavegable = no[i].toString();
            if (clazz.getName().equals(noNavegable) || clazz.getPackage().getName().equals(noNavegable)) {
                return false;
            }
        }
        return true;
    }
}
