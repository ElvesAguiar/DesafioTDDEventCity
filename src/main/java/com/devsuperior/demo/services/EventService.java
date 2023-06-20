package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    EventRepository repository;

    @Transactional(readOnly = true)
    public List<EventDTO> findAll() {

        List<Event> result = repository.findAll();

        return result.stream().map(EventDTO::new).toList();
    }

    @Transactional
    public EventDTO insert(EventDTO dto) {

        Event entity = new Event();
        entityToDTO(entity,dto);

        repository.save(entity);

        return new EventDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        try {
            Optional<Event> entity = repository.findById(id);
            if(entity.isEmpty()) throw new EntityNotFoundException();
            repository.delete(entity.get());
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        }

    }

    @Transactional
    public EventDTO update(Long id ,EventDTO dto) {

        Event entity = repository.getReferenceById(id);
        entityToDTO(entity,dto);
        repository.save(entity);

        return new EventDTO(entity);
    }

    private void entityToDTO(Event entity, EventDTO dto){
        entity.setName(dto.getName());
        entity.setDate(dto.getDate());
        entity.setUrl(dto.getUrl());
        City city = new City();
        city.setId(dto.getCityId());
        entity.setCity(city);
    }
}
