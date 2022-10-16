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
public class RegisterUserRequest {
    @NotBlank(message = "Full name is mandatory")
    @Size(min = 2, max = 255, message = "Characters limit between 2 and 255")
    private String fullName;

    @NotBlank(message = "Email is mandatory")
    @Size(min = 2, max = 255, message = "Characters limit between 2 and 255")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 20, message = "Characters limit between 2 and 20")
    private String password;

    @Size(min = 8, max = 350, message = "Characters limit between 8 and 350")
    private String presentation;

    @Size(min = 8, max = 500, message = "Characters limit between 8 and 500")
    private String relevantLinks;
}