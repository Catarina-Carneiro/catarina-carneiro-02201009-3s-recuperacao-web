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


    //A) VALIDACAO DO CPF 2X
    @PostMapping("/bilhete-unico")
    public ResponseEntity postBilhetes(@RequestBody @Valid BilheteUnico novoBU) {

        repository.save(novoBU);
        return ResponseEntity.status(201).build();

        //return ResponseEntity.status(400).body("Tipo de esporte não encontrado!");

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


    //C)SÓ PODE TER UMA DESC
    @PostMapping("/tipo-passagem")
    public ResponseEntity postTipo(@RequestBody TipoPassagem novoTipo) {

        TipoRepository.save(novoTipo);
        return ResponseEntity.status(201).build();
    }

    //D ERRADO +/-
    @PostMapping("/bilhete-unico/{id}/recarga/{valorRecarga}")
    public ResponseEntity postRecarga(@PathVariable Integer id){

        Optional<BilheteUnico> bilheteUnicoOptional = repository.findById(id);
        if (!bilheteUnicoOptional.isPresent()) {
            return status(404).build();
        } else {
            BilheteUnico bilhete = bilheteUnicoOptional.get();

            if (bilhete.getSaldo() > 1){

                bilhete.setSaldo(bilhete.getSaldo());
                return status(201).build();

            }else if(bilhete.getSaldo() < 1){

                return status(400).body("Valor da recarga deve ser a partir de R$1,00");

            }else{

                return status(400).body("id n econtrado");
            }

        }
    }


    //E
    @PostMapping("/bilhete-unico/{id}/passagem/{idTipo}")
    public ResponseEntity postGolpeLuta(@RequestBody @Valid Recarga recarga) {

        if (!repository.existsById(recarga.)
                || !repository.existsById(golpe.getIdLutadorApanha())) {
            return status(404).build();
        }

        Lutador lutadorBate = repository.findById(golpe.getIdLutadorBate()).get();
        Lutador lutadorApanha = repository.findById(golpe.getIdLutadorApanha()).get();

        if (!lutadorApanha.isVivo() || !lutadorBate.isVivo()) {
            return status(400).body("Ambos os lutadores devem estar vivos!");
        }

        lutadorApanha.setVida(lutadorApanha.getVida() - lutadorBate.getForcaGolpe());
        if (lutadorApanha.getVida() < 0) {
            lutadorApanha.setVida(0);
        }

        // ou troca as 3 linhas de cima por: lutadorApanha.apanharDe(lutadorBate);

        repository.save(lutadorApanha);

        List<Lutador> lutadores = new ArrayList<>();
        lutadores.add(lutadorApanha);
        lutadores.add(lutadorBate);
        return status(201).body(lutadores);

    }


}
