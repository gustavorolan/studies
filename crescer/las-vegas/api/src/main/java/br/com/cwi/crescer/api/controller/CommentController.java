package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.request.CommentRequest;
import br.com.cwi.crescer.api.controller.response.CommentResponse;
import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private ChangeCommentStatusService changeCommentStatusService;

    @Autowired
    private IncludeCommentInDoubtService includeCommentInDoubtService;

    @Autowired
    private ChangeCommentActiveStatusService changeCommentActiveStatusService;

    @Autowired
    private GetAllByDoubtService getAllByDoubtService;

    @GetMapping("/findAllByDoubt/{doubtId}")
    public Page<CommentResponse> getAllByDoubt(Pageable pageable, @PathVariable Long doubtId) {
        return getAllByDoubtService.getAllByDoubt(pageable, doubtId);
    }

    @PostMapping("/include/{doubtId}/{imageId}")
    public ResponseMessage includeCommentInDoubt(@Valid @RequestBody CommentRequest request, @PathVariable Long doubtId, @PathVariable String imageId) {
        return includeCommentInDoubtService.includeCommentInDoubt(request, doubtId, imageId);
    }

    @PostMapping("/changeCommentStatus/{commentId}/{doubtId}")
    public CommentResponse changeCommentStatus(@PathVariable Long commentId, @PathVariable Long doubtId) {
        return changeCommentStatusService.changeCommentStatus(commentId, doubtId);
    }

    @PostMapping("/changeActiveStatus/{commentId}")
    public ResponseMessage changeCommentActiveStatus(@PathVariable Long commentId) {
        return changeCommentActiveStatusService.changeCommentActiveStatus(commentId);
    }

}