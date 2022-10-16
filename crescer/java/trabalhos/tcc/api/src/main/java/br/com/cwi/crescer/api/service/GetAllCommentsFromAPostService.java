package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.CommentResponse;
import br.com.cwi.crescer.api.mapper.UserResponseMapper;
import br.com.cwi.crescer.api.model.Comment;
import br.com.cwi.crescer.api.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllCommentsFromAPostService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserResponseMapper userResponseMapper;
    public List<CommentResponse> get(Long id) {
      List<Comment> commentList =  commentRepository.findAllPostComments(id);
        System.out.println(commentList);
      List<CommentResponse> commentResponseList = new ArrayList<>();

      commentList.stream().forEach(comment ->{
          CommentResponse commentResponse = CommentResponse.builder()
                  .commentText(comment.getCommentText())
                  .userAccountResponse(userResponseMapper.toResponse(comment.getUserAccount()))
                  .build();
          commentResponseList.add(commentResponse);
      });
      return  commentResponseList;
    }
}
