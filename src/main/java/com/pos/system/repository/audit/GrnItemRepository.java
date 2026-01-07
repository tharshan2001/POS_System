package com.pos.system.repository.audit;


import com.pos.system.entity.Audit.GrnItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrnItemRepository extends JpaRepository<GrnItem, Long> {

    List<GrnItem> findByGrnId(Long grnId);
}