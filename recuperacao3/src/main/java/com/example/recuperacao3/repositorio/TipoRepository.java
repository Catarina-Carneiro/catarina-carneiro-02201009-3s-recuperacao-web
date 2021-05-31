package com.example.recuperacao3.repositorio;

import com.example.recuperacao3.dominio.BilheteUnico;
import com.example.recuperacao3.dominio.TipoPassagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoRepository extends JpaRepository<TipoPassagem, String> {

    List<TipoPassagem> findAllByDescricaoEquals(String descricao);

}
