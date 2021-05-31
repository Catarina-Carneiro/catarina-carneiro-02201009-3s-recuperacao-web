package com.example.recuperacao3.controle;


import com.example.recuperacao3.dominio.BilheteUnico;
import com.example.recuperacao3.dominio.TipoPassagem;
import com.example.recuperacao3.repositorio.BilheteRepository;
import com.example.recuperacao3.repositorio.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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



    @GetMapping("/bilhete-unico/{id}")
    public ResponseEntity getBilheres() {

        List<BilheteUnico> bilheteUnicos = repository.findAll();

        if (bilheteUnicos.isEmpty()) {
            return status(204).build();
        } else {
            return status(200).body(bilheteUnicos);
        }
    }



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

    
    @PostMapping("/bilhete-unico/{id}/recarga/{valorRecarga}")
    public ResponseEntity postRecarga(@PathVariable Integer id, Double valorRecarga) {

        Optional<BilheteUnico> bilheteUnicoOptional = repository.findById(id);

        BilheteUnico bilhete = bilheteUnicoOptional.get();

        if (bilhete.getValorRecarga() < 230) {

            if (bilhete.getSaldo() + bilhete.getValorRecarga() < 230){

                bilhete.setSaldo(bilhete.getSaldo() + bilhete.getValorRecarga());
                return status(201).build();
            }else {
                double valor = bilhete.getSaldo() -230;
                return status(400).body("Recarga não efetuada! Passaria do limite de "+ bilhete.getSaldo()+
                        "Você ainda pode carregar até"+ valor);
            }
        } else if (bilhete.getValorRecarga() < 1) {

            return status(400).body("Valor da recarga deve ser a partir de R$1,00");
        } else {

            return status(400).body("id n econtrado");
        }

    }

}