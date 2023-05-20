package com.jjrockin.spring.dive.api.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {

    private Integer status;
    private OffsetDateTime dateTime;
    private String title;
    private List<FieldToBeListed> fieldsToBeListed;
    @Getter
    @AllArgsConstructor
    public static class FieldToBeListed {
        private String name;
        private String message;
    }
}
