import java.util.Scanner;

public class Exercicio02 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Escolha um estado:\n1 - RS\n2 - SC\n3 - PR");
    int estado = Integer.parseInt(scanner.nextLine());

    int cidade = 0;

    switch (estado) {
      case 1:
        System.out.println("1 - Canoas 2 - POA");
        cidade = Integer.parseInt(scanner.nextLine());
        switch (cidade) {
          case 1:
            System.out.println("Canoas");
            break;
          case 2:
            System.out.println("POA");
            break;
        }
        break;
      case 2:
        System.out.println("1 - Floripa 2 - Blumenal");
        cidade = Integer.parseInt(scanner.nextLine());
        switch (cidade) {
          case 1:
            System.out.println("Floripa");
            break;
          case 2:
            System.out.println("Blumenal");
            break;
        }
        break;
      case 3:
        System.out.println("1- Curitiba 2 - Londrina");
        cidade = Integer.parseInt(scanner.nextLine());
        switch (cidade) {
          case 1:
            System.out.println("Curitiba");
            break;
          case 2:
            System.out.println("Londrina");
            break;
        }
        break;
      default:
        System.out.println("Opcao invalida!");
    }
  }
}
