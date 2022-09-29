import java.util.Scanner;

public class Exercicio09 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Digite o horário de início: ");
    System.out.println("Digite a hora inicial: ");
    int horaInicial = Integer.parseInt(scanner.nextLine());

    System.out.println("Digite o minuto inicial: ");
    int minutoInicial = Integer.parseInt(scanner.nextLine());

    System.out.println("Digite a hora final: ");
    int horaFinal = Integer.parseInt(scanner.nextLine());

    System.out.println("Digite o minuto final: ");
    int minutoFinal = Integer.parseInt(scanner.nextLine());

    int totalEmMinutos = ((horaFinal - horaInicial - 1) * 60) + ((60 - minutoInicial) + minutoFinal);

    int horaDuracao = totalEmMinutos / 60;
    int minutoDuracao = totalEmMinutos - (horaDuracao * 60);

    System.out.println("O jogo teve duracao de: " + horaDuracao + "h" + minutoDuracao + "mim.");
  }
}
