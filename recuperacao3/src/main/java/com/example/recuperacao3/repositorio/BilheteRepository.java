package com.example.recuperacao3.repositorio;

import com.example.recuperacao3.dominio.BilheteUnico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BilheteRepository extends JpaRepository<BilheteUnico, Integer>{


    List<BilheteUnico> findAllByCpfEquals(String cpf);



   // List<BilheteUnico> findBySaldoLessThanEquals(double saldo);

   // int countBySaldoGreaterThan(double saldo);



}
