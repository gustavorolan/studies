package br.com.cwi.crescer.api.controller.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestFriendshipRequest {
    @NotNull
    private Long friendId;
}
