package com.pos.system.controller.sale;

import com.pos.system.entity.Sale.Sale;
import com.pos.system.entity.people.User;
import com.pos.system.repository.people.UserRepository;
import com.pos.system.repository.sales.SaleRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleRepository saleRepository;
    private final UserRepository userRepository;

    public SaleController(SaleRepository saleRepository,
                          UserRepository userRepository) {
        this.saleRepository = saleRepository;
        this.userRepository = userRepository;
    }

    // ✅ Cashier / Shop Manager create sale
    @PreAuthorize("hasAnyRole('CASHIER','SHOP_MANAGER','ADMIN','SUPER_ADMIN')")
    @PostMapping
    public Sale createSale(@RequestBody Sale sale) {

        User currentUser = getCurrentUser();

        // 🔒 FORCE branch from logged-in user
        sale.setUser(currentUser);
        sale.setBranch(currentUser.getBranch());
        sale.setSaleDate(LocalDateTime.now());

        return saleRepository.save(sale);
    }

    // ✅ Users see ONLY their branch sales
    @PreAuthorize("hasAnyRole('CASHIER','SHOP_MANAGER','ADMIN','SUPER_ADMIN')")
    @GetMapping
    public List<Sale> getMyBranchSales() {

        User currentUser = getCurrentUser();

        return saleRepository.findByBranchId(
                currentUser.getBranch().getId()
        );
    }

    // ✅ View single sale (branch safe)
    @PreAuthorize("hasAnyRole('CASHIER','SHOP_MANAGER','ADMIN','SUPER_ADMIN')")
    @GetMapping("/{id}")
    public Sale getSale(@PathVariable Long id) {

        User currentUser = getCurrentUser();

        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found"));

        if (!sale.getBranch().getId().equals(currentUser.getBranch().getId())
                && !hasAdminRole()) {
            throw new RuntimeException("Access denied");
        }

        return sale;
    }

    // -------- helpers --------

    private User getCurrentUser() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private boolean hasAdminRole() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(a ->
                        a.getAuthority().equals("ROLE_ADMIN")
                                || a.getAuthority().equals("ROLE_SUPER_ADMIN")
                );
    }
}
