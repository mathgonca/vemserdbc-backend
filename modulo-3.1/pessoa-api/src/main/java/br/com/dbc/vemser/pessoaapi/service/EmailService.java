package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.entity.Endereco;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final freemarker.template.Configuration fmConfiguration;

    @Value("${spring.mail.username}")
    private String from;

    private static final String TO = "math3usgoncalves@protonmail.com";

    private final JavaMailSender emailSender;

    public void sendSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(TO);
        message.setSubject("Assunto");
        message.setText("Teste \n minha mensagem \n\nAtt,\nSistema.");
        emailSender.send(message);
    }

    public void mandarEmailCadastroPessoa(String nome, Integer id, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(TO);
        message.setSubject("Boas vindas!");
        message.setText("Olá " + nome +
                "\nEstamos felizes em ter você em nosso sistema :)" +
                "\nSeu cadastro foi realizado com sucesso, seu identificador é " + id + "." +
                "\nQualquer dúvida é só contatar o suporte pelo e-mail " + from +
                "\nAtt,\nSistema.");
        emailSender.send(message);
    }

    public void mandarEmailAtualizacaoPessoa(String nome, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("Cadastro Atualizado com Sucesso");
        message.setText("Olá" + nome + "\nSeus dados foram atualizados no nosso sistema" +
                "\nQualquer dúvida é só contatar o suporte pelo e-mail " + from +
                "\nAtt,\nSistema.");
        emailSender.send(message);
    }

    public void mandarEmailCadastroEndereco(String nome, String email, Endereco endereco) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("Endereço Cadastrado com Sucesso");
        message.setText("Olá" + nome + "\nUm novo endereço foi cadastrado na sua conta"+
                "\nId: " + endereco.getIdEndereco() +
                "\nLogradouro: " + endereco.getLogradouro() +
                "\nNúmero: " + endereco.getNumero() + " Complemento: " + endereco.getComplemento() +
                "\nCEP: " + endereco.getCep() +
                "\nCidade: " + endereco.getCidade() + " Estado: " + endereco.getEstado() +
                "\nQualquer dúvida é só contatar o suporte pelo e-mail " + from +
                "\nAtt,\nSistema.");

        emailSender.send(message);
    }

    public void mandarEmailAtualizacaoEndereco(String nome, String email, Endereco endereco) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("Endereço Cadastrado com Sucesso");
        message.setText("Olá" + nome + "\nSeu endereço foi atualizado"+
                "\nId: " + endereco.getIdEndereco() +
                "\nLogradouro: " + endereco.getLogradouro() +
                "\nNúmero: " + endereco.getNumero() + " Complemento: " + endereco.getComplemento() +
                "\nCEP: " + endereco.getCep() +
                "\nCidade: " + endereco.getCidade() + " Estado: " + endereco.getEstado() +
                "\nQualquer dúvida é só contatar o suporte pelo e-mail " + from +
                "\nAtt,\nSistema.");

        emailSender.send(message);
    }

    public void mandarEmailDeletarEndereco(String nome, String email, Integer idEndereco) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("Endereço Deletado com Sucesso");
        message.setText("Olá" + nome + "\nSeu endereço de Id: " + idEndereco + "foi removido" +
                "\nQualquer dúvida é só contatar o suporte pelo e-mail " + from +
                "\nAtt,\nSistema.");

        emailSender.send(message);
    }

    public void sendWithAttachment() throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message,
                true);

        helper.setFrom(from);
        helper.setTo(TO);
        helper.setSubject("Subject");
        helper.setText("Teste\n minha mensagem \n\nAtt,\nSistema.");

        File file1 = new File("imagem.jpg");
        FileSystemResource file
                = new FileSystemResource(file1);
        helper.addAttachment(file1.getName(), file);

        emailSender.send(message);
    }

    public void sendEmail() {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(TO);
            mimeMessageHelper.setSubject("subject");
            mimeMessageHelper.setText(geContentFromTemplate(), true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    public String geContentFromTemplate() throws IOException, TemplateException {
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", "MeuNome");
        Template template = fmConfiguration.getTemplate("email-template.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        return html;
    }
}
