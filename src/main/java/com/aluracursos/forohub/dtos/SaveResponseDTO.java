package com.aluracursos.forohub.dtos;

import jakarta.validation.constraints.NotBlank;

public record SaveResponseDTO(

        @NotBlank(message = "Author ID is mandatory")
        String idAuthor,

        @NotBlank(message = "Topic ID is mandatory")
        String idTopic,

        @NotBlank(message = "Message is mandatory")
        String message

) {
}
