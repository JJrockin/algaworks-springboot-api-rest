package com.jjrockin.spring.dive.api.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Problem {

    private Integer status;
    private LocalDateTime dataHora;
    private String titulo;
    private List<Campo> campos;
    @Getter
    @AllArgsConstructor
    public static class Campo {
        private String nome;
        private String message;
    }
}
