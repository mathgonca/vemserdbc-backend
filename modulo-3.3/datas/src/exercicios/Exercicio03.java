package exercicios;

import java.time.LocalDateTime;

public class Exercicio03 {
    public static void main(String[] args) {
        LocalDateTime data = LocalDateTime.now().plusDays(15).plusHours(10);
        System.out.println("Dia da semana: " + data.getDayOfWeek());
        System.out.println("Dia do ano: " + data.getDayOfYear());
    }
}
