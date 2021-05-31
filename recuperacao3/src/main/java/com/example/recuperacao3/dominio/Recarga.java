package com.example.recuperacao3.dominio;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class Recarga {

    @NotNull
    @Positive
    private Integer idNovaRecarga;

    @NotNull
    @Positive
    private Integer idPassouCatraca;

    public Integer getIdNovaRecarga() {
        return idNovaRecarga;
    }

    public void setIdNovaRecarga(Integer idNovaRecarga) {
        this.idNovaRecarga = idNovaRecarga;
    }

    public Integer getIdPassouCatraca() {
        return idPassouCatraca;
    }

    public void setIdPassouCatraca(Integer idPassouCatraca) {
        this.idPassouCatraca = idPassouCatraca;
    }
}