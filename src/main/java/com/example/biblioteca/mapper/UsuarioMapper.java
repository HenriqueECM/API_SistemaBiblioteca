package com.example.biblioteca.mapper;

import com.example.biblioteca.dto.usuario.CriacaoUsuarioRespostaDto;
import com.example.biblioteca.dto.usuario.CriacaorUsuarioRequisicaoDto;
import com.example.biblioteca.model.Usuario;

public class UsuarioMapper {
    public Usuario paraEntidade(CriacaorUsuarioRequisicaoDto requisicaoDto){
        return new Usuario(requisicaoDto.nome(), requisicaoDto.email());
    }

    public CriacaoUsuarioRespostaDto paraResposta(Usuario usuario){
        return new CriacaoUsuarioRespostaDto(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
