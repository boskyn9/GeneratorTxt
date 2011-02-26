/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.generatortxt.generator;

import com.generatortxt.teste.Carro;
import com.generatortxt.teste.Pessoa;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author boskyn9
 */
public class Main {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {        
        try {
            //        Pessoa p = new Pessoa();
            //
            //        Field[] fields = p.getClass().getDeclaredFields();
            //        for (int j = 0; j < fields.length; j++) {
            //            try {
            //                Field field = fields[j];
            //                field.setAccessible(true);
            //                System.out.println(field.getName());
            //                System.out.println(field.get(p));
            //                if(field.get(p)==null){
            //                    System.out.println(field.getType().getName());
            //                    Class c = Class.forName(field.getType().getName());
            //
            //                    Constructor ct  = c.getConstructor();
            //                    Object o = ct.newInstance(null);
            //
            //                    Field f[] = c.getDeclaredFields();
            //                    for (int i = 0; i < f.length; i++) {
            //                        Field field1 = f[i];
            //                        field1.setAccessible(true);
            //                        System.out.println(field1.getName());
            //                        System.out.println(field1.get(o));
            //                    }
            //                }
            //            } catch (InstantiationException ex) {
            //                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            //            } catch (IllegalAccessException ex) {
            //                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            //            } catch (IllegalArgumentException ex) {
            //                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            //            } catch (InvocationTargetException ex) {
            //                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            //            } catch (NoSuchMethodException ex) {
            //                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            //            } catch (SecurityException ex) {
            //                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            //            } catch (ClassNotFoundException ex) {
            //                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            //            }
            //        }
            
            Pessoa p = new Pessoa();
            p.setCarros(new ArrayList<Carro>());
            Carro c1 = new Carro();
            Carro c2 = new Carro();
            p.getCarros().add(c1);
            p.getCarros().add(c2);
            Generator g = new Generator(p);
            System.out.println(g.toTxt());

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
