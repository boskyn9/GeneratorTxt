/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.generatortxt.generator;

import com.generatortxt.generator.types.DefaultType;
import com.generatortxt.teste.Carro;
import com.generatortxt.teste.Pessoa;
import com.generatortxt.util.NoNavegable;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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
            p.setNome("PESSOA");
            p.setIdade(55);
            p.setNasc(new Date());
            p.setAltura(3.22f);
            p.setCarros(new ArrayList<Carro>());
            Carro c1 = new Carro();
            c1.setMarca("MARCA1");
            c1.setModelo("MODELO1");
            Pessoa montador = new Pessoa();
            montador.setNome("MONTADOR1");
            c1.setMontador(montador);

            Carro c2 = new Carro();
            c2.setMarca("MARCA2");
            c2.setModelo("MODELO2");
            Pessoa montador2 = new Pessoa();
            montador.setNome("MONTADOR2");
            c2.setMontador(montador2);
            p.getCarros().add(c1);
            p.getCarros().add(c2);

            p.setIrmaos(new HashSet<Pessoa>());
            Pessoa p2 = new Pessoa();
            p2.setNome("PESSOA2");
            p2.setIdade(44);
            p2.setNasc(new Date());
            p2.setAltura(2.11f);
            p.getIrmaos().add(p2);
            Pessoa p3 = new Pessoa();
            p3.setNome("PESSOA3");
            p3.setIdade(33);
            p3.setNasc(new Date());
            p3.setAltura(8.77f);
            p.getIrmaos().add(p3);
            //          Generator g = new Generator(new DefaultType("0", " "));
                        Generator g = new Generator();// padrão "0" - " "
                        //Generator g = new Generator(new DelimitationType("|"));
                        String saida = g.toTxt(p);
                        System.out.println(saida);
            
                        FileWriter writer = new FileWriter(System.getProperty("user.home")+"/Desktop/teste.txt");
                        writer.write(saida);
                        writer.close();
           

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
