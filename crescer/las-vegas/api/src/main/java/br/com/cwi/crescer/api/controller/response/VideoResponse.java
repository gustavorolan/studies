package br.com.cwi.crescer.api.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VideoResponse {
    private Long id;

    private String name;

    private String description;

    private String vimeoResponse;

    private String link;

    private Integer score;

    private byte[] supportMaterial;
}
