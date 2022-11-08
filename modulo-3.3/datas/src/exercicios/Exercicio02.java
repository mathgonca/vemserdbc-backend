package exercicios;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Exercicio02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        int dias;
        int meses;
        int anos;

        System.out.print("Digite a primeira data (dd-MM-yyyy): ");
        String primeiraData = scanner.nextLine();
        LocalDate date1 = LocalDate.parse(primeiraData, formatter);

        System.out.print("Digite a segunda data (dd-MM-yyyy): ");
        String segundaData = scanner.nextLine();
        LocalDate date2 = LocalDate.parse(segundaData, formatter);

        Period period;
        boolean date1IsAfterDate2 = date1.isAfter(date2);
        if (date1IsAfterDate2) {
            period = Period.between(date2, date1);
        } else {
            period = Period.between(date1, date2);
        }

        dias = period.getDays();
        meses = period.getMonths();
        anos = period.getYears();

        System.out.println("Data 1: " + date1.format(formatter));
        System.out.println("Data 2: " + date2.format(formatter));
        System.out.println("Direça entre as datas: " + anos + " anos " + meses + " meses "+ dias + " dias.");
    }
}
