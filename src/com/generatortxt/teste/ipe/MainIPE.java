/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.generatortxt.teste.ipe;

import com.generatortxt.exception.ExactLengthException;
import com.generatortxt.exception.MaxLengthException;
import com.generatortxt.generator.Generator;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bosco
 */
public class MainIPE {

    public static void main(String[] args) {
        try {
            
            
            IpeRegistro ipe = new IpeRegistro();
            
            HeaderIpe35 header = new HeaderIpe35();
            header.setCpfCnpjPrestador(1353121321l);            
            header.setNumeroPrestador(9999);
            header.setNomePrestador("Willian Lucena");
            header.setQtdNotas(4);
            header.setQtdLancamentosReferencia(5);
            header.setFiller("");            
            ipe.setHeader(header);
            
            int i = 0;
            
            while(i < 10){
                
                Designativo designativo = new Designativo();
                designativo.setCnpjCpf(1353121321l);
                designativo.setNumeroSeqNota(1);
                designativo.setPeriodo(012017);
                designativo.setQtdLancamento(10);
                designativo.setTipoNota(35);
                designativo.setTipoPrestador(05);
                designativo.setValorTotalMedicamentoMaterial(new BigDecimal("859.50"));
                designativo.setValorTotalNota(new BigDecimal("9999.55"));            
                designativo.setFiller("");
                int j = 0;

                while (j < 20){
                    Lancamento lancamento = new Lancamento();
                    lancamento.setRefencia(10);
                    lancamento.setQuantidadeServico(10);
                    lancamento.setNumeroContratoSolicitante(456789);
                    lancamento.setNomeBeneficiario("João Bosco");
                    lancamento.setMatricula(45345645646l);
                    lancamento.setDia(10);
                    lancamento.setCodigoHonorario(1123);
                    lancamento.setArquivoPDF("arquivoPDF");
                    
                    designativo.getLancamento().add(lancamento);
                    
                    j++;
                }
                
                ipe.getDesignativo().add(designativo);
                
                i++;
            }
            
            Generator g = new Generator(null, Generator.CR, Generator.LF);
            
            String saida = g.toTxt(ipe);
            System.out.println(saida);
            
            Files.write(Paths.get("C:\\Users\\bosco\\Desktop/ipe.txt"), saida.getBytes());
            
            
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(MainIPE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainIPE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MaxLengthException ex) {
            Logger.getLogger(MainIPE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExactLengthException ex) {
            Logger.getLogger(MainIPE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainIPE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(MainIPE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MainIPE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(MainIPE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainIPE.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
}
