package br.com.cwi.crescer.api.controller.request;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateNewPostRequest {

    @NotEmpty
    private String postText;

    @NotNull
    private boolean privatePost;

    private String PostImg;
}
