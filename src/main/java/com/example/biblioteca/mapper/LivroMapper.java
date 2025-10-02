package com.example.biblioteca.mapper;

import com.example.biblioteca.dto.livro.CriacaoLivroRequisicaoDto;
import com.example.biblioteca.dto.livro.CriacaoLivroRespostaDto;
import com.example.biblioteca.model.Livro;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {
    public Livro paraEntidade(CriacaoLivroRequisicaoDto requisicaoDto){
        return new Livro(requisicaoDto.titulo(), requisicaoDto.autor(), requisicaoDto.anoPublicacao());
    }

    public CriacaoLivroRespostaDto paraResposta(Livro livro){
        return new CriacaoLivroRespostaDto(livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getAnoPublicacao());
    }

    public Livro verificacaoUpdate(CriacaoLivroRequisicaoDto requisicaoDto, Livro livro){
        if ((requisicaoDto.titulo() != livro.getTitulo()) && requisicaoDto.titulo() != null){
            livro.setTitulo(requisicaoDto.titulo());
        }

        if ((requisicaoDto.autor() != livro.getAutor()) && requisicaoDto.autor() != null) {
            livro.setAutor(requisicaoDto.autor());
        }

        if ((requisicaoDto.anoPublicacao() != livro.getAnoPublicacao()) && requisicaoDto.anoPublicacao() != 0){
            livro.setAnoPublicacao(requisicaoDto.anoPublicacao());
        }

        return livro;
    }
}
