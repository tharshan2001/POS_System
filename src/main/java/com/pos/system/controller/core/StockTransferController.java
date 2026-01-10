package com.pos.system.controller.core;

import com.pos.system.dto.core.StockTransferRequest;
import com.pos.system.dto.user.ResponseMsg;
import com.pos.system.entity.people.User;
import com.pos.system.impl.core.StockTransferService;
import com.pos.system.security.FindCurrentUser;
import com.pos.system.service.Core.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockTransferController {

    private final StockTransferService stockTransferService;
    private final FindCurrentUser currentUser;

    @PostMapping("/transfer")
    public ResponseMsg transferStock(@RequestBody StockTransferRequest request) {

        // 1️⃣ Get logged-in user (from branch)
        User user = currentUser.getCurrentUser();

        // 2️⃣ Perform transfer
        stockTransferService.transferStock(user, request);

        // 3️⃣ Return response
        ResponseMsg msg = new ResponseMsg();
        msg.setMsg(STR."Stock transferred successfully from branch \{user.getBranch().getName()} to branch ID \{request.getToBranchId()}");
        return msg;
    }
}