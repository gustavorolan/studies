package br.com.cwi.crescer.api.controller.response;

import lombok.*;

@Builder
@Data
public class UserAccountResponse {

    private Long userId;

    private String userName;

    private String nickName;

    private String profileImg;

    private String email;

}
