package br.com.cwi.crescer.api.util;

import br.com.cwi.crescer.api.controller.request.CommentRequest;
import br.com.cwi.crescer.api.controller.response.CommentResponse;
import br.com.cwi.crescer.api.model.Comment;

public class CommentFactory {

    public  static Comment.CommentBuilder getBuilder(){
      return Comment.builder()
              .id(1L)
              .description("desc")
              .isRightResponse(true)
              .active(true)
              .author(UserAccountFactory.get());
    }
    public  static Comment get(){
        return getBuilder().build();
    }


    public  static CommentResponse.CommentResponseBuilder getBuilderCommentResponse(){
        return CommentResponse.builder()
                .id(1L)
                .description("desc")
                .isRightResponse(true)
                .active(true);
    }


    public static CommentResponse getCommentResponse(){
        return getBuilderCommentResponse().build();
    }


    public static CommentRequest getCommentRequest(){
        return CommentRequest.builder()
                .description("desc")
                .build();
    }


}
