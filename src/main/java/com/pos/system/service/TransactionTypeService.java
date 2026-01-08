package com.pos.system.service;

import com.pos.system.entity.Lookup.TransactionType;

public interface TransactionTypeService {
    void createIfNotExists(String name);
}