package br.com.cwi.crescer.api.controller.response;

import br.com.cwi.crescer.api.model.Permission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RegisteredCoursesResponse {
    private Long id;
    private String email;
    private String fullName;
    private Integer assessment;
    private Permission permission;
    private String presentation;
    private String relevantLinks;
    private String imageId;
    private List<CourseResponse> registeredCoursesResponseList;
}