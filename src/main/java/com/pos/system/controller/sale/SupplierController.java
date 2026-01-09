package com.pos.system.controller.sale;

import com.pos.system.entity.people.Supplier;
import com.pos.system.repository.people.SupplierRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierRepository supplierRepository;

    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    // --- Create a new supplier ---
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        supplier.setCreatedAt(LocalDateTime.now());
        return supplierRepository.save(supplier);
    }

    // --- Get all suppliers ---
    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    // --- Get a supplier by ID ---
    @GetMapping("/{id}")
    public Supplier getSupplierById(@PathVariable Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
    }

    // --- Update supplier ---
    @PutMapping("/{id}")
    public Supplier updateSupplier(@PathVariable Long id, @RequestBody Supplier updatedSupplier) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));

        supplier.setName(updatedSupplier.getName());
        supplier.setPhone(updatedSupplier.getPhone());
        supplier.setEmail(updatedSupplier.getEmail());
        supplier.setAddress(updatedSupplier.getAddress());
        supplier.setCreditTerms(updatedSupplier.getCreditTerms());

        return supplierRepository.save(supplier);
    }

    // --- Delete supplier ---
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSupplier(@PathVariable Long id) {
        if (!supplierRepository.existsById(id)) {
            throw new RuntimeException("Supplier not found with id: " + id);
        }
        supplierRepository.deleteById(id);
    }
}