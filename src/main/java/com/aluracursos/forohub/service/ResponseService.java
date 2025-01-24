package com.aluracursos.forohub.service;

import com.aluracursos.forohub.dtos.ResponseInfoDTO;
import com.aluracursos.forohub.dtos.SaveResponseDTO;
import com.aluracursos.forohub.exceptions.TopicNotFoundException;
import com.aluracursos.forohub.model.Response;
import com.aluracursos.forohub.model.Topic;
import com.aluracursos.forohub.model.User;
import com.aluracursos.forohub.repository.ResponseRepository;
import com.aluracursos.forohub.repository.TopicRepository;
import com.aluracursos.forohub.utils.AuthenticatedUserProvider;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseService implements IResponseService{

    private final ResponseRepository responseRepository;
    private final TopicRepository topicRepository;
    private final AuthenticatedUserProvider authenticatedUserProvider;

    @Autowired
    public ResponseService(ResponseRepository responseRepository, TopicRepository topicRepository,
                           AuthenticatedUserProvider authenticatedUserProvider) {
        this.responseRepository = responseRepository;
        this.topicRepository = topicRepository;
        this.authenticatedUserProvider = authenticatedUserProvider;
    }

    @Override
    @Transactional
    public ResponseInfoDTO createResponse(Long topicId, SaveResponseDTO saveResponseDTO) {
        // Obtener el usuario autenticado
        User author = authenticatedUserProvider.getAuthenticatedUser();

        // Verificar la existencia del tópico
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new TopicNotFoundException("Topic not found with id: " + topicId));

        // Crear y guardar la respuesta
        Response response = responseRepository.save(
                new Response(saveResponseDTO.message(), author, topic)
        );
        return new ResponseInfoDTO(response);
    }
}
