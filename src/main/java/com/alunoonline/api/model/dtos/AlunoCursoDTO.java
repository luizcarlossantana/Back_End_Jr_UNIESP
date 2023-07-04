package com.alunoonline.api.model.dtos;

import com.alunoonline.api.model.Aluno;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data

public class AlunoCursoDTO {

    private String nome;
    private String curso;


    public AlunoCursoDTO( @NotNull Aluno aluno){
        this.nome = aluno.getNome();
        this.curso = aluno.getCurso();
    }
}
