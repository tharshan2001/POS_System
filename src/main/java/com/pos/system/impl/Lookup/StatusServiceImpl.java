package com.pos.system.impl.Lookup;


import com.pos.system.entity.Lookup.Status;
import com.pos.system.repository.lookup.StatusRepository;
import com.pos.system.service.Lookup.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;
    @Override
    public Status createIfNotExists(String name) {
        return statusRepository.findByName(name)
                .orElseGet(() -> statusRepository.save(new Status(name)));
    }

}