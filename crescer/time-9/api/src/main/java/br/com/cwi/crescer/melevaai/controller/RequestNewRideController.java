package br.com.cwi.crescer.melevaai.controller;




import br.com.cwi.crescer.melevaai.controller.request.RequestNewRideRequest;
import br.com.cwi.crescer.melevaai.controller.response.RequestNewRideResponse;
import br.com.cwi.crescer.melevaai.service.RequestNewRideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/meLevaAi")
public class RequestNewRideController {

    @Autowired
    private RequestNewRideService requestNewRideService;

    @PostMapping("/criarCorrida")
    public RequestNewRideResponse requestNewRide(@Valid @RequestBody RequestNewRideRequest request) {
        return requestNewRideService.createRide(request);
    }

}
