package br.com.cwi.crescer.api.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DoubtRequest {
    @NotBlank(message = "Text is mandatory")
    @Size(min = 10, max = 255, message = "Characters limit between 10 and 255")
    private String description;
}
