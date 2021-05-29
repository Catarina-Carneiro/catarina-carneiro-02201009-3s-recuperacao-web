package com.example.recuperacao3.dominio;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class Recarga {

    @NotNull
    @Positive
    private Integer idRecarga;

    @NotNull
    @Positive
    private Integer idBuRecarregado;

    public Integer getIdRecarga() {
        return idRecarga;
    }

    public void setIdRecarga(Integer idRecarga) {
        this.idRecarga = idRecarga;
    }

    public Integer getIdBuRecarregado() {
        return idBuRecarregado;
    }

    public void setIdBuRecarregado(Integer idBuRecarregado) {
        this.idBuRecarregado = idBuRecarregado;
    }

}