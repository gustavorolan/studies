package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.response.PassengerResponse;
import br.com.cwi.crescer.melevaai.mapper.GetAllPassengersMapper;
import br.com.cwi.crescer.melevaai.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllPassengersService {

    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private GetAllPassengersMapper getAllPassengersMapper;

    public List<PassengerResponse> get() {
        return passengerRepository.findAll().stream()
                .map(passenger-> getAllPassengersMapper.toResponse(passenger))
                .collect(Collectors.toList());
    }
}
