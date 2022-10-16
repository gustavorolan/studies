package br.com.cwi.crescer.api.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CourseCompleteResponse {
    private Long id;

    private String name;

    private String description;

    private Boolean active;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dateTimeCreation;

    private LocalDateTime dateTimeUpdate;

    private Boolean approvementStatus;

    private AttachmentResponseData image;

    private Long authorId;

    private String authorName;

    private List<VideoResponse> videos;

    private Integer assessment;
}
