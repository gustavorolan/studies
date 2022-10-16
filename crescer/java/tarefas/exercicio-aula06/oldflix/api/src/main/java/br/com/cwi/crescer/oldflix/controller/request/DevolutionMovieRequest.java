package br.com.cwi.crescer.oldflix.controller.request;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DevolutionMovieRequest {
    @NotEmpty
    private String rentResponsible;
}
