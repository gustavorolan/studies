package br.com.cwi.crescer.api.controller.request;

import br.com.cwi.crescer.api.model.VideoType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VideoRequest {
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 100, message = "Characters limit between 2 and 100")
    private String name;

    @NotBlank(message = "Link is mandatory")
    @Size(min = 2, max = 400, message = "Characters limit between 2 and 400")
    private String link;

    @NotBlank(message = "Description is mandatory")
    @Size(min = 2, max = 255, message = "Characters limit between 2 and 255")
    private String description;

    @NotNull(message = "Video type is mandatory")
    private VideoType type;
}
