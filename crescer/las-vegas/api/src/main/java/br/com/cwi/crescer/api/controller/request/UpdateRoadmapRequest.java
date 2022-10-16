package br.com.cwi.crescer.api.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UpdateRoadmapRequest {
    @Size(min = 2, max = 100, message = "Characters limit between 2 and 100")
    private String name;

    @Size(min = 2, max = 255, message = "Characters limit between 2 and 255")
    private String description;

}
