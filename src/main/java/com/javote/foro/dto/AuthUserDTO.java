package com.javote.foro.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthUserDTO(

        @NotBlank
        String email,

        @NotBlank
        String password
) {}
