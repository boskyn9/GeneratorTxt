package com.generatortxt.teste.ipe;

import com.generatortxt.annotation.MaxLength;
import com.generatortxt.annotation.RealFormat;

import java.math.BigDecimal;

/**
 * Created by bosco on 12/01/2017.
 */
public class Designativo {

    @MaxLength(value = 2, type = 'z')
    private Integer zeros = 0;
    @MaxLength(value = 2, type = 'z')
    private Integer tipoNota;
    @MaxLength(value = 2, type = 'z')
    private Integer qtdLancamento;
    @MaxLength(value = 14, type = 'z')
    private Long cnpjCpf;
    @RealFormat(format="00000000000.00", commas = false)
    private BigDecimal valorTotalNota;
    @RealFormat(format="0000000.00", commas = false)
    private BigDecimal valorTotalMedicamentoMaterial;
    @MaxLength(value = 2, type = 'z')
    private Integer tipoPrestador;
    @MaxLength(value = 4, type = 'z')
    private Integer periodo;
    @MaxLength(value = 5, type = 'z')
    private Integer numeroSeqNota;
    @MaxLength(value = 28, type = 's')
    private String filler = "";

    public Integer getZeros() {
        return zeros;
    }

    public void setZeros(Integer zeros) {
        this.zeros = zeros;
    }

    public Integer getTipoNota() {
        return tipoNota;
    }

    public void setTipoNota(Integer tipoNota) {
        this.tipoNota = tipoNota;
    }

    public Integer getQtdLancamento() {
        return qtdLancamento;
    }

    public void setQtdLancamento(Integer qtdLancamento) {
        this.qtdLancamento = qtdLancamento;
    }

    public Long getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(Long cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }

    public BigDecimal getValorTotalNota() {
        return valorTotalNota;
    }

    public void setValorTotalNota(BigDecimal valorTotalNota) {
        this.valorTotalNota = valorTotalNota;
    }

    public BigDecimal getValorTotalMedicamentoMaterial() {
        return valorTotalMedicamentoMaterial;
    }

    public void setValorTotalMedicamentoMaterial(BigDecimal valorTotalMedicamentoMaterial) {
        this.valorTotalMedicamentoMaterial = valorTotalMedicamentoMaterial;
    }

    public Integer getTipoPrestador() {
        return tipoPrestador;
    }

    public void setTipoPrestador(Integer tipoPrestador) {
        this.tipoPrestador = tipoPrestador;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public Integer getNumeroSeqNota() {
        return numeroSeqNota;
    }

    public void setNumeroSeqNota(Integer numeroSeqNota) {
        this.numeroSeqNota = numeroSeqNota;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }
}
