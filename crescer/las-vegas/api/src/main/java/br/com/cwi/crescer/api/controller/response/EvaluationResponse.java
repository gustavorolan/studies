package br.com.cwi.crescer.api.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EvaluationResponse {
    private Long id;

    private Integer score;

    private String comment;

    private LocalDateTime dateTimeCreation;

    private LocalDateTime dateTimeUpdate;

    private VideoResponse video;

    private UserResponse userAccount;
}
