package com.vemser.chat.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vemser.chat.dto.MensagemDTO;
import com.vemser.chat.enums.NomeChat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProdutorService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void enviarMensagem(MensagemDTO mensagemDTO, NomeChat nomeChat) throws JsonProcessingException {
        String mensagemStr = objectMapper.writeValueAsString(mensagemDTO);

        MessageBuilder<String> stringMessageBuilder = MessageBuilder.withPayload(mensagemStr)
                .setHeader(KafkaHeaders.TOPIC, nomeChat.getTopico())
                .setHeader(KafkaHeaders.MESSAGE_KEY, UUID.randomUUID().toString())
                .setHeader(KafkaHeaders.PARTITION_ID, nomeChat.getParticao());
        Message<String> message = stringMessageBuilder.build();

        ListenableFuture<SendResult<String, String>> enviado = kafkaTemplate.send(message);

        enviado.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("Erro ao enviar mensagem!");
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("{} {} ({}): {}",
                        mensagemDTO.getDataCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                        mensagemDTO.getUsuario(),
                        nomeChat,
                        mensagemDTO.getMensagem()
                );
            }
        });
    }

    public void enviarMensagemChatsPrivados(String mensagem, List<NomeChat> nomeChatList) {
        MensagemDTO mensagemDTO = new MensagemDTO("${spring.kakfa.producer.client-id}",
                mensagem, LocalDateTime.now());
        nomeChatList.stream()
                .forEach(nomeChat -> {
                    try {
                        enviarMensagem(mensagemDTO, nomeChat);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
