package org.serratec.backend.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private Integer status;
    private String message;
    private LocalDateTime timestamp;
    private List<String> errors;
}
