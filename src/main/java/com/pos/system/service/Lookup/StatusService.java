package com.pos.system.service.Lookup;

import com.pos.system.entity.Lookup.Status;

public interface StatusService {
    Status createIfNotExists(String name);
}
