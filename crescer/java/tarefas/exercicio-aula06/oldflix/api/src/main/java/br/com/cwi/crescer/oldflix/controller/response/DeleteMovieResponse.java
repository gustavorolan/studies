package br.com.cwi.crescer.oldflix.controller.response;

import lombok.*;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DeleteMovieResponse {
    private Long id;
}
