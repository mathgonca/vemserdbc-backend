package exercicios;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Exercicio01 {
    public static void main(String[] args) {
        final int ANO_ATUAL = LocalDate.now().getYear();
        final int PROXIMO_ANO = LocalDate.now().plusYears(1).getYear();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        System.out.print("Digite a sua data de nascimento (dd-MM-yyyy): ");
        Scanner scanner = new Scanner(System.in);
        String dtNascimento = scanner.nextLine();

        LocalDate dtAniversario = LocalDate.parse(dtNascimento, formatter);
        dtAniversario = dtAniversario.withYear(ANO_ATUAL);

        boolean jaFezAniversarioEsseAno = LocalDate.now().isAfter(dtAniversario);
        if (jaFezAniversarioEsseAno) {
            dtAniversario = dtAniversario.withYear(PROXIMO_ANO);
        }

        Period period = Period.between(LocalDate.now(), dtAniversario);
        int faltaDias = period.getDays();
        int faltaMeses = period.getMonths();

        System.out.println("=".repeat(75));
        System.out.println("Próximo aniversário: " + dtAniversario.format(formatter));
        System.out.println("Faltam " + faltaDias + " dias e " + faltaMeses + " meses para o seu aniversário.");
    }
}