package com.example.biblioteca.mapper;

import com.example.biblioteca.dto.usuario.CriacaoUsuarioRespostaDto;
import com.example.biblioteca.dto.usuario.CriacaorUsuarioRequisicaoDto;
import com.example.biblioteca.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public Usuario paraEntidade(CriacaorUsuarioRequisicaoDto requisicaoDto){
        return new Usuario(requisicaoDto.nome(), requisicaoDto.email());
    }

    public Usuario verificacaoUpdate(CriacaorUsuarioRequisicaoDto requisicaoDto, Usuario usuario){

        if ((requisicaoDto.nome() != usuario.getNome()) && requisicaoDto.nome() != null){
            usuario.setNome(requisicaoDto.nome());
        }

        if((requisicaoDto.email() != usuario.getEmail()) && requisicaoDto.email() != null){
            usuario.setEmail(requisicaoDto.email());
        }

        return usuario;
    }

    public CriacaoUsuarioRespostaDto paraResposta(Usuario usuario){
        return new CriacaoUsuarioRespostaDto(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
