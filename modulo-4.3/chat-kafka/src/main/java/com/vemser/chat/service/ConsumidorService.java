package com.vemser.chat.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vemser.chat.dto.MensagemDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumidorService {
    private final ObjectMapper objectMapper;

    @KafkaListener(
            groupId = "grupo1",
            clientIdPrefix = "geral",
            topicPartitions = {@TopicPartition(topic = "${group.kafka.topic}", partitions = {"0"})}
    )
    public void consumirChatGeral(@Payload String mensagem) throws JsonProcessingException {
        MensagemDTO mensagemDTO = objectMapper.readValue(mensagem, MensagemDTO.class);
        log.info("{} [{}]: {}",
                mensagemDTO.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                mensagemDTO.getUsuario(),
                mensagemDTO.getMensagem());
    }

    @KafkaListener(
            groupId = "grupo2",
            clientIdPrefix = "meu-chat",
            topicPartitions = {@TopicPartition(topic = "${meu.kafka.topic}", partitions = {"4"})}
    )
    public void consumirChatMatheus(@Payload String mensagem) throws JsonProcessingException {
        MensagemDTO mensagemDTO = objectMapper.readValue(mensagem, MensagemDTO.class);
        log.info("{} [{}] (privado): {}",
                mensagemDTO.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                mensagemDTO.getUsuario(),
                mensagemDTO.getMensagem());
    }
}
