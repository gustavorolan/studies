package br.com.cwi.crescer.api.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CourseResponse {
    private Long id;

    private String name;

    private String description;

    private Boolean active;

    private AttachmentResponseData image;

    private Boolean approvementStatus;

    private Long authorId;

    private Integer assessment;
}
