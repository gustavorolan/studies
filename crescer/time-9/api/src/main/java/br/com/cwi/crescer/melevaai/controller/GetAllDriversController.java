package br.com.cwi.crescer.melevaai.controller;


import br.com.cwi.crescer.melevaai.controller.response.GetAllDriversResponse;
import br.com.cwi.crescer.melevaai.controller.response.PassengerResponse;
import br.com.cwi.crescer.melevaai.service.GetAllDriversService;
import br.com.cwi.crescer.melevaai.service.GetAllPassengersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/meLevaAi/motoristas")
public class GetAllDriversController {

    @Autowired
    private GetAllDriversService getAllDriversService;

    @GetMapping
    public List<GetAllDriversResponse> getAllDrivers () {
        return getAllDriversService.get();
    }

}
