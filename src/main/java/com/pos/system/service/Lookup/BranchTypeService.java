package com.pos.system.service.Lookup;

import com.pos.system.entity.Lookup.BranchType;

import java.util.List;

public interface BranchTypeService {

    BranchType create(String name);

    BranchType createIfNotExists(String name);

    BranchType getById(Long id);

    BranchType getByName(String name);

    List<BranchType> getAll();
}