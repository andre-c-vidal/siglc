package org.example.siglc.model.dto;

public record FuncionarioDTO(
        String nome,
        String cargo
) {
    public FuncionarioDTO {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome invalido");
        if (cargo == null || cargo.isBlank()) throw new IllegalArgumentException("Cargo invalido");
    }
}
