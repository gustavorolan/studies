package br.com.cwi.crescer.api.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TeamResponse {
    private Long id;

    private String name;

    private String description;

    private AttachmentResponseData image;

    private Boolean active;

    private Long creatorId;
}
