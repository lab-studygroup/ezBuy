package com.ezbuy.email.dto;

public record EmailRequestDTO(
    String email,
    String subject,
    String body
) {}
