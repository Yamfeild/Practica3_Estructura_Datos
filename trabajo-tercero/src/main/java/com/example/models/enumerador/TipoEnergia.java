package com.example.models.enumerador;

public enum TipoEnergia {
    ENERGIA_SOLAR("ENERGIA_SOLAR"), ENERGIA_EOLICA("ENERGIA_EOLICA"), ENERGIA_HIDRAULICA("ENERGIA_HIDRAULICA");
    private String name;

    private TipoEnergia(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
