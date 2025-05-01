package projeto.faculdade.dto;

import java.time.LocalDate;

public record RegisterAlunoDTO(String nome, String matricula, LocalDate dataNascimento) {
    
}
