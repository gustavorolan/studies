package br.com.cwi.crescer.api.controller.request;

import lombok.Data;

@Data
public class ChangeUserRequest {
    private String userName;

    private String nickName;

    private String profileImg;
}
