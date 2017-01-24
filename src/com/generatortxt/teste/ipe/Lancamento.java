package com.generatortxt.teste.ipe;

import com.generatortxt.annotation.MaxLength;

/**
 * Created by bosco on 12/01/2017.
 */
public class Lancamento {

    @MaxLength(value = 2, type = 'z')
    private Integer refencia;
    @MaxLength(value = 13, type = 'z')
    private Integer matricula;
    @MaxLength(value = 8, type = 'z')
    private Integer numeroContratoSolicitante;
    @MaxLength(value = 2, type = 'z')
    private Integer dia;
    @MaxLength(value = 8, type = 'z')
    private Integer codigoHonorario;
    @MaxLength(value = 5, type = 'z')
    private Integer quantidadeServico;
    @MaxLength(value = 43, type = 's')
    private String nomeBeneficiario;
    @MaxLength(value = 50, type = 's')
    private String arquivoPDF;

    public Integer getRefencia() {
        return refencia;
    }

    public void setRefencia(Integer refencia) {
        this.refencia = refencia;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Integer getNumeroContratoSolicitante() {
        return numeroContratoSolicitante;
    }

    public void setNumeroContratoSolicitante(Integer numeroContratoSolicitante) {
        this.numeroContratoSolicitante = numeroContratoSolicitante;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getCodigoHonorario() {
        return codigoHonorario;
    }

    public void setCodigoHonorario(Integer codigoHonorario) {
        this.codigoHonorario = codigoHonorario;
    }

    public Integer getQuantidadeServico() {
        return quantidadeServico;
    }

    public void setQuantidadeServico(Integer quantidadeServico) {
        this.quantidadeServico = quantidadeServico;
    }

    public String getNomeBeneficiario() {
        return nomeBeneficiario;
    }

    public void setNomeBeneficiario(String nomeBeneficiario) {
        this.nomeBeneficiario = nomeBeneficiario;
    }

    public String getArquivoPDF() {
        return arquivoPDF;
    }

    public void setArquivoPDF(String arquivoPDF) {
        this.arquivoPDF = arquivoPDF;
    }
}
