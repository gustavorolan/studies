package br.com.cwi.crescer.api.controller.response;

import br.com.cwi.crescer.api.model.Permission;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private Long usuarioId;
    private Permission permission;
}
