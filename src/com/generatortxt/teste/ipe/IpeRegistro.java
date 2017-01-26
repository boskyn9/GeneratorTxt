package com.generatortxt.teste.ipe;

import java.util.ArrayList;
import java.util.List;

public class IpeRegistro {

    private HeaderIpe35 header;
    private List<Designativo> designativo = new ArrayList<Designativo>();

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

    public HeaderIpe35 getHeader() {
        return header;
    }

    public void setHeader(HeaderIpe35 header) {
        this.header = header;
    }

    public List<Designativo> getDesignativo() {
        return designativo;
    }

    public void setDesignativo(List<Designativo> designativo) {
        this.designativo = designativo;
    }
}
