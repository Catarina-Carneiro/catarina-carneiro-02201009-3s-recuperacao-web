package com.example.recuperacao3.repositorio;

import com.example.recuperacao3.dominio.BilheteUnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BilheteRepository extends JpaRepository<BilheteUnico, Integer>{


}
