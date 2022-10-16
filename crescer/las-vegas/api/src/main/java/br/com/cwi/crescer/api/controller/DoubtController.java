package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.controller.request.DoubtRequest;
import br.com.cwi.crescer.api.controller.response.DoubtResponse;
import br.com.cwi.crescer.api.controller.response.ResponseMessage;
import br.com.cwi.crescer.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/doubts")
@CrossOrigin(origins = "http://localhost:3000/")
public class DoubtController {

    @Autowired
    private IncludeDoubtService includeDoubtService;

    @Autowired
    private ChangeDoubtStateService changeDoubtStateService;

    @Autowired
    private ChangeDoubtActiveStatusService changeDoubtActiveStatusService;

    @Autowired
    private GetAllDoubtsService getAllDoubtsService;

    @Autowired
    private GetDetailedDoubtService getDetailedDoubtService;

    @PostMapping("/include/{imageId}")
    public ResponseMessage includeDoubt(@Valid @RequestBody DoubtRequest request, @PathVariable String imageId) {
        return includeDoubtService.includeDoubt(request, imageId);
    }

    @PostMapping("/changeState/{doubtId}")
    public ResponseMessage changeDoubtState(@PathVariable Long doubtId) {
        return changeDoubtStateService.changeDoubtState(doubtId);
    }

    @PostMapping("/changeActiveStatus/{doubtId}")
    public ResponseMessage changeDoubtActiveStatus(@PathVariable Long doubtId) {
        return changeDoubtActiveStatusService.changeDoubtActiveStatus(doubtId);
    }

    @GetMapping("/allDoubts")
    public Page<DoubtResponse> getAllDoubts(@RequestParam int page) {
        return getAllDoubtsService.getAllDoubts(page);
    }

    @GetMapping("/doubt/{idDoubt}")
    public DoubtResponse getDetailedDoubt(@PathVariable Long idDoubt){
        return getDetailedDoubtService.getDetailedDoubt(idDoubt);
    }
}
