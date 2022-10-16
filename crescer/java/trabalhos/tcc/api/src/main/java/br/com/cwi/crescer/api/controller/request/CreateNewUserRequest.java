package br.com.cwi.crescer.api.controller.request;

import br.com.cwi.crescer.api.model.Permission;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class CreateNewUserRequest {
    @NotEmpty
    private String userName;
    @NotEmpty
    private String nickName;
    @NotEmpty
    private String email;
    @NotEmpty
    private String  password;

    private String profileImg;

    private Permission permission;
}
