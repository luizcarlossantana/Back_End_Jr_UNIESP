package com.alunoonline.api.controller;

import com.alunoonline.api.model.Aluno;
import com.alunoonline.api.model.dtos.AlunoCursoDTO;
import com.alunoonline.api.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    AlunoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Aluno> create(@RequestBody @Valid Aluno aluno){
        Aluno alunoCreated = service.create(aluno);

        return ResponseEntity.status(201).body(alunoCreated);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Aluno> findAll() {

        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Aluno> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Aluno>> buscarPorNomeIgual(@PathVariable String nome) {
        return ResponseEntity.ok(service.buscarPorNomeIgual(nome));
    }

    @GetMapping("/nome/like/{nome}")
    public ResponseEntity<List<Aluno>> buscarPorNomeLike(@PathVariable String nome) {
        return ResponseEntity.ok(service.buscarPorNomeLike(nome));
    }

    @GetMapping("/lista-paginada")
    public ResponseEntity<Page<Aluno>> listaAlunosPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok(service.listarAlunoPaginado(page,size));

    }

@GetMapping("/curso-nome/{id}")
    public ResponseEntity<AlunoCursoDTO>obterNomeCursoAluno(@PathVariable Long id){
        AlunoCursoDTO alunoDTO = new AlunoCursoDTO(service.findById(id).get());
                return ResponseEntity.ok(alunoDTO);
    }
}
