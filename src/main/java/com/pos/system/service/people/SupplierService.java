package com.pos.system.service.people;


import com.pos.system.entity.people.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierService {

    Supplier createSupplier(Supplier supplier);

    Supplier updateSupplier(Long id, Supplier supplier);

    void deleteSupplier(Long id);

    Optional<Supplier> getSupplierById(Long id);

    List<Supplier> getAllSuppliers();
}
