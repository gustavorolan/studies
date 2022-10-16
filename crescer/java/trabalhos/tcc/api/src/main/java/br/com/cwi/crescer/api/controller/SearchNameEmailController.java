package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.request.SearchNameEmailRequest;
import br.com.cwi.crescer.api.controller.response.UserAccountResponse;
import br.com.cwi.crescer.api.service.SearchNameEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchNameEmailController {
    @Autowired
    private SearchNameEmailService searchNameEmailService;
    @PostMapping
    public List<UserAccountResponse> search (@RequestBody SearchNameEmailRequest request){
        return searchNameEmailService.search(request);
    }
}
