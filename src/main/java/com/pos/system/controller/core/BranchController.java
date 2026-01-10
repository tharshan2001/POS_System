package com.pos.system.controller.core;

import com.pos.system.entity.Core.Branch;
import com.pos.system.entity.Lookup.BranchType;
import com.pos.system.repository.core.BranchRepository;
import com.pos.system.service.Lookup.BranchTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/branches")
public class BranchController {

    private final BranchRepository branchRepository;
    private final BranchTypeService branchTypeService;

    public BranchController(BranchRepository branchRepository, BranchTypeService branchTypeService) {
        this.branchRepository = branchRepository;
        this.branchTypeService = branchTypeService;
    }

    //  Create Branch
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Branch create(@RequestBody Branch branch) {

        //Check if a branch with the same name already exists
        if (branchRepository.existsByName(branch.getName().trim())) {
            throw new RuntimeException("Branch already exists");
        }
        //Force the branch type to RETAIL
        BranchType retailType = branchTypeService.getByName("RETAIL");
        branch.setType(retailType);

        //Save and return
        return branchRepository.save(branch);
    }


    //  Get all branches
    @GetMapping
    public List<Branch> getAll() {
        return branchRepository.findAll();
    }


    //  Get branch by ID
    @GetMapping("/{id}")
    public Branch getById(@PathVariable Long id) {
        return branchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Branch not found"));
    }

    //  Update branch
    @PutMapping("/{id}")
    public Branch update(@PathVariable Long id, @RequestBody Branch updatedBranch) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        branch.setName(updatedBranch.getName());
        branch.setAddress(updatedBranch.getAddress());
        branch.setType(updatedBranch.getType());

        return branchRepository.save(branch);
    }

    //  Delete branch
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!branchRepository.existsById(id)) {
            throw new RuntimeException("Branch not found");
        }
        branchRepository.deleteById(id);
    }
}
