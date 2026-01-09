package com.pos.system.impl.people;


import com.pos.system.entity.people.Supplier;
import com.pos.system.repository.people.SupplierRepository;
import com.pos.system.service.people.SupplierService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Supplier createSupplier(Supplier supplier) {
        // Optional: check for duplicates
        if (supplierRepository.existsByEmail(supplier.getEmail())) {
            throw new RuntimeException("Email already exists for another supplier");
        }
        if (supplierRepository.existsByName(supplier.getName())) {
            throw new RuntimeException("Supplier with this name already exists");
        }

        supplier.setCreatedAt(LocalDateTime.now());
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier updateSupplier(Long id, Supplier updatedSupplier) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        supplier.setName(updatedSupplier.getName());
        supplier.setPhone(updatedSupplier.getPhone());
        supplier.setEmail(updatedSupplier.getEmail());
        supplier.setAddress(updatedSupplier.getAddress());
        supplier.setCreditTerms(updatedSupplier.getCreditTerms());

        return supplierRepository.save(supplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        if (!supplierRepository.existsById(id)) {
            throw new RuntimeException("Supplier not found");
        }
        supplierRepository.deleteById(id);
    }

    @Override
    public Optional<Supplier> getSupplierById(Long id) {
        return supplierRepository.findById(id);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }
}