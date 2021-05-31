package com.example.recuperacao3.dominio;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class BilheteUnico {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    @Size(min=5)
    private String passageiro;

    @Past
    private LocalDate data;

    @CPF
    private String cpf;

    @NotNull
    @PositiveOrZero
    private Double saldo;

    private Double valorRecarga = 0.0;


    @ManyToOne
    private TipoPassagem tipoPassagem;

    public boolean isPositivo() {
        return saldo > 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(String passageiro) {
        this.passageiro = passageiro;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public TipoPassagem getTipoPassagem() {
        return tipoPassagem;
    }

    public void setTipoPassagem(TipoPassagem tipoPassagem) {
        this.tipoPassagem = tipoPassagem;
    }

    public Double getValorRecarga() {
        return valorRecarga;
    }

    public void setValorRecarga(Double valorRecarga) {
        this.valorRecarga = valorRecarga;
    }

}
