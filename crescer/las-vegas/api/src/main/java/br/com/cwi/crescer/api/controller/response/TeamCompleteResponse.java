package br.com.cwi.crescer.api.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TeamCompleteResponse {
    private Long id;

    private String name;

    private String description;

    private Boolean active;

    private AttachmentResponseData image;

    private List<UserResponse> users;

    private List<CourseResponse> courses;

    private Long creatorId;

    private String supportMaterialDescription;
}