package com.ifsp.apifilmes.dto;

import java.time.LocalDate;
import java.util.List;

public record FilmeRequestDTO(
        String titulo,
        String sinopse,
        LocalDate dataLancamento,
        Integer generoId,
        Integer diretorId,
        List<Integer> atoresIds
) {
}
