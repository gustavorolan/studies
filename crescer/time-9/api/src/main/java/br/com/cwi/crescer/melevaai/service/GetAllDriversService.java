package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.response.GetAllDriversResponse;
import br.com.cwi.crescer.melevaai.mapper.GetAllDriversMapper;
import br.com.cwi.crescer.melevaai.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllDriversService {
    @Autowired
    public DriverRepository driverRepository;
    @Autowired
    private GetAllDriversMapper getAllDriversMapper;

    public List<GetAllDriversResponse> get() {
        return driverRepository.findAll().stream()
                .map(Driver-> getAllDriversMapper.toResponse(Driver))
                .collect(Collectors.toList());
    }
}
