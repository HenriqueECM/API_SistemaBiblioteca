package com.example.biblioteca.mapper;

import com.example.biblioteca.dto.emprestimo.CriacaoEmprestimoRequisicaoDto;
import com.example.biblioteca.dto.emprestimo.CriacaoEmprestimoRespostaDto;
import com.example.biblioteca.model.Emprestimo;
import org.springframework.stereotype.Component;

@Component
public class EmprestimoMapper {
    public Emprestimo paraEntidade(CriacaoEmprestimoRequisicaoDto requisicaoDto){
        return new Emprestimo(
                requisicaoDto.livroId(),
                requisicaoDto.usuarioId(),
                requisicaoDto.dataEmprestimo(),
                requisicaoDto.dataDevolucao());
    }

    public Emprestimo verificarEmprestimo(CriacaoEmprestimoRequisicaoDto requisicaoDto, Emprestimo emprestimo){
        if ((requisicaoDto.livroId() != emprestimo.getLivroId()) && requisicaoDto.livroId() != 0){
            emprestimo.setLivroId(requisicaoDto.livroId());
        }

        if ((requisicaoDto.usuarioId() != emprestimo.getUsuarioId()) && requisicaoDto.usuarioId() != 0){
            emprestimo.setUsuarioId(requisicaoDto.usuarioId());
        }

        if ((requisicaoDto.dataEmprestimo() != emprestimo.getDataEmprestimo()) && requisicaoDto.dataEmprestimo() != null){
            emprestimo.setDataEmprestimo(requisicaoDto.dataEmprestimo());
        }

        if ((requisicaoDto.dataDevolucao() != emprestimo.getDataDevolucao()) && requisicaoDto.dataDevolucao() != null){
            emprestimo.setDataDevolucao(requisicaoDto.dataDevolucao());
        }

        return emprestimo;
    }

    public CriacaoEmprestimoRespostaDto paraResposta(Emprestimo emprestimo){
        return new CriacaoEmprestimoRespostaDto(
                emprestimo.getId(),
                emprestimo.getLivroId(),
                emprestimo.getUsuarioId(),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucao());
    }
}