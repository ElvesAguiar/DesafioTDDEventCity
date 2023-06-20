package com.devsuperior.demo.cotrollers;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cities")
public class CityController {

    @Autowired
    CityService service;

    @RequestMapping
    public ResponseEntity<List<CityDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
}
