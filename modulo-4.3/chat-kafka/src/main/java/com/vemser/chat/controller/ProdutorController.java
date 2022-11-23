package com.vemser.chat.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vemser.chat.enums.NomeChat;
import com.vemser.chat.service.ProdutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")
public class ProdutorController {
    private final ProdutorService produtorService;

    @PostMapping("/send-to")
    public void sendTo(@RequestParam List<NomeChat> chats, @RequestParam String mensagem) throws JsonProcessingException {
        produtorService.enviarMensagemChatsPrivados(mensagem, chats);
    }
}