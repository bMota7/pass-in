package bruno.com.passin.controllers;

import bruno.com.passin.dto.event.EventIdDTO;
import bruno.com.passin.dto.event.EventRequestDTO;
import bruno.com.passin.dto.event.EventResponseDTO;
import bruno.com.passin.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService service;
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id) {
        EventResponseDTO event = this.service.getEventDetail(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder) {
       EventIdDTO eventIdDTO = this.service.createEvent(body);

       var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();

       return ResponseEntity.created(uri).body(eventIdDTO);
    }
}
