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

@RestController
@RequestMapping("/consultas")
public class BilheteUnicoController {


    @Autowired
    private BilheteRepository repository;

    @Autowired
    private TipoRepository passagemRepository;


    @GetMapping("/bilhete-unico/{id}")
    public ResponseEntity getCartorios() {

        List<BilheteUnico> bilheteUnicos = repository.findAll();
        return ResponseEntity.status(200).body(repository.findAll());
    }



    @PostMapping("/tipo-passagem")
    public ResponseEntity postBu(@RequestBody @Valid BilheteUnico novoBu){

        repository.save(novoBu);
        return ResponseEntity.status(201).build();
    }







}
