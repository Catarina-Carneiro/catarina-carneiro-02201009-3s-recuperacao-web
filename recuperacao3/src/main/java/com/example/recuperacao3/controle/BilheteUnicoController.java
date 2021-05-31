package com.example.recuperacao3.controle;


import com.example.recuperacao3.dominio.BilheteUnico;
import com.example.recuperacao3.dominio.Recarga;
import com.example.recuperacao3.dominio.TipoPassagem;
import com.example.recuperacao3.repositorio.BilheteRepository;
import com.example.recuperacao3.repositorio.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/bus")
public class BilheteUnicoController {


    @Autowired
    private BilheteRepository repository;

    @Autowired
    private TipoRepository TipoRepository;


    //A)OK
    @PostMapping("/bilhete-unico")
    public ResponseEntity postBilhetes(@RequestBody @Valid BilheteUnico novoBu) {

        List<BilheteUnico> bu = repository.findAllByCpfEquals(novoBu.getCpf());

        if (bu.size() < 2) {

            repository.save(novoBu);
            return ResponseEntity.status(201).build();

        } else {
            return status(400).body("Você já tem 2 Bilhetes unicos nesse CPF!");
        }

    }


    //B) OK
    @GetMapping("/bilhete-unico/{id}")
    public ResponseEntity getBilheres() {

        List<BilheteUnico> bilheteUnicos = repository.findAll();

        if (bilheteUnicos.isEmpty()) {
            return status(204).build();
        } else {
            return status(200).body(bilheteUnicos);
        }
    }


    //    //C)ok
    @PostMapping("/tipo-passagem")
    public ResponseEntity postTipo(@RequestBody @Valid TipoPassagem novoTipo) {

        List<TipoPassagem> tipo = TipoRepository.findAllByDescricaoEquals(novoTipo.getDescricao());

        if (tipo.size() < 1) {

            TipoRepository.save(novoTipo);
            return ResponseEntity.status(201).build();

        } else {
            return status(400).body("Tipo já cadastrado");
        }

    }


    //D  +/-
    @PostMapping("/bilhete-unico/{id}/recarga/{valorRecarga}")
    public ResponseEntity postRecarga(@PathVariable Integer id) {

        Optional<BilheteUnico> bilheteUnicoOptional = repository.findById(id);

//        if (!bilheteUnicoOptional.isPresent()) {
//            return status(404).build();
//        } else {

        BilheteUnico bilhete = bilheteUnicoOptional.get();
        if (bilhete.getSaldo() > 1) {

            bilhete.setSaldo(bilhete.getSaldo() + bilhete.getValorRecarga());
            return status(201).build();

        } else if (bilhete.getSaldo() < 1) {
            return status(400).body("Valor da recarga deve ser a partir de R$1,00");
        } else {

            return status(400).body("id n econtrado");
        }

    }


//    E
//    @PostMapping("/bilhete-unico/{id}/passagem/{idTipo}")
//    public ResponseEntity postGolpeLuta(@RequestBody @Valid Recarga recarga) {
//
//        if (!repository.existsById(recarga.getIdNovaRecarga())) {
//            return status(404).body("BU não encontrado");
//        }
//        if (!repository.existsById(TipoPassagem.getId())) {
//            return status(404).body("Tipo de passagem não encontrado");
//        }
//
//        BilheteUnico novaRecarga = repository.findById(recarga.getIdNovaRecarga()).get();
//        BilheteUnico passouCatraca = repository.findById(recarga.getIdPassouCatraca().get());
//
//
//        if (!novaRecarga.isPositivo()) {
//
//            return status(400).body("Saldo atual: " + novaRecarga.getSaldo() + "insuficiente para esta passagem");
//        }
//
//        novaRecarga.setSaldo(novaRecarga.getSaldo() - passouCatraca.getValorPassagem);
//
//        repository.save(passouCatraca);
//
//        List<BilheteUnico> bilheteUnicos = new ArrayList<>();
//        bilheteUnicos.add(passouCatraca);
//        return status(201).body(bilheteUnicos);
//
// }


}