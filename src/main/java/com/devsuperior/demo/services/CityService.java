package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    CityRepository repository;

    public List<CityDTO> findAll(){

        List<City> result = repository.findAll()
                .stream()
                .sorted((a, b)-> a.getName().compareTo(b.getName()))
                .toList();

        return result.stream().map(CityDTO::new).toList();
    }
}
