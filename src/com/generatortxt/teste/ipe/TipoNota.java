package com.generatortxt.teste.ipe;

public enum TipoNota {

    TIPO35(35),
    TIPO55(55),
    TIPO75(75),
    TIPO85(85),
    TIPO76(76),
    TIPO86(86),
    TIPO77(77),
    TIPO87(87);

    TipoNota(int v) {
        value = v;
    }

    public static TipoNota fromValue(String v) {
        for (TipoNota c : TipoNota.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public final Integer getValue() {
        return value;
    }

    private final Integer value;
}
