package com.github.danielleziemba.tenmo.contoller;

import com.github.danielleziemba.tenmo.model.Transfer;
import com.github.danielleziemba.tenmo.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    TransferService transferService;

    @PostMapping("/send")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Transfer> sendTransfer() {
        return null;
    }
}
