package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.repositories.CityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    CityRepository repository;

    public List<CityDTO> findAll() {

        List<City> result = repository.findAll()
                .stream()
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .toList();

        return result.stream().map(CityDTO::new).toList();
    }

    public CityDTO insert(CityDTO dto) {

        City entity = new City();
        entity.setName(dto.getName());
        repository.save(entity);

        return new CityDTO(entity);
    }

    public void delete(Long id) {
        try {
            Optional<City> entity = repository.findById(id);
            if(entity.isEmpty()) throw new EntityNotFoundException();
            repository.delete(entity.get());
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        }

    }
}
