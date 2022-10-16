package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.controller.response.UserWithPostsResponse;
import br.com.cwi.crescer.api.mapper.UserResponseMapper;
import br.com.cwi.crescer.api.model.LikePost;
import br.com.cwi.crescer.api.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllLikesFromAPostService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private UserResponseMapper userResponseMapper;
    public List<UserWithPostsResponse> get(Long id) {
      List<LikePost>likePostList= likeRepository.filterAllLikesOfPostId(id);
      List<UserWithPostsResponse> userAccounts = likePostList.stream()
              .map(likePost -> userResponseMapper.toResponseWithPosts(likePost.getUserAccount()))
              .collect(Collectors.toList());

      return userAccounts;
    }
}
