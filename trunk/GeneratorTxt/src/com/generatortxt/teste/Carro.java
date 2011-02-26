/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.generatortxt.teste;

import com.generatortxt.annotation.MaxLength;

/**
 *
 * @author boskyn9
 */
public class Carro {

    @MaxLength(type='s', value=10)
    private String marca = "Chev";
    @MaxLength(type='s', value=20)
    private String modelo = "astra";

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    
}
