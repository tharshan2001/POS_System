package com.pos.system.controller.lookup;

import com.pos.system.entity.Lookup.Status;
import com.pos.system.repository.lookup.StatusRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statuses")
public class StatusController {

    private final StatusRepository statusRepository;

    public StatusController(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @GetMapping
    public List<Status> getAll() {
        return statusRepository.findAll();
    }

    @GetMapping("/{id}")
    public Status getById(@PathVariable Long id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status not found"));
    }
}
