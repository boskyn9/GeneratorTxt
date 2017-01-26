package com.generatortxt.teste.ipe;

import com.generatortxt.annotation.MaxLength;

/**
 * Created by bosco on 03/01/2017.
 */
public class HeaderIpe35 {

    @MaxLength(value = 3, type = 's')
    private String nomeDoSitema = "SMH";
    @MaxLength(value = 14, type = 'z')
    private Long cpfCnpjPrestador;
    @MaxLength(value = 4, type = 'z')
    private Integer qtdNotas;
    @MaxLength(value = 5, type = 'z')
    private Integer qtdLancamentosReferencia;
    @MaxLength(value = 8, type = 'z')
    private Integer numeroPrestador;
    @MaxLength(value = 45, type = 's')
    private String nomePrestador;
    @MaxLength(value = 2, type = 's')
    private String filler = " ";

    public String getNomeDoSitema() {
        return nomeDoSitema;
    }

    public Long getCpfCnpjPrestador() {
        return cpfCnpjPrestador;
    }

    public void setCpfCnpjPrestador(Long cpfCnpjPrestador) {
        this.cpfCnpjPrestador = cpfCnpjPrestador;
    }

    public Integer getQtdNotas() {
        return qtdNotas;
    }

    public void setQtdNotas(Integer qtdNotas) {
        this.qtdNotas = qtdNotas;
    }

    public Integer getQtdLancamentosReferencia() {
        return qtdLancamentosReferencia;
    }

    public void setQtdLancamentosReferencia(Integer qtdLancamentosReferencia) {
        this.qtdLancamentosReferencia = qtdLancamentosReferencia;
    }

    public Integer getNumeroPrestador() {
        return numeroPrestador;
    }

    public void setNumeroPrestador(Integer numeroPrestador) {
        this.numeroPrestador = numeroPrestador;
    }

    public String getNomePrestador() {
        return nomePrestador;
    }

    public void setNomePrestador(String nomePrestador) {
        this.nomePrestador = nomePrestador;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    
}
