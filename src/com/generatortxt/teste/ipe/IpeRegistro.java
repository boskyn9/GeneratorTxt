package com.generatortxt.teste.ipe;

import java.util.ArrayList;
import java.util.List;

public class IpeRegistro {

    private Header header;
    private List<Nota> notas = new ArrayList<Nota>();

    /**
     * "SMH" + <código do prestador com 5 dígitos> + "." + <extensão>
     * <p>
     * sendo a extensão determinada conforme a tabela abaixo:
     * 035 - Atendimento Complementar
     * 055 - Pronto Atendimento
     * 075 - Conta Hospitalar
     * 085 - Conta Ambulatorial
     */

    public String getFileName() {
        return "";
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
}
