package com.example.biblioteca.dto.emprestimo;

import java.time.LocalDate;

public record CriacaoEmprestimoRequisicaoDto(int livroId, int usuarioId, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
}
