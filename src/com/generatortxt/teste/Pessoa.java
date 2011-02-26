/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.generatortxt.teste;

import com.generatortxt.annotation.DateFormat;
import com.generatortxt.annotation.MaxLength;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author boskyn9
 */
public class Pessoa {

    
    @MaxLength(value=5, type='z')
    private Integer idade;// = 10;
    private Float altura;// = 1.78f;
    @MaxLength(value=20, type='s')
    private String nome;// = "Bosco";

    @DateFormat(format="dd-MM-yyyy")
    private Date nasc;// = new Date();

    private List<Carro> carros;

    private Set<Pessoa> irmaos;

    public Float getAltura() {
        return altura;
    }

    public void setAltura(Float altura) {
        this.altura = altura;
    }

    public List<Carro> getCarros() {
        return carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Pessoa> getIrmaos() {
        return irmaos;
    }

    public void setIrmaos(Set<Pessoa> irmaos) {
        this.irmaos = irmaos;
    }

    public Date getNasc() {
        return nasc;
    }

    public void setNasc(Date nasc) {
        this.nasc = nasc;
    }

}
