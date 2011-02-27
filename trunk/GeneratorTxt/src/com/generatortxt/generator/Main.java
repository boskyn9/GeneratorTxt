/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.generatortxt.generator;

import com.generatortxt.teste.Carro;
import com.generatortxt.teste.Pessoa;
import java.text.DecimalFormat;
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

            //DecimalFormat df  = new DecimalFormat("00.00");
            //df.setMaximumFractionDigits(1);
            //df.setMaximumIntegerDigits(3);
            //df.setDecimalSeparatorAlwaysShown(false);
            //df.setMinimumIntegerDigits(2);
            //df.setMinimumFractionDigits(2);
            //df.setRoundingMode(RoundingMode.FLOOR);
            //System.out.println("resultado: "+df.format(230556));

//            String[] No_Navegable = {
//                Integer.class.getName(),
//                Float.class.getName(),
//                String.class.getName(),
//                Double.class.getName(),
//                Date.class.getName(),
//                BigDecimal.class.getName(),
//                Number.class.getName(),
//                BigInteger.class.getName(),
//                Character.class.getName(),
//                String.class.getPackage().getName(),
//                BigDecimal.class.getPackage().getName(),
//                Date.class.getPackage().getName()
//            };
//
//            List<String> list = Arrays.asList(No_Navegable);
//
//            Pessoa pe = new Pessoa();
//            //Date pe = new Date();
//            if (list.contains(pe.getClass().getName())) {
//                System.out.println(pe.getClass().getName());
//            }
            
            double valor = 12345.678;
            // resulta em: 12346:
            formata("#####", valor);
            // resulta em: 12345,68:
            formata("###.##", valor);
            // resulta em: 12345,678:
            formata("###.###", valor);
            // resulta em: 12.345,68:
            formata("###,###.##", valor);
            // resulta em: R$ 12.345,68:
            formata("R$ ###,###.##", valor);
            // resulta em: R$ 012.345,68:
            formata("R$ 000,000.00", valor);
            // resulta em: CPF 633.191.101:
            formata("CPF ###,###,###", 633191101);
            // nao funciona bem quando os grupos de dígitos possuem
            // tamanhos diferentes
            // resulta em: CPF 6.33.19.11.01.44:
            formata("CPF ###,###,###,##", 63319110144L);
            // resulta em: RG 1.782.726
            formata("RG ###,###,###", 1782726);
            // resulta em: CODIGO 000123
            formata("CODIGO 000000", 123);
            
            if (true) {
                return;
            }
            Pessoa p = new Pessoa();
            p.setNome("PESSOA");
            p.setIdade(55);
            p.setNasc(new Date());
            p.setAltura(3.22f);
            p.setCarros(new ArrayList<Carro>());

            Carro c1 = new Carro();
            c1.setMarca("MARCA1");
            c1.setModelo("MODELO1");
            p.getCarros().add(c1);

            p.setIrmaos(new HashSet<Pessoa>());
            Pessoa p2 = new Pessoa();
            p2.setNome("PESSOA2");
            p2.setIdade(44);
            p2.setNasc(new Date());
            //p2.setAltura(2.11f);
            p.getIrmaos().add(p2);
            //          Generator g = new Generator(new DefaultType("0", " "));
            Generator g = new Generator();// padrão "0" - " "
            //Generator g = new Generator(new DelimitationType("|"));
            String saida = g.toTxt(p);
            System.out.println(saida);

//            FileWriter writer = new FileWriter(System.getProperty("user.home") + "/Desktop/teste.txt");
//            writer.write(saida);
//            writer.close();


        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void formata(String formato, double valor) {
        String saida = new DecimalFormat(formato).format(valor);
        System.out.println(valor + " formatado com " + formato + " resulta em: " + saida);
    }

}
