package com.devsuperior.demo.cotrollers;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/events")
public class EventController {

    @Autowired
    EventService service;

    @RequestMapping
    public ResponseEntity<List<EventDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<EventDTO> insert(@RequestBody EventDTO dto) {

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(service.insert(dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> findAll(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EventDTO> update(@PathVariable Long id, @RequestBody EventDTO dto) {

        return ResponseEntity.ok(service.update(id, dto));
    }


}
