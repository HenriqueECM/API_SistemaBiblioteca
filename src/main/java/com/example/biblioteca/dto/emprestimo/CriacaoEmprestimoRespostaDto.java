package com.example.biblioteca.dto.emprestimo;

import java.time.LocalDate;

public record CriacaoEmprestimoRespostaDto(int id, int livroId, int usuarioId, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
}
