package com.ezbuy.email.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezbuy.email.dto.EmailRequestDTO;
import com.ezbuy.email.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService mailService;

    @PostMapping
    public ResponseEntity<Void> sendMail(@RequestBody EmailRequestDTO mailRequestDTO) {
        mailService.sendMail(mailRequestDTO);
        return ResponseEntity.ok().build();
    }
   
}
